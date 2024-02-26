package com.example.HRM.controller.employee;

import com.example.HRM.entity.employee.Bank;
import com.example.HRM.entity.employee.EmergencyContacts;
import com.example.HRM.entity.employee.Employee;
import com.example.HRM.entity.employee.FamilyInformations;
import com.example.HRM.entity.employee.Personal;
import com.example.HRM.entity.employee.Qualification;
import com.example.HRM.entity.payroll.SalaryTypeList;
import com.example.HRM.repository.employee.BankRepository;
import com.example.HRM.repository.employee.EmployeeRepository;
import com.example.HRM.repository.payroll.SalaryTypeListRepository;
import com.example.HRM.service.employee.BankService;
import com.example.HRM.service.employee.EmergencyContactsService;
import com.example.HRM.service.employee.EmployeeService;
import com.example.HRM.service.employee.FamilyInformationsService;
import com.example.HRM.service.employee.PersonalService;
import com.example.HRM.service.employee.QualificationService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class EmployeeController {
	 @Autowired
	  private EmployeeService employeeService;
	  
	  @Autowired
	  private EmployeeRepository employeeRepository;
	  
	  @Autowired
	  private BankService bankService;
	  
	  @Autowired
	  private BankRepository bankRepository;
	  
	  @Autowired
	  private PersonalService personalService;
	  
	  @Autowired
	  private EmergencyContactsService emergencyContactsService;
	  
	  @Autowired
	  private FamilyInformationsService familyInformationsService;
	  
	  @Autowired
	  private QualificationService qualificationService;
	  
	  @Autowired
	  private SalaryTypeListRepository salaryTypeListRepository;
	  
	  @GetMapping({"/count/{employeeId}"})
	  public ResponseEntity<Map<String, List<Map<String, Object>>>> getAllDataBetweenDates(@PathVariable long employeeId) {
	    Map<String, List<Map<String, Object>>> resultMap = new HashMap<>();
	    List<Map<String, Object>> employeeResponses = new ArrayList<>();
	    List<Map<String, Object>> employeeDetails = this.bankRepository.getAllRoleByEmployees3(Long.valueOf(employeeId));
	    for (Map<String, Object> employeeDetail : employeeDetails) {
	      int randomNumber = generateRandomNumber();
	      String fileExtension = getFileExtensionForImage(employeeDetail);
	      String imageUrl = "profile/" + randomNumber + "/" + employeeDetail.get("employee_id") + "." + fileExtension;
	      Map<String, Object> employeeResponse = new HashMap<>();
	      employeeResponse.put("employeeId", employeeDetail.get("employee_id"));
	      employeeResponse.put("address", employeeDetail.get("address"));
	      employeeResponse.put("city", employeeDetail.get("city"));
	      employeeResponse.put("country", employeeDetail.get("country"));
	      employeeResponse.put("date", employeeDetail.get("date"));
	      employeeResponse.put("departmentId", employeeDetail.get("department_id"));
	      employeeResponse.put("description", employeeDetail.get("description"));
	      employeeResponse.put("designationId", employeeDetail.get("designation_id"));
	      employeeResponse.put("dob", employeeDetail.get("dob"));
	      employeeResponse.put("email", employeeDetail.get("email"));
	      employeeResponse.put("gender", employeeDetail.get("gender"));
	      employeeResponse.put("password", employeeDetail.get("password"));
	      employeeResponse.put("phoneNumber", employeeDetail.get("phone_number"));
	      employeeResponse.put("roleId", employeeDetail.get("role_id"));
	      employeeResponse.put("state", employeeDetail.get("state"));
	      employeeResponse.put("status", employeeDetail.get("status"));
	      employeeResponse.put("departmentName", employeeDetail.get("department_name"));
	      employeeResponse.put("userName", employeeDetail.get("user_name"));
	      employeeResponse.put("userId", employeeDetail.get("user_id"));
	      employeeResponse.put("profile", imageUrl);
	      employeeResponses.add(employeeResponse);
	    } 
	    resultMap.put("employee", employeeResponses);
	    resultMap.put("bank", this.bankRepository.getAllBank(Long.valueOf(employeeId)));
	    resultMap.put("personal", this.bankRepository.getAllPersonal(Long.valueOf(employeeId)));
	    resultMap.put("contacts", this.bankRepository.getAllEmergencyContacts(Long.valueOf(employeeId)));
	    resultMap.put("familyInformations", this.bankRepository.getAllFamilyInformations(Long.valueOf(employeeId)));
	    resultMap.put("qualification", this.bankRepository.getQualifications(Long.valueOf(employeeId)));
	    List<Map<String, Object>> qualifications = this.bankRepository.getQualifications(Long.valueOf(employeeId));
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
	      String resumeUrl = "/resumeUrl/" + resumeRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	      String tenUrl = "/tenUrl/" + tenRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	      String aadharUrl = "/aadharUrl/" + aadharRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	      String degreeUrl = "/degreeUrl/" + degreeRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	      String pannoUrl = "/pannoUrl/" + pannoRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	      String bankBookUrl = "/bankBookUrl/" + bankBookRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
	      String twelveUrl = "/twelveUrl/" + twelveRandomNumber + "/" + qualification.getValue().get(0).get("qualification_id");
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
	    resultMap.put("qualification", qualificationResponses);
	    return ResponseEntity.ok(resultMap);
	  }
	  
	  @GetMapping({"/employees"})
	  public ResponseEntity<List<Employee>> displayAllImages(@RequestParam(required = true) String employeesParam) {
	    try {
	      if ("employees".equals(employeesParam)) {
	        List<Employee> employees = this.employeeService.listAll1();
	        List<Employee> employeeObjects = new ArrayList<>();
	        for (Employee employee : employees) {
	          int randomNumber = generateRandomNumber();
	          String fileExtension = getFileExtensionForImage(employee);
	          String imageUrl = "profile/" + randomNumber + "/" + employee.getEmployeeId() + "." + fileExtension;
	          Employee employeeObject = new Employee();
	          employeeObject.setEmployeeId(employee.getEmployeeId());
	          employeeObject.setUrl(imageUrl);
	          employeeObject.setAddress(employee.getAddress());
	          employeeObject.setCity(employee.getCity());
	          employeeObject.setCountry(employee.getCountry());
	          employeeObject.setPhoneNumber(employee.getPhoneNumber());
	          employeeObject.setDescription(employee.getDescription());
	          employeeObject.setEmail(employee.getEmail());
	          employeeObject.setPassword(employee.getPassword());
	          employeeObject.setDesignationId(employee.getDesignationId());
	          employeeObject.setGender(employee.getGender());
	          employeeObject.setDob(employee.getDob());
	          employeeObject.setState(employee.getState());
	          employeeObject.setUserName(employee.getUserName());
	          employeeObject.setDepartmentId(employee.getDepartmentId());
	          employeeObject.setRoleId(employee.getRoleId());
	          employeeObject.setStatus(employee.isStatus());
	          employeeObject.setDate(employee.getDate());
	          employeeObject.setUserId(employee.getUserId());
	          employeeObject.setAttendanceType(employee.getAttendanceType());
	          employeeObject.setShiftId(employee.getShiftId());
	          employeeObject.setShiftTypeId(employee.getShiftTypeId());
	          employeeObject.setRoleType(employee.getRoleType());
	          employeeObjects.add(employeeObject);
	        } 
	        return ResponseEntity.ok().body(employeeObjects);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @GetMapping({"/employees/shift"})
	  public ResponseEntity<?> getAllShiftType(@RequestParam(required = true) String employeesParam, @RequestParam(required = true) String attendanceParam,
			  @RequestParam(required = false) Long shiftId) {
	    try {
	      if ("employees".equals(employeesParam)) {
	        if ("Regular".equals(attendanceParam)) {
	          List<Map<String, Object>> employeeShift = new ArrayList<>();
	          List<Map<String, Object>> shiftDetails = this.employeeRepository.getAllShiftTypeDetails();
	          Map<String, List<Map<String, Object>>> shiftGroupMap = (Map<String, List<Map<String, Object>>>)shiftDetails.stream().collect(Collectors.groupingBy(action -> action.get("shift_type_id").toString()));
	          for (Map.Entry<String, List<Map<String, Object>>> shiftLoop : shiftGroupMap.entrySet()) {
	            Map<String, Object> shiftMap = new HashMap<>();
	            shiftMap.put("shiftTypeId", shiftLoop.getKey());
	            shiftMap.put("shiftName", shiftLoop.getValue().get(0).get("shift_name"));
	            List<Map<String, Object>> empShiftTypeList = new ArrayList<>();
	            for (Map<String, Object> shiftLoop1 : shiftLoop.getValue()) {
	              Map<String, Object> shiftEmpTypeMap = new HashMap<>();
	              shiftEmpTypeMap.put("userName", shiftLoop1.get("user_name"));
	              shiftEmpTypeMap.put("employeeId", shiftLoop1.get("employee_id"));
	              shiftEmpTypeMap.put("gender", shiftLoop1.get("gender"));
	              shiftEmpTypeMap.put("phoneNumber", shiftLoop1.get("phone_number"));
	              shiftEmpTypeMap.put("roleType", shiftLoop1.get("role_type"));
	              shiftEmpTypeMap.put("designationName", shiftLoop1.get("designation_name"));
	              shiftEmpTypeMap.put("departmentName", shiftLoop1.get("department_name"));
	              empShiftTypeList.add(shiftEmpTypeMap);
	            } 
	            shiftMap.put("attendanceDetails", empShiftTypeList);
	            employeeShift.add(shiftMap);
	          } 
	          return ResponseEntity.ok().body(employeeShift);
	        } 
	        if ("Shift".equals(attendanceParam)) {
	          List<Map<String, Object>> employeeShift = new ArrayList<>();
	          List<Map<String, Object>> shiftDetails = this.employeeRepository.getAllshiftAndShiftTypeDetails(shiftId);
	          Map<String, List<Map<String, Object>>> shiftGroupMap = (Map<String, List<Map<String, Object>>>)shiftDetails.stream().collect(Collectors.groupingBy(action -> action.get("shift_type_id").toString()));
	          for (Map.Entry<String, List<Map<String, Object>>> shiftLoop : shiftGroupMap.entrySet()) {
	            Map<String, Object> shiftMap = new HashMap<>();
	            shiftMap.put("shiftTypeId", shiftLoop.getKey());
	            shiftMap.put("shiftName",shiftLoop.getValue().get(0).get("shift_name"));
	            List<Map<String, Object>> empShiftList = new ArrayList<>();
	            for (Map<String, Object> shiftLoop1 : shiftLoop.getValue()) {
	              Map<String, Object> shiftTypeMap = new HashMap<>();
	              shiftTypeMap.put("shiftId", shiftLoop1.get("shift_id"));
	              shiftTypeMap.put("shiftType", shiftLoop1.get("shift_type"));
	              shiftTypeMap.put("inTime", shiftLoop1.get("in_time"));
	              shiftTypeMap.put("outTime", shiftLoop1.get("out_time"));
	              
	              
	              shiftTypeMap.put("userName", shiftLoop1.get("user_name"));
	              shiftTypeMap.put("employeeId", shiftLoop1.get("employee_id"));
	              shiftTypeMap.put("gender", shiftLoop1.get("gender"));
	              shiftTypeMap.put("phoneNumber", shiftLoop1.get("phone_number"));
	              shiftTypeMap.put("roleType", shiftLoop1.get("role_type"));
	              shiftTypeMap.put("designationName", shiftLoop1.get("designation_name"));
	              shiftTypeMap.put("departmentName", shiftLoop1.get("department_name"));
	              
	              List<Map<String, Object>> empShiftTypeList = new ArrayList<>();
	              Map<String, Object> shiftEmpTypeMap = new HashMap<>();
	              shiftEmpTypeMap.put("userName", shiftLoop1.get("user_name"));
	              shiftEmpTypeMap.put("employeeId", shiftLoop1.get("employee_id"));
	              shiftEmpTypeMap.put("gender", shiftLoop1.get("gender"));
	              shiftEmpTypeMap.put("phoneNumber", shiftLoop1.get("phone_number"));
	              shiftEmpTypeMap.put("roleType", shiftLoop1.get("role_type"));
	              shiftEmpTypeMap.put("designationName", shiftLoop1.get("designation_name"));
	              shiftEmpTypeMap.put("departmentName", shiftLoop1.get("department_name"));
	              empShiftTypeList.add(shiftEmpTypeMap);
	              shiftTypeMap.put("shiftTypeDetails", empShiftTypeList);
	              empShiftList.add(shiftTypeMap);
	            } 
	            shiftMap.put("attendanceType", empShiftList);
	            employeeShift.add(shiftMap);
	          } 
	          return ResponseEntity.ok().body(employeeShift);
	        } 
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
	  
	  @GetMapping({"profile/{randomNumber}/{id:.+}"})
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
	    Employee image = this.employeeService.getById(imageId.longValue());
	    if (image == null)
	      return ResponseEntity.notFound().build(); 
	    try {
	      imageBytes = image.getProfile().getBytes(1L, (int)image.getProfile().length());
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
	  
	  private String getFileExtensionForImage(Employee image) {
	    if (image == null || image.getUrl() == null || image.getUrl().isEmpty())
	      return "jpg"; 
	    String url = image.getUrl();
	    if (url.endsWith(".png"))
	      return "png"; 
	    if (url.endsWith(".jpg"))
	      return "jpg"; 
	    return "jpg";
	  }
	  
	  @PostMapping({"/employees/save"})
	  public ResponseEntity<String> addEmployeeWithImage(@RequestParam("profile") MultipartFile file, @RequestParam("userName") String userName, @RequestParam("gender") String gender, @RequestParam("country") String country, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("state") String state, @RequestParam("city") String city, @RequestParam("address") String address, @RequestParam("roleType") String roleType, @RequestParam("dob") Date dob, @RequestParam("description") String description, @RequestParam("designationId") long designationId, @RequestParam("departmentId") Long departmentId, @RequestParam("phoneNumber") long phoneNumber, @RequestParam("shiftId") long shiftId, @RequestParam("attendanceType") String attendanceType) {
	    try {
	      byte[] bytes = file.getBytes();
	      Blob blob = new SerialBlob(bytes);
	      Employee employee = new Employee();
	      employee.setRoleType(roleType);
	      employee.setProfile(blob);
	      employee.setStatus(true);
	      employee.setUserName(userName);
	      employee.setGender(gender);
	      employee.setCountry(country);
	      employee.setEmail(email);
	      employee.setPassword(password);
	      employee.setState(state);
	      employee.setCity(city);
	      employee.setAddress(address);
	      employee.setDob(dob);
	      employee.setDescription(description);
	      employee.setDesignationId(designationId);
	      employee.setDepartmentId(departmentId);
	      employee.setPhoneNumber(phoneNumber);
	      employee.setAttendanceType(attendanceType);
	      employee.setShiftId(shiftId);
	      if ("Employee".equals(employee.getRoleType())) {
	        employee.setRoleId(4);
	      } else if ("ProjectHead".equals(employee.getRoleType())) {
	        employee.setRoleId(6);
	      } else if ("TL".equals(employee.getRoleType())) {
	        employee.setRoleId(7);
	      } else {
	        employee.setRoleId(0L);
	      } 
	      if ("Regular".equals(employee.getAttendanceType())) {
	        employee.setShiftTypeId(1L);
	        employee.setShiftId(0L);
	      } else if ("Shift".equals(employee.getAttendanceType())) {
	        employee.setShiftTypeId(2L);
	      } else {
	        employee.setShiftTypeId(0L);
	      } 
	      this.employeeService.saveOrUpdate(employee);
	      long id = employee.getEmployeeId();
	      String userId = String.format("UID%04d", new Object[] { Long.valueOf(id) });
	      employee.setUserId(userId);
	      Bank bank = new Bank();
	      bank.setEmployeeId(Long.valueOf(id));
	      this.bankService.save(bank);
	      Personal personal = new Personal();
	      personal.setEmployeeId(id);
	      this.personalService.SaveorUpdate(personal);
	      EmergencyContacts contact = new EmergencyContacts();
	      contact.setEmployeeId(Long.valueOf(id));
	      this.emergencyContactsService.saveOrUpdate(contact);
	      FamilyInformations family = new FamilyInformations();
	      family.setEmployeeId(Long.valueOf(id));
	      this.familyInformationsService.SaveorUpdate(family);
	      Qualification qualification = new Qualification();
	      qualification.setEmployeeId(id);
	      this.qualificationService.update(qualification);
	      return ResponseEntity.ok("Employee added successfully. Employee ID: " + id);
	    } catch (IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file");
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with SQL operation");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred");
	    } 
	  }
	  
	  @PutMapping({"/employees/ststus/{id}"})
	  public ResponseEntity<Boolean> toggleComplaintsStatus(@PathVariable(name = "id") long id) {
	    try {
	      Employee complaints = this.employeeService.getById(id);
	      if (complaints != null) {
	        boolean currentStatus = complaints.isStatus();
	        complaints.setStatus(!currentStatus);
	        this.employeeService.saveOrUpdate(complaints);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(complaints.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/employees/edit/{employeeId}"})
	  public ResponseEntity<String> updateEmployee(@PathVariable long employeeId, @RequestParam(value = "profile", required = false) MultipartFile file, @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "gender", required = false) String gender, @RequestParam(value = "country", required = false) String country, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "state", required = false) String state, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "address", required = false) String address, @RequestParam(value = "dob", required = false) Date dob, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "designationId", required = false) Long designationId, @RequestParam(value = "departmentId", required = false) Long departmentId, @RequestParam(value = "roleId", required = false) Long roleId, @RequestParam(value = "phoneNumber", required = false) Long phoneNumber, @RequestParam(value = "shiftId", required = false) Long shiftId, @RequestParam(value = "attendanceType", required = false) String attendanceType) {
	    try {
	      Employee employee = this.employeeService.getEmployeeById(Long.valueOf(employeeId));
	      if (employee == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found."); 
	      if (file != null) {
	        byte[] bytes = file.getBytes();
	        Blob blob = new SerialBlob(bytes);
	        employee.setProfile(blob);
	      } 
	      if (userName != null)
	        employee.setUserName(userName); 
	      if (gender != null)
	        employee.setGender(gender); 
	      if (country != null)
	        employee.setCountry(country); 
	      if (email != null)
	        employee.setEmail(email); 
	      if (password != null)
	        employee.setPassword(password); 
	      if (state != null)
	        employee.setState(state); 
	      if (city != null)
	        employee.setCity(city); 
	      if (address != null)
	        employee.setAddress(address); 
	      if (dob != null)
	        employee.setDob(dob); 
	      if (description != null)
	        employee.setDescription(description); 
	      if (designationId != null)
	        employee.setDesignationId(designationId.longValue()); 
	      if (departmentId != null)
	        employee.setDepartmentId(departmentId); 
	      if (roleId != null)
	        employee.setRoleId(roleId.longValue()); 
	      if (phoneNumber != null)
	        employee.setPhoneNumber(phoneNumber.longValue()); 
	      if (shiftId != null)
	        employee.setShiftId(shiftId.longValue()); 
	      if (attendanceType != null)
	        employee.setAttendanceType(attendanceType); 
	      if ("Regular".equals(employee.getAttendanceType())) {
	        employee.setShiftTypeId(1L);
	        employee.setShiftId(0L);
	      } else if ("Shift".equals(employee.getAttendanceType())) {
	        employee.setShiftTypeId(2L);
	      } else {
	        employee.setShiftTypeId(0L);
	      } 
	      this.employeeService.saveOrUpdate(employee);
	      return ResponseEntity.ok("Employee updated successfully. Employee ID: " + employeeId);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee.");
	    } 
	  }
	  
	  @DeleteMapping({"/employees/delete/{id}"})
	  public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
	    try {
	      this.employeeService.deleteById(id);
	      return ResponseEntity.ok("Employee deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting Employee: " + e.getMessage());
	    } 
	  }
	  
	  private String getFileExtensionForImage(Map<String, Object> employeeDetail) {
	    if (employeeDetail == null || !employeeDetail.containsKey("url") || employeeDetail.get("url") == null)
	      return "jpg"; 
	    String url = (String)employeeDetail.get("url");
	    if (url.endsWith(".png"))
	      return "png"; 
	    if (url.endsWith(".jpg"))
	      return "jpg"; 
	    return "jpg";
	  }
	  
	  @GetMapping({"/employees/view"})
	  public ResponseEntity<List<Map<String, Object>>> displayAllEmployeesWithDetails(@RequestParam(required = true) String employeesview) {
	    try {
	      if ("employeesview".equals(employeesview)) {
	        List<Map<String, Object>> employeeDetails = this.employeeRepository.getAllEmployeesWithDetails();
	        List<Map<String, Object>> employeeResponses = new ArrayList<>();
	        for (Map<String, Object> employeeDetail : employeeDetails) {
	          int randomNumber = generateRandomNumber();
	          String fileExtension = getFileExtensionForImage(employeeDetail);
	          String imageUrl = "profile/" + randomNumber + "/" + employeeDetail.get("employee_id") + "." + fileExtension;
	          Map<String, Object> employeeResponse = new HashMap<>();
	          employeeResponse.put("employeeId", employeeDetail.get("employee_id"));
	          employeeResponse.put("address", employeeDetail.get("address"));
	          employeeResponse.put("city", employeeDetail.get("city"));
	          employeeResponse.put("country", employeeDetail.get("country"));
	          employeeResponse.put("date", employeeDetail.get("date"));
	          employeeResponse.put("departmentId", employeeDetail.get("department_id"));
	          employeeResponse.put("description", employeeDetail.get("description"));
	          employeeResponse.put("designationId", employeeDetail.get("designation_id"));
	          employeeResponse.put("dob", employeeDetail.get("dob"));
	          employeeResponse.put("email", employeeDetail.get("email"));
	          employeeResponse.put("gender", employeeDetail.get("gender"));
	          employeeResponse.put("password", employeeDetail.get("password"));
	          employeeResponse.put("phoneNumber", employeeDetail.get("phone_number"));
	          employeeResponse.put("roleId", employeeDetail.get("role_id"));
	          employeeResponse.put("roleName", employeeDetail.get("role_name"));
	          employeeResponse.put("state", employeeDetail.get("state"));
	          employeeResponse.put("status", employeeDetail.get("status"));
	          employeeResponse.put("departmentName", employeeDetail.get("department_name"));
	          employeeResponse.put("userName", employeeDetail.get("user_name"));
	          employeeResponse.put("designationName", employeeDetail.get("designation_name"));
	          employeeResponse.put("userId", employeeDetail.get("user_id"));
	          employeeResponse.put("profile", imageUrl);
	          employeeResponse.put("shiftId", employeeDetail.get("shift_id"));
	          employeeResponse.put("shiftType", employeeDetail.get("shift_type"));
	          employeeResponse.put("inTime", employeeDetail.get("in_time"));
	          employeeResponse.put("outTime", employeeDetail.get("out_time"));
	          employeeResponse.put("attendanceType", employeeDetail.get("attendance_type"));
	          employeeResponses.add(employeeResponse);
	        } 
	        return ResponseEntity.ok(employeeResponses);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @GetMapping({"/employees/other"})
	  public List<Map<String, Object>> getAllReg(@RequestParam(required = true) String employees) {
	    try {
	      if ("other".equalsIgnoreCase(employees))
	        return this.employeeRepository.ALLOver(); 
	      throw new IllegalArgumentException("Invalid parameter value. 'employees' must be 'resignations'.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/employees/year/count"})
	  public List<Map<String, Object>> getAllcount(@RequestParam(required = true) String employees) {
	    try {
	      if ("count".equalsIgnoreCase(employees))
	        return this.employeeRepository.ALLCount(); 
	      throw new IllegalArgumentException("Invalid parameter value. 'employees' must be 'resignations'.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/employees/department"})
	  public List<Map<String, Object>> getDeparment(@RequestParam(required = true) String employees) {
	    try {
	      if ("department".equalsIgnoreCase(employees))
	        return this.employeeRepository.ALLDepatment(); 
	      throw new IllegalArgumentException("Invalid parameter value. 'employees' must be '.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/notifications"})
	  public List<Map<String, Object>> getnoti(@RequestParam(required = true) String employees) {
	    try {
	      if ("Notifications".equalsIgnoreCase(employees))
	        return this.employeeRepository.AllNotifications(); 
	      throw new IllegalArgumentException("Invalid parameter value. 'employees' must be 'resignations'.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/notifications1"})
	  public List<Map<String, Object>> getnoti1(@RequestParam(required = true) String employees1) {
	    try {
	      if ("Notifications".equalsIgnoreCase(employees1))
	        return this.employeeRepository.AllNotifications1(); 
	      throw new IllegalArgumentException("Invalid parameter value. 'employees' must be 'resignations'.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/employees/view/{id}"})
	  public ResponseEntity<Map<String, Object>> displayAllEmployeesWithDetailsWithId(@PathVariable("id") Long employee_id) {
	    try {
	      List<Map<String, Object>> employeeDetails = this.employeeRepository.getAllEmployeesWithDetailsWithId(employee_id);
	      Map<String, Object> employeeResponses = new HashMap<>();
	      for (Map<String, Object> employeeDetail : employeeDetails) {
	        int randomNumber = generateRandomNumber();
	        String fileExtension = getFileExtensionForImage(employeeDetail);
	        String imageUrl = "profile/" + randomNumber + "/" + employeeDetail.get("employee_id") + "." + fileExtension;
	        Map<String, Object> employeeResponse = new HashMap<>();
	        employeeResponse.put("employeeId", employeeDetail.get("employee_id"));
	        employeeResponse.put("address", employeeDetail.get("address"));
	        employeeResponse.put("city", employeeDetail.get("city"));
	        employeeResponse.put("country", employeeDetail.get("country"));
	        employeeResponse.put("date", employeeDetail.get("date"));
	        employeeResponse.put("departmentId", employeeDetail.get("department_id"));
	        employeeResponse.put("description", employeeDetail.get("description"));
	        employeeResponse.put("designationId", employeeDetail.get("designation_id"));
	        employeeResponse.put("dob", employeeDetail.get("dob"));
	        employeeResponse.put("email", employeeDetail.get("email"));
	        employeeResponse.put("gender", employeeDetail.get("gender"));
	        employeeResponse.put("password", employeeDetail.get("password"));
	        employeeResponse.put("phoneNumber", employeeDetail.get("phone_number"));
	        employeeResponse.put("roleId", employeeDetail.get("role_id"));
	        employeeResponse.put("roleName", employeeDetail.get("role_name"));
	        employeeResponse.put("state", employeeDetail.get("state"));
	        employeeResponse.put("status", employeeDetail.get("status"));
	        employeeResponse.put("departmentName", employeeDetail.get("department_name"));
	        employeeResponse.put("userName", employeeDetail.get("user_name"));
	        employeeResponse.put("designationName", employeeDetail.get("designation_name"));
	        employeeResponse.put("userId", employeeDetail.get("user_id"));
	        employeeResponse.put("shiftId", employeeDetail.get("shift_id"));
	        employeeResponse.put("shiftType", employeeDetail.get("shift_type"));
	        employeeResponse.put("inTime", employeeDetail.get("in_time"));
	        employeeResponse.put("outTime", employeeDetail.get("out_time"));
	        employeeResponse.put("attendanceType", employeeDetail.get("attendance_type"));
	        employeeResponse.put("profile", imageUrl);
	        employeeResponses.putAll(employeeResponse);
	      } 
	      return ResponseEntity.ok(employeeResponses);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @GetMapping({"/employees/true"})
	  public ResponseEntity<List<Map<String, Object>>> displayAllImages2(@RequestParam(required = true) String trueParam) {
	    try {
	      if ("true".equals(trueParam)) {
	        List<Map<String, Object>> employeeDetails = this.employeeRepository.AllEmployees();
	        List<Map<String, Object>> employeeResponses = new ArrayList<>();
	        for (Map<String, Object> employeeDetail : employeeDetails) {
	          int randomNumber = generateRandomNumber();
	          String fileExtension = getFileExtensionForImage(employeeDetail);
	          String imageUrl = "profile/" + randomNumber + "/" + employeeDetail.get("employee_id") + "." + fileExtension;
	          Map<String, Object> employeeResponse = new HashMap<>();
	          employeeResponse.put("employeeId", employeeDetail.get("employee_id"));
	          employeeResponse.put("address", employeeDetail.get("address"));
	          employeeResponse.put("city", employeeDetail.get("city"));
	          employeeResponse.put("country", employeeDetail.get("country"));
	          employeeResponse.put("date", employeeDetail.get("date"));
	          employeeResponse.put("departmentId", employeeDetail.get("department_id"));
	          employeeResponse.put("description", employeeDetail.get("description"));
	          employeeResponse.put("designationId", employeeDetail.get("designation_id"));
	          employeeResponse.put("dob", employeeDetail.get("dob"));
	          employeeResponse.put("email", employeeDetail.get("email"));
	          employeeResponse.put("gender", employeeDetail.get("gender"));
	          employeeResponse.put("password", employeeDetail.get("password"));
	          employeeResponse.put("phoneNumber", employeeDetail.get("phone_number"));
	          employeeResponse.put("roleId", employeeDetail.get("role_id"));
	          employeeResponse.put("state", employeeDetail.get("state"));
	          employeeResponse.put("status", employeeDetail.get("status"));
	          employeeResponse.put("departmentName", employeeDetail.get("department_name"));
	          employeeResponse.put("userName", employeeDetail.get("user_name"));
	          employeeResponse.put("userId", employeeDetail.get("user_id"));
	          employeeResponse.put("shiftId", employeeDetail.get("shift_id"));
	          employeeResponse.put("shiftType", employeeDetail.get("shift_type"));
	          employeeResponse.put("inTime", employeeDetail.get("in_time"));
	          employeeResponse.put("outTime", employeeDetail.get("out_time"));
	          employeeResponse.put("attendanceType", employeeDetail.get("attendance_type"));
	          employeeResponse.put("profile", imageUrl);
	          employeeResponses.add(employeeResponse);
	        } 
	        return ResponseEntity.ok(employeeResponses);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PostMapping({"/employee/login"})
	  public ResponseEntity<String> processLogin(@RequestParam String email, @RequestParam String password) {
	    Employee employee = this.employeeRepository.findByEmail(email);
	    if (employee != null && employee.getPassword().equals(password))
	      return ResponseEntity.ok("Login successful"); 
	    return ResponseEntity.badRequest().body("Invalid Email or Password");
	  }
	  
	  @PostMapping({"/employee/changepassword"})
	  public ResponseEntity<String> processChangePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
	    Employee user = this.employeeRepository.findByEmail(email);
	    if (user != null && user.getPassword().equals(oldPassword)) {
	      user.setPassword(newPassword);
	      this.employeeRepository.save(user);
	      return ResponseEntity.ok("Password changed successfully");
	    } 
	    return ResponseEntity.badRequest().body("Invalid credentials");
	  }
	  
	  @GetMapping({"/department/role"})
	  public ResponseEntity<Object> getAllRoleByEmployee(@RequestParam(required = true) String department) {
	    if ("Department".equals(department)) {
	      List<Map<String, Object>> departmentList = new ArrayList<>();
	      List<Map<String, Object>> departmentRole = this.employeeRepository.getAllRoleByEmployees();
	      Map<String, List<Map<String, Object>>> departmentGroupMap = (Map<String, List<Map<String, Object>>>)departmentRole.stream().collect(Collectors.groupingBy(action -> action.get("department_id").toString()));
	      List<SalaryTypeList> salaryTypeList = this.salaryTypeListRepository.findAll();
	      Set<Long> employeesWithSalary = (Set<Long>)salaryTypeList.stream().map(SalaryTypeList::getEmployeeId).collect(Collectors.toSet());
	      for (Map.Entry<String, List<Map<String, Object>>> departmentLoop : departmentGroupMap.entrySet()) {
	        String departmentId = departmentLoop.getKey();
	        List<Map<String, Object>> departmentDetails = departmentLoop.getValue();
	        List<Map<String, Object>> filteredDepartmentDetails = (List<Map<String, Object>>)departmentDetails.stream().filter(employee -> !employeesWithSalary.contains(Long.valueOf(Long.parseLong(employee.get("employee_id").toString())))).collect(Collectors.toList());
	        Map<String, Object> departmentMap = new HashMap<>();
	        departmentMap.put("department_id", departmentId);
	        departmentMap.put("department_name",departmentLoop.getValue().get(0).get("department_name"));
	        departmentMap.put("department_details", filteredDepartmentDetails);
	        departmentList.add(departmentMap);
	      } 
	      return ResponseEntity.ok(departmentList);
	    } 
	    String errorMessage = "Invalid value for 'department'. Expected 'Department'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @GetMapping({"/department/employees/{departmentId}"})
	  public ResponseEntity<Object> getAllEmployeesByDepartment(@PathVariable long departmentId) {
	    try {
	      List<Map<String, Object>> departmentList = new ArrayList<>();
	      List<Map<String, Object>> departmentEmployees = new ArrayList<>(this.employeeRepository.getAllEmployeesByDepartment(Long.valueOf(departmentId)));
	      Map<String, List<Map<String, Object>>> departmentGroupMap = (Map<String, List<Map<String, Object>>>)departmentEmployees.stream().collect(Collectors.groupingBy(action -> action.get("department_id").toString()));
	      for (Map.Entry<String, List<Map<String, Object>>> departmentLoop : departmentGroupMap.entrySet()) {
	        Map<String, Object> departmentMap = new HashMap<>();
	        departmentMap.put("department_id", departmentLoop.getKey());
	        departmentMap.put("department_name",departmentLoop.getValue().get(0).get("department_name"));
	        List<Map<String, Object>> employeeResponses = new ArrayList<>();
	        for (Map<String, Object> employeeDetail : departmentLoop.getValue()) {
	          int randomNumber = generateRandomNumber();
	          String fileExtension = getFileExtensionForImage(employeeDetail);
	          String imageUrl = "profile/" + randomNumber + "/" + employeeDetail.get("employee_id") + "." + fileExtension;
	          Map<String, Object> updatedEmployeeDetail = new HashMap<>(employeeDetail);
	          updatedEmployeeDetail.put("profile", imageUrl);
	          employeeResponses.add(updatedEmployeeDetail);
	        } 
	        departmentMap.put("department_details", employeeResponses);
	        departmentList.add(departmentMap);
	      } 
	      return ResponseEntity.ok(departmentList);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
		@GetMapping("/department/role/filter")
		public ResponseEntity<Object> getAllRoleByEmployeeFilterDD(@RequestParam(required = true) String department) {
			if ("Department".equals(department)) {
				List<Map<String, Object>> departmentList = new ArrayList<>();
				List<Map<String, Object>> departmentRole = employeeRepository.getAllRoleByEmployees();
				Map<String, List<Map<String, Object>>> departmentGroupMap = departmentRole.stream()
						.collect(Collectors.groupingBy(action -> action.get("department_id").toString()));

				List<SalaryTypeList> salaryTypeList = salaryTypeListRepository.findAll();

				Set<Long> employeesWithSalary = salaryTypeList.stream().map(SalaryTypeList::getEmployeeId)
						.collect(Collectors.toSet());

				for (Entry<String, List<Map<String, Object>>> departmentLoop : departmentGroupMap.entrySet()) {
					String departmentId = departmentLoop.getKey();
					List<Map<String, Object>> departmentDetails = departmentLoop.getValue();

					List<Map<String, Object>> filteredDepartmentDetails = departmentDetails.stream()
							.filter(employee -> !employeesWithSalary
									.contains(Long.parseLong(employee.get("employee_id").toString())))
							.map(employee -> {
								int employeeRandomNumber = generateRandomNumber(); // Generate a new random number for each
																					// employee
								String fileExtension = getFileExtensionForImage1(departmentLoop);
								String imageUrl = "profile/" + employeeRandomNumber + "/" + employee.get("employee_id")
										+ "." + fileExtension;

								Map<String, Object> modifiedEmployee = new HashMap<>(employee);
								modifiedEmployee.put("profile", imageUrl);
								return modifiedEmployee;
							}).collect(Collectors.toList());

					Map<String, Object> departmentMap = new HashMap<>();
					departmentMap.put("department_id", departmentId);
					departmentMap.put("department_name", departmentLoop.getValue().get(0).get("department_name"));
					departmentMap.put("department_details", filteredDepartmentDetails);
					departmentList.add(departmentMap);

				}

				return ResponseEntity.ok(departmentList);
			} else {
				String errorMessage = "Invalid value for 'department'. Expected 'Department'.";
				return ResponseEntity.badRequest().body(errorMessage);
			}
		}
		private String getFileExtensionForImage1(Entry<String, List<Map<String, Object>>> departmentLoop) {
			if (departmentLoop == null || departmentLoop.getValue() == null || departmentLoop.getValue().isEmpty()) {
				return "jpg";
			}

			String url = (String) departmentLoop.getValue().get(0).get("url");

			if (url == null) {
				return "jpg";
			}

			if (url.endsWith(".png")) {
				return "png";
			} else if (url.endsWith(".jpg")) {
				return "jpg";
			} else {
				return "jpg";
			}
		}

	  
	  
	  
	}

