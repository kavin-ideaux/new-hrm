package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Complaints;
import com.example.HRM.repository.employee.ComplaintsRepository;
import com.example.HRM.service.employee.ComplaintsService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class ComplaintsController {
	@Autowired
	  private ComplaintsService service;
	  
	  @Autowired
	  private ComplaintsRepository repo;
	  
	  @PostMapping({"/complaints/save"})
	  public ResponseEntity<String> saveComplaints(@RequestParam("attachments") MultipartFile file, @RequestParam("employeeId") long employeeId, @RequestParam("complaintsTitle") String complaintsTitle, @RequestParam("complaintsDate") Date complaintsDate, @RequestParam("complaintsAgainst") String complaintsAgainst, @RequestParam("description") String description) {
	    try {
	      byte[] bytes = file.getBytes();
	      Blob blob = new SerialBlob(bytes);
	      Complaints complaints = new Complaints();
	      complaints.setStatus(true);
	      complaints.setAttachments(blob);
	      complaints.setEmployeeId(employeeId);
	      complaints.setComplaintsTitle(complaintsTitle);
	      complaints.setComplaintsDate(complaintsDate);
	      complaints.setComplaintsAgainst(complaintsAgainst);
	      complaints.setDescription(description);
	      this.service.saveOrUpdate(complaints);
	      return ResponseEntity.ok("Complaints saved with id: " + complaints.getComplaintsId());
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving Complaints: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/complaints/or/{id}"})
	  public ResponseEntity<Boolean> toggleComplaintsStatus(@PathVariable(name = "id") long id) {
	    try {
	      Complaints complaints = this.service.getById(id);
	      if (complaints != null) {
	        boolean currentStatus = complaints.isStatus();
	        complaints.setStatus(!currentStatus);
	        this.service.saveOrUpdate(complaints);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(complaints.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @GetMapping({"/complaints"})
	  public ResponseEntity<List<Complaints>> displayAllImages(@RequestParam(required = true) String viewType) {
	    try {
	      if ("complaints".equals(viewType)) {
	        List<Complaints> images = this.service.listAll();
	        List<Complaints> imageObjects = new ArrayList<>();
	        for (Complaints image : images) {
	          int randomNumber = generateRandomNumber();
	          String imageUrl = "complaints/" + randomNumber + "/" + image.getComplaintsId();
	          Complaints imageObject = new Complaints();
	          imageObject.setComplaintsId(image.getComplaintsId());
	          imageObject.setEmployeeId(image.getEmployeeId());
	          imageObject.setComplaintsAgainst(image.getComplaintsAgainst());
	          imageObject.setStatus(true);
	          imageObject.setComplaintsDate(image.getComplaintsDate());
	          imageObject.setComplaintsTitle(image.getComplaintsTitle());
	          imageObject.setDescription(image.getDescription());
	          imageObject.setComplaintsAgainst(image.getComplaintsAgainst());
	          imageObject.setUrl(imageUrl);
	          imageObjects.add(imageObject);
	        } 
	        return ResponseEntity.ok().body(imageObjects);
	      } 
	      String errorMessage = "The provided viewType is not supported.";
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  private int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(1000000);
	  }
	  
	  @GetMapping({"complaints/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<Complaints> complaintsOptional = this.service.getById1(id.longValue());
	    if (complaintsOptional.isPresent()) {
	      byte[] fileBytes;
	      Complaints complaints = complaintsOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        fileBytes = complaints.getAttachments().getBytes(1L, (int)complaints.getAttachments().length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "inline; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  private String determineFileExtension(byte[] fileBytes) {
	    try {
	      String fileSignature = bytesToHex(Arrays.copyOfRange(fileBytes, 0, 4));
	      if (fileSignature.startsWith("89504E47"))
	        return "png"; 
	      if (fileSignature.startsWith("FFD8FF"))
	        return "jpg"; 
	      if (fileSignature.startsWith("52494646"))
	        return "webp"; 
	      if (fileSignature.startsWith("47494638"))
	        return "gif"; 
	      if (fileSignature.startsWith("66747970") || fileSignature.startsWith("00000020"))
	        return "mp4"; 
	      if (fileSignature.startsWith("25504446"))
	        return "pdf"; 
	    } catch (Exception exception) {}
	    return "unknown";
	  }
	  
	  private MediaType determineMediaType(String extension) {
	    switch (extension) {
	      case "png":
	        return MediaType.IMAGE_PNG;
	      case "jpg":
	        return MediaType.IMAGE_JPEG;
	      case "pdf":
	        return MediaType.APPLICATION_PDF;
	      case "webp":
	        return MediaType.parseMediaType("image/webp");
	      case "gif":
	        return MediaType.parseMediaType("image/gif");
	      case "mp4":
	        return MediaType.parseMediaType("video/mp4");
	    } 
	    return MediaType.APPLICATION_OCTET_STREAM;
	  }
	  
	  private String bytesToHex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	      sb.append(String.format("%02X", new Object[] { Byte.valueOf(b) }));
	    } 
	    return sb.toString();
	  }
	  
	  @PutMapping({"/complaints/edit/{id}"})
	  public ResponseEntity<?> updateComplaints(@PathVariable Long id, @RequestParam(value = "attachments", required = false) MultipartFile file, @RequestParam(value = "employeeId", required = false) long employeeId, @RequestParam(value = "complaintsTitle", required = false) String complaintsTitle, @RequestParam(value = "complaintsDate", required = false) Date complaintsDate, @RequestParam(value = "complaintsAgainst", required = false) String complaintsAgainst, @RequestParam(value = "description", required = false) String description) {
	    try {
	      Complaints existingComplaints = this.service.getById(id.longValue());
	      if (existingComplaints == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found."); 
	      existingComplaints.setComplaintsAgainst(complaintsAgainst);
	      existingComplaints.setDescription(description);
	      existingComplaints.setEmployeeId(employeeId);
	      existingComplaints.setComplaintsDate(complaintsDate);
	      existingComplaints.setComplaintsTitle(complaintsTitle);
	      if (file != null && !file.isEmpty()) {
	        byte[] bytes = file.getBytes();
	        Blob blob = new SerialBlob(bytes);
	        existingComplaints.setAttachments(blob);
	      } 
	      this.service.saveOrUpdate(existingComplaints);
	      return ResponseEntity.ok("Complaints updated successfully.Complaints ID: " + id);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee.");
	    } 
	  }
	  
	  @DeleteMapping({"/complaints/delete/{id}"})
	  public ResponseEntity<String> deleteComplaints(@PathVariable("id") long id) {
	    try {
	      this.service.deleteById(id);
	      return ResponseEntity.ok("Complaints deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting Complaints: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/complaints/view"})
	  public ResponseEntity<List<Map<String, Object>>> displayAllImages1(@RequestParam(required = true) String type) {
	    try {
	      if ("complaintstable".equals(type)) {
	        List<Map<String, Object>> images = this.repo.AllComplaints();
	        List<Map<String, Object>> imageResponses = new ArrayList<>();
	        for (Map<String, Object> image : images) {
	          int randomNumber = generateRandomNumber();
	          String imageUrl = "complaints/" + randomNumber + "/" + image.get("complaints_id");
	          Map<String, Object> imageResponse = new HashMap<>();
	          imageResponse.put("complaintsId", image.get("complaints_id"));
	          imageResponse.put("employeeId", image.get("employee_id"));
	          imageResponse.put("complaintsAgainst", image.get("complaints_against"));
	          imageResponse.put("complaintsTitle", image.get("complaints_title"));
	          imageResponse.put("description", image.get("description"));
	          imageResponse.put("complaintsDate", image.get("complaints_date"));
	          imageResponse.put("attachments", imageUrl);
	          imageResponse.put("userName", image.get("user_name"));
	          imageResponse.put("userId", image.get("user_id"));
	          imageResponse.put("status", image.get("status"));
	          imageResponses.add(imageResponse);
	        } 
	        return ResponseEntity.ok(imageResponses);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	}

