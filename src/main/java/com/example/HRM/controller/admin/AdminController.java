package com.example.HRM.controller.admin;
import com.example.HRM.JwtUtils;
import com.example.HRM.entity.admin.AdminLogin;
import com.example.HRM.entity.admin.LoginRequest;
import com.example.HRM.repository.admin.AdminLoginRepository;
import com.example.HRM.repository.admin.UserRepository;
import com.example.HRM.repository.eRecruitments.TraineeDetailsRepository;
import com.example.HRM.repository.employee.EmployeeAttendanceRepository;
import com.example.HRM.repository.employee.EmployeeRepository;
import com.example.HRM.service.admin.AdminLoginService;


import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	  private AdminLoginRepository adminLoginRepository;
	  
	  @Autowired
	  private EmployeeAttendanceRepository employeeAtten;
	  
	  @Autowired
	  private AdminLoginService adminLoginService;
	  
	  @Autowired
	  private EmployeeRepository repo;
	  
	  @Autowired
		private UserRepository repository;
	  
	  @Autowired
	  private TraineeDetailsRepository traineerepo;
	  
	  @GetMapping({"/admin"})
	  public ResponseEntity<?> displayAllImages1(@RequestParam(required = true) String viewType) {
	    if ("AdminLogin".equals(viewType)) {
	      List<AdminLogin> images = this.adminLoginService.listAll();
	      Map<String, Object> imageObjects = new HashMap<>();
	      for (AdminLogin image : images) {
	        int randomNumber = generateRandomNumber();
	        String imageUrl = "admin/" + randomNumber + "/" + image.getId();
	        image.setUrl(imageUrl);
	        imageObjects.put("Id", Long.valueOf(image.getId()));
	        imageObjects.put("name", image.getName());
	        imageObjects.put("image", image.getUrl());
	        imageObjects.put("password", image.getPassword());
	        imageObjects.put("confirmPassword", image.getConfirmPassword());
	        imageObjects.put("email", image.getEmail());
	      } 
	      return ResponseEntity.ok(imageObjects);
	    } 
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList());
	  }
	  
	  private int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(1000000);
	  }
	  
	  @GetMapping({"/admin/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<AdminLogin> complaintsOptional = this.adminLoginService.getById1(id.longValue());
	    if (complaintsOptional.isPresent()) {
	      AdminLogin complaints = complaintsOptional.get();
	      Blob image = complaints.getImage();
	      if (image != null) {
	        byte[] fileBytes;
	        String filename = "file_" + randomNumber + "_" + id;
	        try {
	          fileBytes = image.getBytes(1L, (int)image.length());
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
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
	  
	  @PutMapping({"admin/edit/{id}"})
	  public ResponseEntity<?> updateComplaints(@PathVariable Long id, @RequestParam(value = "image", required = false) MultipartFile file, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "confirmPassword", required = false) String confirmPassword) {
	    try {
	      AdminLogin existingComplaints = this.adminLoginService.getById(id.longValue());
	      if (existingComplaints == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found."); 
	      if (!Objects.equals(password, confirmPassword)) {
	        String errorMessage = "Password and ConfirmPassword do not match.";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	      } 
	      existingComplaints.setName(name);
	      existingComplaints.setConfirmPassword(confirmPassword);
	      existingComplaints.setPassword(password);
	      if (file != null && !file.isEmpty()) {
	        byte[] bytes = file.getBytes();
	        Blob blob = new SerialBlob(bytes);
	        existingComplaints.setImage(blob);
	      } 
	      this.adminLoginService.saveOrUpdate(existingComplaints);
	      return ResponseEntity.ok("Admin updated successfully. Admin ID: " + id);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating admin.");
	    } 
	  }
	  
	  @PostMapping({"/admin/login"})
	  public ResponseEntity<String> processLogin(@RequestBody LoginRequest loginRequest) {
	    String email = loginRequest.getEmail();
	    String password = loginRequest.getPassword();
	    AdminLogin admin = this.adminLoginRepository.findByEmail(email);
	    if (admin != null && admin.getPassword().equals(password)) {
	      String token = JwtUtils.generateToken(admin);
	      return ResponseEntity.ok(token);
	    } 
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	  }
	  
	  @PostMapping({"/admin/changepassword"})
	  public ResponseEntity<String> processChangePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
	    AdminLogin admin = this.adminLoginRepository.findByEmail(email);
	    if (admin != null && admin.getPassword().equals(oldPassword)) {
	      admin.setPassword(newPassword);
	      this.adminLoginRepository.save(admin);
	      return ResponseEntity.ok("Password changed successfully");
	    } 
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	  }
	  
	  @GetMapping({"/dashboard"})
	  public Map<String, Object> getAllDetails(@RequestParam(required = true) String attendance) {
	    Map<String, Object> productMap = new HashMap<>();
	    if ("dashboardcount".equals(attendance)) {
	      List<Map<String, Object>> attendanceData = this.employeeAtten.getAllpresent();
	      Object presentCount = Integer.valueOf(0);
	      Object absentCount = Integer.valueOf(0);
	      if (!attendanceData.isEmpty() && attendanceData.get(0) != null) {
	        presentCount = ((Map)attendanceData.get(0)).get("present_count");
	        absentCount = ((Map)attendanceData.get(0)).get("absent_count");
	      } 
	      productMap.put("present_count", presentCount);
	      productMap.put("absent_count", absentCount);
	      productMap.put("employee", Long.valueOf(this.repo.countByStatusTrue()));
	      productMap.put("trainee", Long.valueOf(this.traineerepo.countByStatusTrue()));
	    } 
	    return productMap;
	  }
	  
	  @GetMapping({"/dashboard/attendace"})
	  public List<Map<String, Object>> INeedList(@RequestParam(required = true) String attendace) {
	    try {
	      if ("dashboard".equals(attendace))
	        return this.employeeAtten.getAllpresent1(); 
	      throw new IllegalArgumentException("The provided dashboard is not supported.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  
	  @GetMapping("/admin/view/{id}/{roleId}")
		public ResponseEntity<Object> getHrInterviewDetailsWithId(@PathVariable("id") long id,
				@PathVariable("roleId") long roleId) {
		
			
			List<Map<String, Object>> user = repository.getAllEmployeesWithDetailsWithIdUser(id, roleId);
			List<Map<String, Object>> admin = adminLoginRepository.getAllEmployeesWithDetailsWithId(id,roleId);

			if (!admin.isEmpty() && admin.get(0).get("id") != null && admin.get(0).get("role_id") != null) {
					List<Map<String, Object>> adminList = new ArrayList<>();
					Map<String, Object> adminMap = new HashMap<>();
					adminMap.put("id", admin.get(0).get("id"));
					adminMap.put("confirmPassword", admin.get(0).get("confirm_password"));
					adminMap.put("email", admin.get(0).get("email"));
					adminMap.put("name", admin.get(0).get("name"));
					adminMap.put("password", admin.get(0).get("password"));
					adminMap.put("roleType", admin.get(0).get("role_type"));
					adminMap.put("status", admin.get(0).get("status"));
					adminMap.put("date", admin.get(0).get("date"));
					adminMap.put("inTime", admin.get(0).get("intime"));
					adminMap.put("roleId", admin.get(0).get("role_id"));
					int randomNumber = generateRandomNumber();
					Object complaintId1 = admin.get(0).get("id");
					String imageUrl1 = "admin/" + randomNumber + "/" + complaintId1;
					adminMap.put("image", imageUrl1);
//					adminList.add(adminMap);
					adminMap.putAll(adminMap);
					return ResponseEntity.ok(adminMap);
			} 

			else if (!user.isEmpty() && user.get(0).get("user_id") != null && user.get(0).get("role_id") != null) {
			    Map<String, Object> userMap = new HashMap<>();
			    userMap.put("id", user.get(0).get("user_id"));
			    userMap.put("email", user.get(0).get("email"));
			    userMap.put("confirmPassword", user.get(0).get("confirm_password"));
			    userMap.put("name", user.get(0).get("username"));
			    userMap.put("password", user.get(0).get("password"));
			    userMap.put("roleType", user.get(0).get("role_type"));
			    userMap.put("status", user.get(0).get("status"));
			    userMap.put("date", user.get(0).get("date"));
			    userMap.put("inTime", user.get(0).get("intime"));
			    userMap.put("roleId", user.get(0).get("role_id"));

			    int randomNumber = generateRandomNumber();
			    String fileExtension = getFileExtensionForImage1(user);
			    String imageUrl = "user/" + randomNumber + "/" + user.get(0).get("user_id") + "." + fileExtension;
			    userMap.put("image", imageUrl);

			    return ResponseEntity.ok(userMap);
			}

	
	else {
	    String errorMessage = "The Id does not exist";
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(Collections.singletonMap("error", errorMessage));
	}
	}
	  
	  
	  
	  private String getFileExtensionForImage1(List<Map<String, Object>> user) {
			if (user == null || !user.get(0).containsKey("url") || user.get(0).get("url") == null) {
		        return "jpg";
		    }
		    
		    String url = user.get(0).get("url").toString();
		    
		    if (url.endsWith(".png")) {
		        return "png";
		    } else if (url.endsWith(".jpg")) {
		        return "jpg";
		    } else {
		        return "jpg"; // You might want to handle other cases appropriately
		    }
		}
		
	  
	  
	  
	  
	}

