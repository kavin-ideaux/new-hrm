package com.example.HRM.controller.project;
import com.example.HRM.entity.project.IssueType;
import com.example.HRM.service.project.IssueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@CrossOrigin
public class IssueTypeController {
	 @Autowired
	  private IssueTypeService Service;
	  
	  @GetMapping({"/issueType"})
	  public ResponseEntity<?> getDetails(@RequestParam(required = true) String IssueTypeParam) {
	    try {
	      if ("IssueType".equals(IssueTypeParam)) {
	        Iterable<IssueType> departmentDetails = this.Service.listAll();
	        return new ResponseEntity(departmentDetails, HttpStatus.OK);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided IssueType is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving IssueType details: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/issueType/save"})
	  public ResponseEntity<?> saveDepartment(@RequestBody IssueType issueType) {
	    try {
	      this.Service.SaveorUpdate(issueType);
	      long id = issueType.getIssueTypeId();
	      return ResponseEntity.status(HttpStatus.OK).body("Department details saved successfully." + id);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving Department details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @PutMapping({"/issueType/edit/{issueTypeId}"})
	  public ResponseEntity<IssueType> updateDepartmentId(@PathVariable("issueTypeId") Long IssueTypeId, @RequestBody IssueType DepartmentIdDetails) {
	    try {
	      IssueType existingDepartment = this.Service.findById(IssueTypeId);
	      if (existingDepartment == null)
	        return ResponseEntity.notFound().build(); 
	      existingDepartment.setIssuename(DepartmentIdDetails.getIssuename());
	      this.Service.save(existingDepartment);
	      return ResponseEntity.ok(existingDepartment);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/issueType/delete/{issueTypeId}"})
	  public ResponseEntity<String> deletDepartmentName(@PathVariable("issueTypeId") Long IssueTypeId) {
	    this.Service.deleteById(IssueTypeId);
	    return ResponseEntity.ok("Department deleted successfully");
	  }
	}

