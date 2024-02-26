package com.example.HRM.controller.admin;

import com.example.HRM.entity.admin.AdminLogin;
import com.example.HRM.entity.admin.LoginRequest;
import com.example.HRM.entity.admin.User;
import com.example.HRM.entity.clientDetails.ClientProfile;
import com.example.HRM.entity.employee.Employee;
import com.example.HRM.repository.admin.AdminLoginRepository;
import com.example.HRM.repository.admin.UserRepository;
import com.example.HRM.repository.clientDetails.ClientRepository;
import com.example.HRM.repository.employee.EmployeeRepository;
import com.example.HRM.service.admin.UserService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	  private UserService userService;
	  
	  @Autowired
	  private AdminLoginRepository adminLoginRepository;
	  
	  @Autowired
	  private EmployeeRepository employeeRepository;
	  
	  @Autowired
	  private ClientRepository clientRepository;
	  
	  @Autowired
	  private UserRepository userRepository;
	  
	  @GetMapping({"/user/withoutrole"})
	  public ResponseEntity<Object> getUserDetails(@RequestParam(required = true) String user) {
	    if ("manager".equals(user))
	      return ResponseEntity.ok(this.userService.singleUser()); 
	    String errorMessage = "Invalid value for 'user'. Expected 'manager'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/user/save"})
	  public ResponseEntity<?> addEmployeeWithImage(@RequestParam("userProfile") MultipartFile file, @RequestParam("username") String username, @RequestParam("address") String address, @RequestParam("location") String location, @RequestParam("country") String country, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, @RequestParam("city") String city, @RequestParam("roleType") String roleType, @RequestParam("mobileNumber") long mobileNumber) {
	    try {
	      User existingUser = this.userService.getUserByEmail(email);
	      if (existingUser != null)
	        return ResponseEntity.badRequest().body("Email is already in use. Please use a different email."); 
	      byte[] bytes = file.getBytes();
	      Blob blob = new SerialBlob(bytes);
	      User user = new User();
	      user.setUserProfile(blob);
	      user.setStatus(true);
	      user.setUsername(username);
	      user.setCountry(country);
	      user.setEmail(email);
	      user.setCity(city);
	      user.setAddress(address);
	      user.setConfirmPassword(confirmPassword);
	      user.setDate(LocalDate.now());
	      LocalDateTime currentTime = LocalDateTime.now();
	        user.setIntime(currentTime);
	      user.setPassword(password);
	      user.setMobileNumber(mobileNumber);
	      user.setRoleType(roleType);
	      user.setLocation(location);
	      if (!password.equals(confirmPassword))
	        return ResponseEntity.badRequest().body("Password and confirm password do not match"); 
	      if (user.getRoleType().equals("Manager")) {
	        user.setRoleId(2);
	      }  else {
	        user.setRoleId(0);
	      } 
	      this.userService.saveSingleUser(user);
	      long id = user.getUserId();
	      return ResponseEntity.ok("user added successfully. user ID: " + id);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding user.");
	    } 
	  }
	  
	  @PutMapping({"/user/ststus/{id}"})
	  public ResponseEntity<Boolean> toggleComplaintsStatus(@PathVariable(name = "id") long id) {
	    try {
	      User user = this.userService.getCompanyById(Long.valueOf(id));
	      if (user != null) {
	        boolean currentStatus = user.isStatus();
	        user.setStatus(!currentStatus);
	        this.userService.saveSingleUser(user);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(user.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @GetMapping({"/user/{id}"})
	  public ResponseEntity<?> displayUserDetailsAndImageUrls(@PathVariable("id") Long userId) {
	    List<User> users = this.userService.getUserById(userId);
	    Map<String, Object> userDetailsWithImages = new HashMap<>();
	    if (users != null && !users.isEmpty()) {
	      for (User user : users) {
	        Map<String, Object> userDetails = new HashMap<>();
	        userDetails.put("userId", Long.valueOf(user.getUserId()));
	        userDetails.put("username", user.getUsername());
	        userDetails.put("address", user.getAddress());
	        userDetails.put("city", user.getCity());
	        userDetails.put("location", user.getLocation());
	        userDetails.put("country", user.getCountry());
	        userDetails.put("email", user.getEmail());
	        userDetails.put("password", user.getPassword());
	        userDetails.put("confirmPassword", user.getConfirmPassword());
	        userDetails.put("roleType", user.getRoleType());
	        userDetails.put("roleId", Long.valueOf(user.getRoleId()));
	        userDetails.put("mobileNumber", Long.valueOf(user.getMobileNumber()));
	        userDetails.put("status", Boolean.valueOf(user.isStatus()));
	        int randomNumber = generateRandomNumber();
	        String fileExtension = getFileExtensionForImage(user);
	        String imageUrl = "user/" + randomNumber + "/" + user.getUserId() + "." + fileExtension;
	        userDetails.put("imageUrl", imageUrl);
	        userDetailsWithImages.putAll(userDetails);
	      } 
	      return ResponseEntity.ok(userDetailsWithImages);
	    } 
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	  }
	  
	  @GetMapping({"/User"})
	  public ResponseEntity<List<User>> displayAllUsers(@RequestParam(required = true) String User) {
	    try {
	      if ("User".equals(User)) {
	        List<User> users = this.userService.singleUser();
	        List<User> userObjects = new ArrayList<>();
	        for (User user : users) {
	          int randomNumber = generateRandomNumber();
	          String fileExtension = getFileExtensionForImage(user);
	          String imageUrl = "user/" + randomNumber + "/" + user.getUserId() + "." + fileExtension;
	          User employeeObject = new User();
	          employeeObject.setUserId(user.getUserId());
	          employeeObject.setUrl(imageUrl);
	          employeeObject.setAddress(user.getAddress());
	          employeeObject.setCity(user.getCity());
	          employeeObject.setCountry(user.getCountry());
	          employeeObject.setMobileNumber(user.getMobileNumber());
	          employeeObject.setEmail(user.getEmail());
	          employeeObject.setPassword(user.getPassword());
	          employeeObject.setConfirmPassword(user.getConfirmPassword());
	          employeeObject.setRoleType(user.getRoleType());
	          employeeObject.setRoleId(user.getRoleId());
	          employeeObject.setUsername(user.getUsername());
	          employeeObject.setLocation(user.getLocation());
	          employeeObject.setStatus(user.isStatus());
	          userObjects.add(employeeObject);
	        } 
	        return ResponseEntity.ok().body(userObjects);
	      } 
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
	  
	  @GetMapping({"user/{randomNumber}/{id:.+}"})
	  public ResponseEntity<Resource> serveImage(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") String id) {
	    Long imageId;
	    byte[] imageBytes;
	    String[] parts = id.split("\\.");
	    if (parts.length != 2)
	      return ResponseEntity.badRequest().build(); 
	    String fileExtension = parts[1];
	    try {
	      imageId = Long.valueOf(Long.parseLong(parts[0]));
	    } catch (NumberFormatException e) {
	      return ResponseEntity.badRequest().build();
	    } 
	    User image = this.userService.findUserById(imageId);
	    if (image == null)
	      return ResponseEntity.notFound().build(); 
	    try {
	      imageBytes = image.getUserProfile().getBytes(1L, (int)image.getUserProfile().length());
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	    ByteArrayResource resource = new ByteArrayResource(imageBytes);
	    HttpHeaders headers = new HttpHeaders();
	    if ("jpg".equalsIgnoreCase(fileExtension)) {
	      headers.setContentType(MediaType.IMAGE_JPEG);
	    } else if ("png".equalsIgnoreCase(fileExtension)) {
	      headers.setContentType(MediaType.IMAGE_PNG);
	    } else {
	      headers.setContentType(MediaType.IMAGE_JPEG);
	    } 
	    return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	  }
	  
	  private String getFileExtensionForImage(User image) {
	    if (image == null || image.getUrl() == null || image.getUrl().isEmpty())
	      return "jpg"; 
	    String url = image.getUrl();
	    if (url.endsWith(".png"))
	      return "png"; 
	    if (url.endsWith(".jpg"))
	      return "jpg"; 
	    return "jpg";
	  }
	  
	  @PutMapping({"/user/edit/{userId}"})
	  public ResponseEntity<String> updateCompany(@PathVariable long userId, @RequestParam(value = "userProfile", required = false) MultipartFile file, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "address", required = false) String address, @RequestParam(value = "location", required = false) String location, @RequestParam(value = "country", required = false) String country, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "roleType", required = false) String roleType, @RequestParam(value = "mobileNumber", required = false) Long mobileNumber) {
	    try {
	      User user = this.userService.getCompanyById(Long.valueOf(userId));
	      if (user == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found."); 
	      if (file != null && !file.isEmpty()) {
	        byte[] bytes = file.getBytes();
	        Blob blob = new SerialBlob(bytes);
	        user.setUserProfile(blob);
	      } 
	      if (username != null)
	        user.setUsername(username); 
	      if (country != null)
	        user.setCountry(country); 
	      if (email != null)
	        user.setEmail(email); 
	      if (location != null)
	        user.setLocation(location); 
	      if (address != null)
	        user.setAddress(address); 
	      if (city != null)
	        user.setCity(city); 
	      if (roleType != null)
	        user.setRoleType(roleType); 
	      if (mobileNumber != null)
	        user.setMobileNumber(mobileNumber.longValue()); 
	      this.userService.saveSingleUser(user);
	      return ResponseEntity.ok("User updated successfully. user ID: " + userId);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user.");
	    } 
	  }
	  
	  @DeleteMapping({"/user/delete/{id}"})
	  public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long userId) {
	    this.userService.deleteUserById(userId);
	    return ResponseEntity.ok("User deleted successfully");
	  }
	  
	  @PostMapping({"/login"})
	  public ResponseEntity<?> processLogin(@RequestBody LoginRequest loginRequest) {
	    String email = loginRequest.getEmail();
	    String password = loginRequest.getPassword();
	    User user = this.userRepository.findByEmail(email);
	    AdminLogin admin = this.adminLoginRepository.findByEmail(email);
	    if (user != null && user.getPassword().equals(password)) {
	      Map<String, Object> userDetails = getUserDetails(user);
	      return ResponseEntity.ok(userDetails);
	    } 
	    if (admin != null && admin.getPassword().equals(password)) {
	      Map<String, Object> adminDetails = getAdminDetails(admin);
	      return ResponseEntity.ok(adminDetails);
	    } 
	   
	    Map<String, Object> loginFailedResponse = createLoginFailedResponse();
	    return ResponseEntity.badRequest().body(loginFailedResponse);
	  }
	  
	  private Map<String, Object> getUserDetails(User user) {
	    List<Map<String, Object>> userDetails = this.userRepository.getManagerDetailsById(Long.valueOf(user.getUserId()));
	    Map<String, List<Map<String, Object>>> userGroupMap = (Map<String, List<Map<String, Object>>>)userDetails.stream().collect(Collectors.groupingBy(action -> action.get("userId").toString()));
	    Map<String, Object> userDetailsMap = new HashMap<>();
	    for (Map.Entry<String, List<Map<String, Object>>> userLoop : userGroupMap.entrySet()) {
	      Map<String, Object> userMap = new HashMap<>();
	      int randomNumber = generateRandomNumber();
			String fileExtension = getFileExtensionForImage1(userLoop);
			String imageUrl = "user/" + randomNumber + "/" + userLoop.getValue().get(0).get("userId") + "."
					+ fileExtension;
	      userMap.put("id", userLoop.getKey());
	      userMap.put("userName", userLoop.getValue().get(0).get("username"));
	      userMap.put("roleId", userLoop.getValue().get(0).get("roleId"));
	      userMap.put("roleName", userLoop.getValue().get(0).get("roleName"));
	      userMap.put("profile", imageUrl);
	      userMap.put("token", "2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys");
	      userDetailsMap.putAll(userMap);
	    } 
	    return userDetailsMap;
	  }
	  
	  private Map<String, Object> getAdminDetails(AdminLogin admin) {
	    List<Map<String, Object>> adminDetails = this.userRepository.getAllAdminDetailsById(Long.valueOf(admin.getId()));
	    Map<String, Object> adminMainMap = new HashMap<>();
	    Map<String, List<Map<String, Object>>> adminGroupMap = (Map<String, List<Map<String, Object>>>)adminDetails.stream().collect(Collectors.groupingBy(action -> action.get("id").toString()));
	    for (Map.Entry<String, List<Map<String, Object>>> adminLoop : adminGroupMap.entrySet()) {
	      Map<String, Object> adminMap = new HashMap<>();
	      int randomNumber = generateRandomNumber();
			String imageUrl = "admin/" + randomNumber + "/" + adminLoop.getValue().get(0).get("id");
	      adminMap.put("id", adminLoop.getKey());
	      adminMap.put("userName", adminLoop.getValue().get(0).get("name"));
	      adminMap.put("roleId",adminLoop.getValue().get(0).get("role_id"));
	      adminMap.put("roleName", adminLoop.getValue().get(0).get("role_name"));
	      adminMap.put("roleType", adminLoop.getValue().get(0).get("role_type"));
	      adminMap.put("profile", imageUrl);
	      adminMap.put("token", "2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys");
	      adminMainMap.putAll(adminMap);
	    } 
	    return adminMainMap;
	  }
	  

	  private String getFileExtensionForImage1(Entry<String, List<Map<String, Object>>> employeeLoop) {
			if (employeeLoop == null || !employeeLoop.getValue().get(0).containsKey("url")
					|| employeeLoop.getValue().get(0).get("url") == null) {
				return "jpg";
			}
			String url = employeeLoop.getValue().get(0).get("url").toString();
			if (url.endsWith(".png")) {
				return "png";
			} else if (url.endsWith(".jpg")) {
				return "jpg";
			} else {
				return "jpg"; // You might want to handle other cases appropriately
			}
		}

	  
	  private Map<String, Object> createLoginFailedResponse() {
	    Map<String, Object> response = new HashMap<>();
	    Map<String, Object> adminMap = new HashMap<>();
	    adminMap.put("token", "2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYbhTrg789Iys");
	    adminMap.put("result", "Login Failed");
	    response.putAll(adminMap);
	    return response;
	  }
	  
	  @PostMapping({"/changepassword"})
	  public ResponseEntity<String> processChangePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
	    User user = this.userRepository.findByEmail(email);
	    AdminLogin admin = this.adminLoginRepository.findByEmail(email);
	    Employee employee = this.employeeRepository.findByEmail(email);
	    ClientProfile client = this.clientRepository.findByEmail(email);
	    if (user != null && user.getPassword().equals(oldPassword)) {
	      user.setPassword(newPassword);
	      this.userRepository.save(user);
	      return ResponseEntity.ok("Password changed successfully");
	    } 
	    if (admin != null && admin.getPassword().equals(oldPassword)) {
	      admin.setPassword(newPassword);
	      this.adminLoginRepository.save(admin);
	      return ResponseEntity.ok("Password changed successfully");
	    } 
	    if (employee != null && employee.getPassword().equals(oldPassword)) {
	      employee.setPassword(newPassword);
	      this.employeeRepository.save(employee);
	      return ResponseEntity.ok("Password changed successfully");
	    } 
	    if (client != null && client.getPassword().equals(oldPassword)) {
	      client.setPassword(newPassword);
	      this.clientRepository.save(client);
	      return ResponseEntity.ok("Password changed successfully");
	    } 
	    return ResponseEntity.badRequest().body("Invalid credentials");
	  }
	  
	  @GetMapping({"/user/withrole"})
	  public ResponseEntity<Object> getManagerDetils(@RequestParam(required = true) String user) {
	    if ("manager".equals(user))
	      return ResponseEntity.ok(this.userRepository.getManagerDetails()); 
	    String errorMessage = "Invalid value for 'user'. Expected 'manager'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @GetMapping("/logout/{id}/{roleId}/{roleName}")
		public ResponseEntity<Map<String, Object>> getManagerDetailsById(@PathVariable long id, @PathVariable long roleId,
				@PathVariable String roleName) {
			Map<String, Object> result;
			switch (roleName.toLowerCase()) {	
			case "manager":
				result = userRepository.getManagerDetailsById2(id, roleId);
				break;		
			default:
				return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid entity type"));
			}
			return ResponseEntity.ok(result);
		}
	}
