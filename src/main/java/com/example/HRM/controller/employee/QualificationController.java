package com.example.HRM.controller.employee;

import com.example.HRM.entity.employee.Qualification;
import com.example.HRM.repository.employee.QualificationRepository;
import com.example.HRM.service.employee.EmployeeService;
import com.example.HRM.service.employee.QualificationService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class QualificationController {
	 @Autowired
	  private QualificationService service;
	  
	  @Autowired
	  private EmployeeService service1;
	  
	  @Autowired
	  private QualificationRepository qualificationRepository;
	  
	  @Autowired
	  private QualificationService repo1;
	  
	  @PostMapping({"/qualification/save"})
	  public ResponseEntity<String> addImagePost(@RequestParam("resume") MultipartFile resumeFile, @RequestParam("ten") MultipartFile tenFile, @RequestParam("aadhar") MultipartFile aadharFile, @RequestParam("degree") MultipartFile degreeFile, @RequestParam("bankBook") MultipartFile bankBookFile, @RequestParam("twelve") MultipartFile twelveFile, @RequestParam("panno") MultipartFile pannoFile, @RequestParam("highestQualification") String highestQualification, @RequestParam("aadharNO") long aadharNO) {
	    try {
	      Qualification qualification = new Qualification();
	      qualification.setStatus(true);
	      qualification.setHighestQualification(highestQualification);
	      qualification.setAadharNO(aadharNO);
	      qualification.setResume(convertToBlob(resumeFile));
	      qualification.setTen(convertToBlob(tenFile));
	      qualification.setAadhar(convertToBlob(aadharFile));
	      qualification.setDegree(convertToBlob(degreeFile));
	      qualification.setBankBook(convertToBlob(bankBookFile));
	      qualification.setTwelve(convertToBlob(twelveFile));
	      qualification.setPanno(convertToBlob(pannoFile));
	      this.service.create(qualification);
	      long qualificationId = qualification.getQualificationId();
	      return ResponseEntity.ok("Images added successfully. Qualification ID: " + qualificationId);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error adding images: " + e.getMessage());
	    } 
	  }
	  
	  private Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
	    if (file != null && !file.isEmpty()) {
	      byte[] bytes = file.getBytes();
	      return new SerialBlob(bytes);
	    } 
	    return null;
	  }
	  
	  @GetMapping({"/qualification"})
	  public ResponseEntity<?> getAllQualifications1(@RequestParam(required = true) String Qualification) {
	    try {
	      if ("qualification".equals(Qualification)) {
	        List<Qualification> qualifications = this.service.getAllQualifications();
	        List<Qualification> qualificationResponses = new ArrayList<>();
	        for (Qualification qualification : qualifications) {
	          int resumeRandomNumber = generateRandomNumber();
	          int tenRandomNumber = generateRandomNumber();
	          int aadharRandomNumber = generateRandomNumber();
	          int degreeRandomNumber = generateRandomNumber();
	          int pannoRandomNumber = generateRandomNumber();
	          int bankBookRandomNumber = generateRandomNumber();
	          int twelveRandomNumber = generateRandomNumber();
	          String resumeUrl = "resumeUrl/" + resumeRandomNumber + "/" + qualification.getQualificationId();
	          String tenUrl = "tenUrl/" + tenRandomNumber + "/" + qualification.getQualificationId();
	          String aadharUrl = "aadharUrl/" + aadharRandomNumber + "/" + qualification.getQualificationId();
	          String degreeUrl = "degreeUrl/" + degreeRandomNumber + "/" + qualification.getQualificationId();
	          String pannoUrl = "pannoUrl/" + pannoRandomNumber + "/" + qualification.getQualificationId();
	          String bankBookUrl = "bankBookUrl/" + bankBookRandomNumber + "/" + qualification.getQualificationId();
	          String twelveUrl = "twelveUrl/" + twelveRandomNumber + "/" + qualification.getQualificationId();
	          Qualification qualificationResponse = new Qualification();
	          qualificationResponse.setQualificationId(qualification.getQualificationId());
	          qualificationResponse.setEmployeeId(qualification.getEmployeeId());
	          qualificationResponse.setStatus(qualification.isStatus());
	          qualificationResponse.setHighestQualification(qualification.getHighestQualification());
	          qualificationResponse.setAadharNO(qualification.getAadharNO());
	          qualificationResponse.setResumeurl(resumeUrl);
	          qualificationResponse.setTenurl(tenUrl);
	          qualificationResponse.setAadharurl(aadharUrl);
	          qualificationResponse.setDegreeurl(degreeUrl);
	          qualificationResponse.setPannourl(pannoUrl);
	          qualificationResponse.setBankBookurl(bankBookUrl);
	          qualificationResponse.setTwelveurl(twelveUrl);
	          qualificationResponses.add(qualificationResponse);
	        } 
	        return ResponseEntity.ok(qualificationResponses);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided qualification is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  private int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(10000);
	  }
	  
	  @GetMapping({"/qualification/{id}"})
	  public ResponseEntity<Qualification> getQualificationById(@PathVariable("id") long id) {
	    try {
	      Optional<Qualification> qualificationOptional = this.service.getQualificationById(id);
	      Qualification qualification = qualificationOptional.get();
	      if (qualification == null)
	        return ResponseEntity.notFound().build(); 
	      int resumeRandomNumber = generateRandomNumber();
	      int tenRandomNumber = generateRandomNumber();
	      int aadharRandomNumber = generateRandomNumber();
	      int degreeRandomNumber = generateRandomNumber();
	      int pannoRandomNumber = generateRandomNumber();
	      int bankBookRandomNumber = generateRandomNumber();
	      int twelveRandomNumber = generateRandomNumber();
	      String resumeUrl = "resumeUrl/" + resumeRandomNumber + "/" + qualification.getQualificationId();
	      String tenUrl = "tenUrl/" + tenRandomNumber + "/" + qualification.getQualificationId();
	      String aadharUrl = "aadharUrl/" + aadharRandomNumber + "/" + qualification.getQualificationId();
	      String degreeUrl = "degreeUrl/" + degreeRandomNumber + "/" + qualification.getQualificationId();
	      String pannoUrl = "pannoUrl/" + pannoRandomNumber + "/" + qualification.getQualificationId();
	      String bankBookUrl = "bankBookUrl/" + bankBookRandomNumber + "/" + qualification.getQualificationId();
	      String twelveUrl = "twelveUrl/" + twelveRandomNumber + "/" + qualification.getQualificationId();
	      Qualification qualificationResponse = new Qualification();
	      qualificationResponse.setQualificationId(qualification.getQualificationId());
	      qualificationResponse.setEmployeeId(qualification.getEmployeeId());
	      qualificationResponse.setStatus(qualification.isStatus());
	      qualificationResponse.setHighestQualification(qualification.getHighestQualification());
	      qualificationResponse.setAadharNO(qualification.getAadharNO());
	      qualificationResponse.setResumeurl(resumeUrl);
	      qualificationResponse.setTenurl(tenUrl);
	      qualificationResponse.setAadharurl(aadharUrl);
	      qualificationResponse.setDegreeurl(degreeUrl);
	      qualificationResponse.setPannourl(pannoUrl);
	      qualificationResponse.setBankBookurl(bankBookUrl);
	      qualificationResponse.setTwelveurl(twelveUrl);
	      return ResponseEntity.ok(qualificationResponse);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @GetMapping({"aadharUrl/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile2(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<Qualification> awardsPhotoOptional = this.service.getAwardsPhotoById(id);
	    if (awardsPhotoOptional.isPresent()) {
	      byte[] fileBytes;
	      Qualification awardsPhoto = awardsPhotoOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        Blob aadharBlob = awardsPhoto.getAadhar();
	        if (aadharBlob == null)
	          return ResponseEntity.notFound().build(); 
	        fileBytes = aadharBlob.getBytes(1L, (int)aadharBlob.length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "attachment; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  @GetMapping({"bankBookUrl/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile3(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<Qualification> awardsPhotoOptional = this.service.getAwardsPhotoById(id);
	    if (awardsPhotoOptional.isPresent()) {
	      byte[] fileBytes;
	      Qualification awardsPhoto = awardsPhotoOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        Blob bankBlob = awardsPhoto.getBankBook();
	        if (bankBlob == null)
	          return ResponseEntity.notFound().build(); 
	        fileBytes = bankBlob.getBytes(1L, (int)bankBlob.length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "attachment; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  @GetMapping({"tenUrl/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile4(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<Qualification> awardsPhotoOptional = this.service.getAwardsPhotoById(id);
	    if (awardsPhotoOptional.isPresent()) {
	      byte[] fileBytes;
	      Qualification awardsPhoto = awardsPhotoOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        Blob tenBlob = awardsPhoto.getTen();
	        if (tenBlob == null)
	          return ResponseEntity.notFound().build(); 
	        fileBytes = tenBlob.getBytes(1L, (int)tenBlob.length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "attachment; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  @GetMapping({"twelveUrl/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile5(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<Qualification> awardsPhotoOptional = this.service.getAwardsPhotoById(id);
	    if (awardsPhotoOptional.isPresent()) {
	      byte[] fileBytes;
	      Qualification awardsPhoto = awardsPhotoOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        Blob twelveBlob = awardsPhoto.getTwelve();
	        if (twelveBlob == null)
	          return ResponseEntity.notFound().build(); 
	        fileBytes = twelveBlob.getBytes(1L, (int)twelveBlob.length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "attachment; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  @GetMapping({"degreeUrl/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile6(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<Qualification> awardsPhotoOptional = this.service.getAwardsPhotoById(id);
	    if (awardsPhotoOptional.isPresent()) {
	      byte[] fileBytes;
	      Qualification awardsPhoto = awardsPhotoOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        Blob degreeBlob = awardsPhoto.getDegree();
	        if (degreeBlob == null)
	          return ResponseEntity.notFound().build(); 
	        fileBytes = degreeBlob.getBytes(1L, (int)degreeBlob.length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "attachment; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  @GetMapping("pannoUrl/{randomNumber}/{id}")
		public ResponseEntity<?> serveFile7(@PathVariable("randomNumber") int randomNumber,
		        @PathVariable("id") Long id) {
		    Optional<Qualification> awardsPhotoOptional = service.getAwardsPhotoById(id);
		    if (awardsPhotoOptional.isPresent()) {
		        Qualification awardsPhoto = awardsPhotoOptional.get();
		        String filename = "file_" + randomNumber + "_" + id;
		        byte[] fileBytes;
		        if (awardsPhoto.getPanno() != null) { 
				    try {
				        fileBytes = awardsPhoto.getPanno().getBytes(1, (int) awardsPhoto.getPanno().length());
				    } catch (SQLException e) {
				        e.printStackTrace();  

				        String errorMessage = "An error occurred while retrieving bytes from the 'panno' field.";
				        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
				    }
				} else {	             
				    String errorMessage = "'panno' field is null.";
				    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
				}


				String extension = determineFileExtension(fileBytes);
				MediaType mediaType = determineMediaType(extension);

				// Create the resource and set headers
				ByteArrayResource resource = new ByteArrayResource(fileBytes);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(mediaType);
				headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + "." + extension);
				return ResponseEntity.ok().headers(headers).body(resource);
		    } else {
		    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
	  @GetMapping({"resumeUrl/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile8(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<Qualification> awardsPhotoOptional = this.service.getAwardsPhotoById(id);
	    if (awardsPhotoOptional.isPresent()) {
	      byte[] fileBytes;
	      Qualification awardsPhoto = awardsPhotoOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        Blob resumeBlob = awardsPhoto.getResume();
	        if (resumeBlob == null)
	          return ResponseEntity.notFound().build(); 
	        fileBytes = resumeBlob.getBytes(1L, (int)resumeBlob.length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "attachment; filename=" + filename + "." + extension);
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
	      if (fileSignature.startsWith("25504446"))
	        return "pdf"; 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
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
	  
	  @PutMapping({"/qualification/or/{id}"})
	  public ResponseEntity<Boolean> toggleQualificationStatus(@PathVariable(name = "id") long id) {
	    try {
	      Optional<Qualification> qualificationOptional = this.service.getQualificationById(id);
	      Qualification qualification = qualificationOptional.get();
	      if (qualificationOptional.isPresent()) {
	        boolean currentStatus = qualification.isStatus();
	        qualification.setStatus(!currentStatus);
	        this.service.update(qualification);
	        int resumeRandomNumber = generateRandomNumber();
	        int tenRandomNumber = generateRandomNumber();
	        int aadharRandomNumber = generateRandomNumber();
	        int degreeRandomNumber = generateRandomNumber();
	        int pannoRandomNumber = generateRandomNumber();
	        int bankBookRandomNumber = generateRandomNumber();
	        int twelveRandomNumber = generateRandomNumber();
	        String resumeUrl = "resumeUrl/" + resumeRandomNumber + "/" + qualification.getQualificationId();
	        String tenUrl = "tenUrl/" + tenRandomNumber + "/" + qualification.getQualificationId();
	        String aadharUrl = "aadharUrl/" + aadharRandomNumber + "/" + qualification.getQualificationId();
	        String degreeUrl = "degreeUrl/" + degreeRandomNumber + "/" + qualification.getQualificationId();
	        String pannoUrl = "pannoUrl/" + pannoRandomNumber + "/" + qualification.getQualificationId();
	        String bankBookUrl = "bankBookUrl/" + bankBookRandomNumber + "/" + qualification.getQualificationId();
	        String twelveUrl = "twelveUrl/" + twelveRandomNumber + "/" + qualification.getQualificationId();
	        Qualification qualificationResponse = new Qualification();
	        qualificationResponse.setQualificationId(qualification.getQualificationId());
	        qualificationResponse.setEmployeeId(qualification.getEmployeeId());
	        qualificationResponse.setHighestQualification(qualification.getHighestQualification());
	        qualificationResponse.setAadharNO(qualification.getAadharNO());
	        qualificationResponse.setResumeurl(resumeUrl);
	        qualificationResponse.setTenurl(tenUrl);
	        qualificationResponse.setAadharurl(aadharUrl);
	        qualificationResponse.setDegreeurl(degreeUrl);
	        qualificationResponse.setPannourl(pannoUrl);
	        qualificationResponse.setBankBookurl(bankBookUrl);
	        qualificationResponse.setTwelveurl(twelveUrl);
	        List<Qualification> qualificationResponses = new ArrayList<>();
	        qualificationResponses.add(qualificationResponse);
	        return ResponseEntity.ok(Boolean.valueOf(qualification.isStatus()));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(false));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/qualification/update/{id}"})
	  public ResponseEntity<String> updateQualification(@PathVariable("id") Long id, @RequestParam(value = "resume", required = false) MultipartFile resumeFile, @RequestParam(value = "ten", required = false) MultipartFile tenFile, @RequestParam(value = "aadhar", required = false) MultipartFile aadharFile, @RequestParam(value = "degree", required = false) MultipartFile degreeFile, @RequestParam(value = "bankBook", required = false) MultipartFile bankBookFile, @RequestParam(value = "twelve", required = false) MultipartFile twelveFile, @RequestParam(value = "panno", required = false) MultipartFile pannoFile, @RequestParam(value = "highestQualification", required = false) String highestQualification, @RequestParam(value = "aadharNO", required = false) long aadharNO) {
	    try {
	      Optional<Qualification> qualificationOptional = this.service.getQualificationById(id.longValue());
	      if (qualificationOptional.isPresent()) {
	        Qualification qualification = qualificationOptional.get();
	        qualification.setHighestQualification(highestQualification);
	        qualification.setAadharNO(aadharNO);
	        if (resumeFile != null && !resumeFile.isEmpty()) {
	          int resumeRandomNumber = generateRandomNumber();
	          String resumeUrl = "resumeUrl/" + resumeRandomNumber + "/" + qualification.getQualificationId();
	          qualification.setResume(convertToBlob(resumeFile));
	          qualification.setResumeurl(resumeUrl);
	        } 
	        if (aadharFile != null && !aadharFile.isEmpty()) {
	          int aadharRandomNumber = generateRandomNumber();
	          String aadharUrl = "aadharUrl/" + aadharRandomNumber + "/" + qualification.getQualificationId();
	          qualification.setAadhar(convertToBlob(aadharFile));
	          qualification.setAadharurl(aadharUrl);
	        } 
	        if (bankBookFile != null && !bankBookFile.isEmpty()) {
	          int bankBookRandomNumber = generateRandomNumber();
	          String bankBookUrl = "bankBookUrl/" + bankBookRandomNumber + "/" + qualification.getQualificationId();
	          qualification.setBankBook(convertToBlob(bankBookFile));
	          qualification.setBankBookurl(bankBookUrl);
	        } 
	        if (pannoFile != null && !pannoFile.isEmpty()) {
	          int pannoRandomNumber = generateRandomNumber();
	          String pannoUrl = "pannoUrl/" + pannoRandomNumber + "/" + qualification.getQualificationId();
	          qualification.setPanno(convertToBlob(pannoFile));
	          qualification.setPannourl(pannoUrl);
	        } 
	        if (twelveFile != null && !twelveFile.isEmpty()) {
	          int twelveRandomNumber = generateRandomNumber();
	          String twelveUrl = "twelveUrl/" + twelveRandomNumber + "/" + qualification.getQualificationId();
	          qualification.setTwelve(convertToBlob(twelveFile));
	          qualification.setTwelveurl(twelveUrl);
	        } 
	        if (tenFile != null && !tenFile.isEmpty()) {
	          int tenRandomNumber = generateRandomNumber();
	          String tenUrl = "tenUrl/" + tenRandomNumber + "/" + qualification.getQualificationId();
	          qualification.setTen(convertToBlob(tenFile));
	          qualification.setTenurl(tenUrl);
	        } 
	        if (degreeFile != null && !degreeFile.isEmpty()) {
	          int degreeRandomNumber = generateRandomNumber();
	          String degreeUrl = "degreeUrl/" + degreeRandomNumber + "/" + qualification.getQualificationId();
	          qualification.setDegree(convertToBlob(degreeFile));
	          qualification.setDegreeurl(degreeUrl);
	        } 
	        this.service.update(qualification);
	        return ResponseEntity.ok("Qualification with ID updated successfully." + id);
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error updating qualification: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/qualification/view"})
	  public ResponseEntity<?> getAllQualifications(@RequestParam(required = true) String qualificationType) {
	    try {
	      if ("qualification".equals(qualificationType)) {
	        List<Map<String, Object>> qualifications = this.repo1.getAllQualificationsByImage();
	        List<Map<String, Object>> qualificationResponses = new ArrayList<>();
	        Map<String, List<Map<String, Object>>> qualificationsGroupMap = (Map<String, List<Map<String, Object>>>)qualifications.stream().collect(Collectors.groupingBy(action -> String.valueOf(action.get("qualification_id"))));
	        for (Map.Entry<String, List<Map<String, Object>>> qualification : qualificationsGroupMap.entrySet()) {
	          int resumeRandomNumber = generateRandomNumber();
	          int tenRandomNumber = generateRandomNumber();
	          int aadharRandomNumber = generateRandomNumber();
	          int degreeRandomNumber = generateRandomNumber();
	          int pannoRandomNumber = generateRandomNumber();
	          int bankBookRandomNumber = generateRandomNumber();
	          int twelveRandomNumber = generateRandomNumber();
	          String resumeUrl = "resumeUrl/" + resumeRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	          String tenUrl = "tenUrl/" + tenRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	          String aadharUrl = "aadharUrl/" + aadharRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	          String degreeUrl = "degreeUrl/" + degreeRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	          String pannoUrl = "pannoUrl/" + pannoRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	          String bankBookUrl = "bankBookUrl/" + bankBookRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	          String twelveUrl = "twelveUrl/" + twelveRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	          Map<String, Object> qualificationResponse = new HashMap<>();
	          qualificationResponse.put("qualificationId", qualification.getValue().get(0).get("qualification_id"));
	          qualificationResponse.put("status", qualification.getValue().get(0).get("status"));
	          qualificationResponse.put("employeeId", qualification.getValue().get(0).get("employee_id"));
	          qualificationResponse.put("highestQualification", qualification.getValue().get(0).get("highest_qualification"));
	          qualificationResponse.put("aadharno", qualification.getValue().get(0).get("aadharno"));
	          qualificationResponse.put("resumeurl", resumeUrl);
	          qualificationResponse.put("tenUrl", tenUrl);
	          qualificationResponse.put("aadharUrl", aadharUrl);
	          qualificationResponse.put("degreeUrl", degreeUrl);
	          qualificationResponse.put("pannoUrl", pannoUrl);
	          qualificationResponse.put("bankBookUrl", bankBookUrl);
	          qualificationResponse.put("twelveUrl", twelveUrl);
	          qualificationResponse.put("usertName", qualification.getValue().get(0).get("user_name"));
	          qualificationResponse.put("userId", qualification.getValue().get(0).get("user_id"));
	          qualificationResponses.add(qualificationResponse);
	        } 
	        return ResponseEntity.ok(qualificationResponses);
	      } 
	      return ResponseEntity.badRequest().body("The provided qualificationType is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	}
