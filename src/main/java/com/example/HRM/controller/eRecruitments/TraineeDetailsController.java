package com.example.HRM.controller.eRecruitments;
import com.example.HRM.entity.eRecruitments.TraineeDetails;
import com.example.HRM.service.eRecruitments.TraineeDetailsService;
import com.example.HRM.service.employee.EmployeeService;
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
public class TraineeDetailsController {
	@Autowired
	  private EmployeeService service;
	  
	  @Autowired
	  private TraineeDetailsService traineeDetailsService;
	  
	  @GetMapping({"/traineeDetails"})
	  public ResponseEntity<Object> getTraineeDetails(@RequestParam(required = true) String trainee) {
	    if ("traineeDetails".equals(trainee))
	      return ResponseEntity.ok(this.traineeDetailsService.listAll()); 
	    String errorMessage = "Invalid value for 'trainee'. Expected 'trainee'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"traineeDetailss/save"})
	  public ResponseEntity<String> saveTraineeDetailss(@RequestBody TraineeDetails traineeDetails) {
	    try {
	      this.traineeDetailsService.SaveTraineeDetails(traineeDetails);
	      long traineeId = traineeDetails.getTraineeId();
	      return ResponseEntity.ok("TraineeDetails saved successfully. Trainee ID: " + traineeId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving TraineeDetails: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/traineeDetails/ststus/{id}"})
	  public ResponseEntity<Boolean> toggleComplaintsStatus(@PathVariable(name = "id") long id) {
	    try {
	      TraineeDetails complaints = this.traineeDetailsService.findById(Long.valueOf(id));
	      if (complaints != null) {
	        boolean currentStatus = complaints.isStatus();
	        complaints.setStatus(!currentStatus);
	        this.traineeDetailsService.SaveTraineeDetails(complaints);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(true));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(complaints.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"traineeDetails/edit/{id}"})
	  public ResponseEntity<TraineeDetails> updateOrder(@PathVariable("id") Long trainee_id, @RequestBody TraineeDetails traineeDetails) {
	    try {
	      TraineeDetails existingTraineeDetails = this.traineeDetailsService.findById(trainee_id);
	      if (existingTraineeDetails == null)
	        return ResponseEntity.notFound().build(); 
	      existingTraineeDetails.setCollegeName(traineeDetails.getCollegeName());
	      existingTraineeDetails.setTraineeName(traineeDetails.getTraineeName());
	      existingTraineeDetails.setEmailId(traineeDetails.getEmailId());
	      existingTraineeDetails.setStartDate(traineeDetails.getStartDate());
	      existingTraineeDetails.setEndDate(traineeDetails.getEndDate());
	      existingTraineeDetails.setStarted(traineeDetails.isStarted());
	      existingTraineeDetails.setCompleted(traineeDetails.isCompleted());
	      existingTraineeDetails.setMobileNumber(traineeDetails.getMobileNumber());
	      existingTraineeDetails.setStatus(traineeDetails.isStatus());
	      this.traineeDetailsService.SaveTraineeDetails(existingTraineeDetails);
	      return ResponseEntity.ok(existingTraineeDetails);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"traineeDetails/delete/{id}"})
	  public ResponseEntity<String> deletevalue(@PathVariable Long id) {
	    this.traineeDetailsService.deleteTraineeIdId(id);
	    return ResponseEntity.ok("TraineeDetails deleted successfully");
	  }
	}
