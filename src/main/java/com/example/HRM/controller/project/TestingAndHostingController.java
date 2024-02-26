package com.example.HRM.controller.project;
import com.example.HRM.entity.project.TestingAndHosting;
import com.example.HRM.service.project.TestingAndHostingService;
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
public class TestingAndHostingController {
	@Autowired
	  private TestingAndHostingService testingAndHostingService;
	  
	  @GetMapping({"/testing/hosting"})
	  public ResponseEntity<?> getAllDetails(@RequestParam(required = true) String testing) {
	    try {
	      if ("Hosted".equals(testing)) {
	        Iterable<TestingAndHosting> departmentDetails = this.testingAndHostingService.testingAndHostingForProject();
	        return new ResponseEntity(departmentDetails, HttpStatus.OK);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Hosted Issue is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving Hosted details: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/testing/hosting/save"})
	  public ResponseEntity<?> saveTesting(@RequestBody TestingAndHosting testing) {
	    try {
	      if (testing.getProjectStatus().equals("todo")) {
	        testing.setError(false);
	        testing.setHostingCompled(false);
	        testing.setHostingProcess(false);
	        testing.setMovedHosting(false);
	        testing.setOnProcess(false);
	        testing.setTodo(true);
	      } else if (testing.getProjectStatus().equals("error")) {
	        testing.setError(true);
	        testing.setHostingCompled(false);
	        testing.setHostingProcess(false);
	        testing.setMovedHosting(false);
	        testing.setOnProcess(false);
	        testing.setTodo(false);
	      } else if (testing.getProjectStatus().equals("hostingCompleted")) {
	        testing.setError(false);
	        testing.setHostingCompled(true);
	        testing.setHostingProcess(false);
	        testing.setMovedHosting(false);
	        testing.setOnProcess(false);
	        testing.setTodo(false);
	      } else if (testing.getProjectStatus().equals("hostingProcess")) {
	        testing.setError(false);
	        testing.setHostingCompled(false);
	        testing.setHostingProcess(true);
	        testing.setMovedHosting(false);
	        testing.setOnProcess(false);
	        testing.setTodo(false);
	      } else if (testing.getProjectStatus().equals("movedHosting")) {
	        testing.setError(false);
	        testing.setHostingCompled(false);
	        testing.setHostingProcess(false);
	        testing.setMovedHosting(true);
	        testing.setOnProcess(false);
	        testing.setTodo(false);
	      } else if (testing.getProjectStatus().equals("onProcess")) {
	        testing.setError(false);
	        testing.setHostingCompled(false);
	        testing.setHostingProcess(false);
	        testing.setMovedHosting(false);
	        testing.setOnProcess(true);
	        testing.setTodo(false);
	      } else {
	        testing.setError(false);
	        testing.setHostingCompled(false);
	        testing.setHostingProcess(false);
	        testing.setMovedHosting(false);
	        testing.setOnProcess(false);
	        testing.setTodo(false);
	      } 
	      this.testingAndHostingService.saveProjectTestAndHost(testing);
	      long id = testing.getTestId();
	      return ResponseEntity.status(HttpStatus.OK).body("Testing details saved successfully." + id);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving Testing details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @PutMapping({"/testing/hosting/edit/{id}"})
	  public ResponseEntity<TestingAndHosting> updateTesting(@PathVariable("id") Long testId, @RequestBody TestingAndHosting testing) {
	    try {
	      TestingAndHosting existingTesting = this.testingAndHostingService.findProjectAssignedForTestAndHostById(testId);
	      if (existingTesting == null)
	        return ResponseEntity.notFound().build(); 
	      existingTesting.setDate(testing.getDate());
	      existingTesting.setEmployeeId(testing.getEmployeeId());
	      existingTesting.setApiDocumentationId(testing.getApiDocumentationId());
	      existingTesting.setProjectStatus(testing.getProjectStatus());
	      existingTesting.setError(testing.isError());
	      existingTesting.setHostingCompled(testing.isHostingCompled());
	      existingTesting.setTodo(testing.isTodo());
	      existingTesting.setMovedHosting(testing.isMovedHosting());
	      existingTesting.setOnProcess(testing.isOnProcess());
	      existingTesting.setHostingProcess(testing.isHostingProcess());
	      String updatedTestingProcess = testing.getProjectStatus();
	      switch (updatedTestingProcess) {
	        case "todo":
	          existingTesting.setError(false);
	          existingTesting.setHostingCompled(false);
	          existingTesting.setHostingProcess(false);
	          existingTesting.setMovedHosting(false);
	          existingTesting.setOnProcess(false);
	          existingTesting.setTodo(true);
	          this.testingAndHostingService.saveProjectTestAndHost(existingTesting);
	          return ResponseEntity.ok(existingTesting);
	        case "hostingCompleted":
	          existingTesting.setError(false);
	          existingTesting.setHostingCompled(true);
	          existingTesting.setHostingProcess(false);
	          existingTesting.setMovedHosting(false);
	          existingTesting.setOnProcess(false);
	          existingTesting.setTodo(false);
	          this.testingAndHostingService.saveProjectTestAndHost(existingTesting);
	          return ResponseEntity.ok(existingTesting);
	        case "hostingProcess":
	          existingTesting.setError(false);
	          existingTesting.setHostingCompled(false);
	          existingTesting.setHostingProcess(true);
	          existingTesting.setMovedHosting(false);
	          existingTesting.setOnProcess(false);
	          existingTesting.setTodo(false);
	          this.testingAndHostingService.saveProjectTestAndHost(existingTesting);
	          return ResponseEntity.ok(existingTesting);
	        case "movedHosting":
	          existingTesting.setError(false);
	          existingTesting.setHostingCompled(false);
	          existingTesting.setHostingProcess(false);
	          existingTesting.setMovedHosting(true);
	          existingTesting.setOnProcess(false);
	          existingTesting.setTodo(false);
	          this.testingAndHostingService.saveProjectTestAndHost(existingTesting);
	          return ResponseEntity.ok(existingTesting);
	        case "onProcess":
	          existingTesting.setError(false);
	          existingTesting.setHostingCompled(false);
	          existingTesting.setHostingProcess(false);
	          existingTesting.setMovedHosting(false);
	          existingTesting.setOnProcess(true);
	          existingTesting.setTodo(false);
	          this.testingAndHostingService.saveProjectTestAndHost(existingTesting);
	          return ResponseEntity.ok(existingTesting);
	        case "error":
	          existingTesting.setError(true);
	          existingTesting.setHostingCompled(false);
	          existingTesting.setHostingProcess(false);
	          existingTesting.setMovedHosting(false);
	          existingTesting.setOnProcess(false);
	          existingTesting.setTodo(false);
	          this.testingAndHostingService.saveProjectTestAndHost(existingTesting);
	          return ResponseEntity.ok(existingTesting);
	      } 
	      existingTesting.setError(false);
	      existingTesting.setHostingCompled(false);
	      existingTesting.setHostingProcess(false);
	      existingTesting.setMovedHosting(false);
	      existingTesting.setOnProcess(false);
	      existingTesting.setTodo(false);
	      this.testingAndHostingService.saveProjectTestAndHost(existingTesting);
	      return ResponseEntity.ok(existingTesting);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/testing/hosting/{id}"})
	  public ResponseEntity<String> deletDepartmentName(@PathVariable("id") Long testId) {
	    this.testingAndHostingService.deleteProjectAssignedForTestAndHost(testId);
	    return ResponseEntity.ok("Testing deleted successfully");
	  }
	}

