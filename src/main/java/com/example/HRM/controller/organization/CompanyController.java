package com.example.HRM.controller.organization;
import com.example.HRM.entity.organization.Company;
import com.example.HRM.service.organization.CompanyService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

@RestController
@CrossOrigin
public class CompanyController {
	 @Autowired
	  private CompanyService companyservice;
	  
	  @GetMapping({"/company"})
	  public ResponseEntity<?> displayAllImages(@RequestParam(required = true) String company) {
	    if ("company".equals(company)) {
	      List<Company> images = this.companyservice.listAll();
	      Map<String, Object> imageObjects = new HashMap<>();
	      for (Company image : images) {
	        int randomNumber = generateRandomNumber();
	        String fileExtension = getFileExtensionForImage(image);
	        String imageUrl = "company/" + randomNumber + "/" + image.getCompanyId() + "." + fileExtension;
	        image.setUrl(imageUrl);
	        imageObjects.put("companyId", Long.valueOf(image.getCompanyId()));
	        imageObjects.put("companyName", image.getCompanyName());
	        imageObjects.put("url", image.getUrl());
	        imageObjects.put("address", image.getAddress());
	        imageObjects.put("bankName", image.getBankName());
	        imageObjects.put("branchName", image.getBranchName());
	        imageObjects.put("country", image.getCountry());
	        imageObjects.put("email", image.getEmail());
	        imageObjects.put("state", image.getState());
	        imageObjects.put("pincode", Integer.valueOf(image.getPincode()));
	        imageObjects.put("location", image.getLocation());
	        imageObjects.put("phoneNumber1", Long.valueOf(image.getPhoneNumber1()));
	        imageObjects.put("phoneNumber2", Long.valueOf(image.getPhoneNumber2()));
	        imageObjects.put("gstNo", image.getGstNo());
	        imageObjects.put("taxNo", image.getTaxNo());
	        imageObjects.put("accountNo", Long.valueOf(image.getAccountNo()));
	        imageObjects.put("ifscCode", image.getIfscCode());
	        imageObjects.put("holderName", image.getHolderName());
	        imageObjects.put("status", Boolean.valueOf(image.isStatus()));
	      } 
	      return ResponseEntity.ok(imageObjects);
	    } 
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList());
	  }
	  
	  private int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(1000000);
	  }
	  
	  @GetMapping({"/company/{randomNumber}/{id:.+}"})
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
	    Company image = this.companyservice.findById(imageId);
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
	  
	  private String getFileExtensionForImage(Company image) {
	    if (image == null || image.getUrl() == null || image.getUrl().isEmpty())
	      return "jpg"; 
	    String url = image.getUrl();
	    if (url.endsWith(".png"))
	      return "png"; 
	    if (url.endsWith(".jpg"))
	      return "jpg"; 
	    return "jpg";
	  }
	  
	  @PostMapping({"/company/save"})
	  public ResponseEntity<?> addEmployeeWithImage(@RequestParam("profile") MultipartFile file, @RequestParam("companyName") String companyName, @RequestParam("gstNo") String gstNo, @RequestParam("taxNo") String taxNo, @RequestParam("country") String country, @RequestParam("email") String email, @RequestParam("state") String state, @RequestParam("address") String address, @RequestParam("bankName") String bankName, @RequestParam("branchName") String branchName, @RequestParam("holderName") String holderName, @RequestParam("ifscCode") String ifscCode, @RequestParam("location") String location, @RequestParam("pincode") int pincode, @RequestParam("accountNo") long accountNo, @RequestParam(value = "phoneNumber2", required = false) long phoneNumber2, @RequestParam("phoneNumber1") long phoneNumber1) {
	    try {
	      byte[] bytes = file.getBytes();
	      Blob blob = new SerialBlob(bytes);
	      Company employee = new Company();
	      employee.setProfile(blob);
	      employee.setStatus(true);
	      employee.setCompanyName(companyName);
	      employee.setCountry(country);
	      employee.setEmail(email);
	      employee.setState(state);
	      employee.setAddress(address);
	      employee.setBankName(bankName);
	      employee.setBranchName(branchName);
	      employee.setAccountNo(accountNo);
	      employee.setHolderName(holderName);
	      employee.setGstNo(gstNo);
	      employee.setTaxNo(taxNo);
	      employee.setLocation(location);
	      employee.setPincode(pincode);
	      employee.setPhoneNumber1(phoneNumber1);
	      employee.setPhoneNumber2(phoneNumber2);
	      employee.setIfscCode(ifscCode);
	      this.companyservice.SaveorUpdate(employee);
	      long id = employee.getCompanyId();
	      return ResponseEntity.ok("Employee added successfully. Employee ID: " + id);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding employee.");
	    } 
	  }
	  
	  @PutMapping({"/company/or/{id}"})
	  public ResponseEntity<Boolean> toggleCustomerStatus(@PathVariable(name = "id") long id) {
	    try {
	      Company company = this.companyservice.findById(Long.valueOf(id));
	      if (company != null) {
	        boolean currentStatus = company.isStatus();
	        company.setStatus(!currentStatus);
	        this.companyservice.SaveorUpdate(company);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(company.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/company/edit/{companyId}"})
	  public ResponseEntity<String> updateCompany(@PathVariable long companyId, @RequestParam(value = "profile", required = false) MultipartFile file, @RequestParam(value = "companyName", required = false) String companyName, @RequestParam(value = "gstNo", required = false) String gstNo, @RequestParam(value = "taxNo", required = false) String taxNo, @RequestParam(value = "country", required = false) String country, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "state", required = false) String state, @RequestParam(value = "address", required = false) String address, @RequestParam(value = "bankName", required = false) String bankName, @RequestParam(value = "branchName", required = false) String branchName, @RequestParam(value = "holderName", required = false) String holderName, @RequestParam(value = "ifscCode", required = false) String ifscCode, @RequestParam(value = "location", required = false) String location, @RequestParam(value = "pincode", required = false) Integer pincode, @RequestParam(value = "accountNo", required = false) Long accountNo, @RequestParam(value = "phoneNumber2", required = false) Long phoneNumber2, @RequestParam(value = "phoneNumber1", required = false) Long phoneNumber1) {
	    try {
	      Company company = this.companyservice.getCompanyById(Long.valueOf(companyId));
	      if (company == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found."); 
	      if (file != null && !file.isEmpty()) {
	        byte[] bytes = file.getBytes();
	        Blob blob = new SerialBlob(bytes);
	        company.setProfile(blob);
	      } 
	      if (companyName != null)
	        company.setCompanyName(companyName); 
	      if (gstNo != null)
	        company.setGstNo(gstNo); 
	      if (taxNo != null)
	        company.setTaxNo(taxNo); 
	      if (country != null)
	        company.setCountry(country); 
	      if (email != null)
	        company.setEmail(email); 
	      if (location != null)
	        company.setLocation(location); 
	      if (state != null)
	        company.setState(state); 
	      if (address != null)
	        company.setAddress(address); 
	      if (bankName != null)
	        company.setBankName(bankName); 
	      if (branchName != null)
	        company.setBranchName(branchName); 
	      if (ifscCode != null)
	        company.setIfscCode(ifscCode); 
	      if (holderName != null)
	        company.setHolderName(holderName); 
	      if (phoneNumber1 != null)
	        company.setPhoneNumber1(phoneNumber1.longValue()); 
	      if (pincode != null)
	        company.setPincode(pincode.intValue()); 
	      if (accountNo != null)
	        company.setAccountNo(accountNo.longValue()); 
	      if (phoneNumber2 != null)
	        company.setPhoneNumber2(phoneNumber2.longValue()); 
	      this.companyservice.SaveorUpdate(company);
	      return ResponseEntity.ok("Company updated successfully. Company ID: " + companyId);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating company.");
	    } 
	  }
	  
	  @DeleteMapping({"/company/companydelete/{companyId}"})
	  public ResponseEntity<String> deleteTitle(@PathVariable("companyId") Long companyId) {
	    this.companyservice.deleteCompanyById(companyId);
	    return ResponseEntity.ok("company deleted successfully");
	  }
	}

