package com.example.HRM.controller.project;
import com.example.HRM.entity.project.Issue;
import com.example.HRM.service.project.IssueService;
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
public class IssueController {
	@Autowired
	  private IssueService Service;
	  
	  @GetMapping({"/issue"})
	  public ResponseEntity<?> getDetails(@RequestParam(required = true) String IssueParam) {
	    try {
	      if ("Issue".equals(IssueParam)) {
	        Iterable<Issue> departmentDetails = this.Service.listAll();
	        return new ResponseEntity(departmentDetails, HttpStatus.OK);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided Issue is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving Issue details: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/issue/save"})
	  public ResponseEntity<?> saveDepartment(@RequestBody Issue issue) {
	    try {
	      this.Service.SaveorUpdate(issue);
	      long id = issue.getIssueId();
	      return ResponseEntity.status(HttpStatus.OK).body("Department details saved successfully." + id);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving Department details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @PutMapping({"/issue/edit/{issueId}"})
	  public ResponseEntity<Issue> updateDepartmentId(@PathVariable("issueId") Long issueId, @RequestBody Issue DepartmentIdDetails) {
	    try {
	      Issue existingDepartment = this.Service.findById(issueId);
	      if (existingDepartment == null)
	        return ResponseEntity.notFound().build(); 
	      existingDepartment.setDate(DepartmentIdDetails.getDate());
	      existingDepartment.setEmployeeId(DepartmentIdDetails.getEmployeeId());
	      existingDepartment.setProjectId(DepartmentIdDetails.getProjectId());
	      this.Service.save(existingDepartment);
	      return ResponseEntity.ok(existingDepartment);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/issue/delete/{issueId}"})
	  public ResponseEntity<String> deletDepartmentName(@PathVariable("issueId") Long IssueId) {
	    this.Service.deleteById(IssueId);
	    return ResponseEntity.ok("Department deleted successfully");
	  }
	}

