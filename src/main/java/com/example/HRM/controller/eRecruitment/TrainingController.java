package com.example.HRM.controller.eRecruitment;
import com.example.HRM.PasswordMismatchException;
import com.example.HRM.entity.erecruitment.Training;
import com.example.HRM.repository.erecruitment.TrainingRepository;
import com.example.HRM.service.eRecruitment.TrainingService;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TrainingController {
	@Autowired
	  private TrainingService trainingService;
	  
//	  @Autowired
//	  private PasswordEncoder passwordEncoder;
	  
	  @Autowired
	  private TrainingRepository trainingRepository;
	  
	  @GetMapping({"/traineeDetails/view"})
	  public ResponseEntity<Object> getTrainee(@RequestParam(required = true) String training) {
	    if ("traineeDetails".equals(training))
	      return ResponseEntity.ok(this.trainingService.listAll()); 
	    String errorMessage = "Invalid value for 'training'. Expected 'training'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/trainee/save"})
	  public ResponseEntity<String> saveTrainee(@RequestParam("candidateId") long candidateId, @RequestParam("offerId") long offerId, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam(value = "started", defaultValue = "false") boolean started, @RequestParam(value = "inProgress", defaultValue = "false") boolean inProgress, @RequestParam(value = "completed", defaultValue = "false") boolean completed, @RequestParam("status") boolean status) {
	    try {
	      Training trainee = new Training();
	      trainee.setCandidateId(candidateId);
	      trainee.setOfferId(offerId);
	      trainee.setStartDate(startDate);
	      trainee.setEndDate(endDate);
	      trainee.setStatus(status);
	      trainee.setStarted(started);
	      trainee.setInProgress(inProgress);
	      trainee.setCompleted(completed);
	      if (started) {
	        trainee.setInProgress(false);
	        trainee.setCompleted(false);
	      } else if (inProgress) {
	        trainee.setStarted(false);
	        trainee.setCompleted(false);
	      } else if (completed) {
	        trainee.setInProgress(false);
	        trainee.setStarted(false);
	      } 
	      trainee.setRoleId(8L);
	      String takenPassword = trainee.getPassword();
	      String confirmPassword = trainee.getConfirmPassword();
	      if (!takenPassword.equals(confirmPassword))
	        throw new PasswordMismatchException("Password and confirm password do not match"); 
//	      String encodedPassword = this.passwordEncoder.encode(trainee.getPassword());
	      trainee.setPassword(takenPassword);
	      this.trainingService.SaveTrainee(trainee);
	      long traineeId = trainee.getTraineeId();
	      return ResponseEntity.ok("Trainee Details saved successfully. Trainee ID: " + traineeId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving trainee: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/trainee/{id}"})
	  public ResponseEntity<Training> updateTraineeDetails(@PathVariable("id") long id, @RequestParam("candidateId") long candidateId, @RequestParam("offerId") long offerId, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("status") boolean status, @RequestParam(value = "started", defaultValue = "false") boolean started, @RequestParam(value = "inProgress", defaultValue = "false") boolean inProgress, @RequestParam(value = "completed", defaultValue = "false") boolean completed) {
	    try {
	      Training existingTrainee = this.trainingService.findById(Long.valueOf(id));
	      if (existingTrainee == null)
	        return ResponseEntity.notFound().build(); 
	      existingTrainee.setCandidateId(candidateId);
	      existingTrainee.setOfferId(offerId);
	      existingTrainee.setStartDate(startDate);
	      existingTrainee.setEndDate(endDate);
	      existingTrainee.setStatus(status);
	      existingTrainee.setStarted(started);
	      existingTrainee.setInProgress(inProgress);
	      existingTrainee.setCompleted(completed);
	      if (started) {
	        existingTrainee.setInProgress(false);
	        existingTrainee.setCompleted(false);
	      } else if (inProgress) {
	        existingTrainee.setStarted(false);
	        existingTrainee.setCompleted(false);
	      } else if (completed) {
	        existingTrainee.setStarted(false);
	        existingTrainee.setInProgress(false);
	      } 
	      this.trainingService.SaveTrainee(existingTrainee);
	      return ResponseEntity.ok(existingTrainee);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/trainee/delete/{id}"})
	  public ResponseEntity<String> deleteTrainee(@PathVariable("id") Long id) {
	    this.trainingService.deleteTrainingId(id);
	    return ResponseEntity.ok("Trainee details deleted successfully");
	  }
	  
	  @GetMapping({"/trainingDetailsWithCandidate"})
	  public ResponseEntity<Object> getTrainingDetails(@RequestParam(required = true) String training) {
	    if ("traineeDetails".equals(training)) {
	      List<Map<String, Object>> trainees = this.trainingRepository.findTrainingDetails();
	      return ResponseEntity.ok(trainees);
	    } 
	    String errorMessage = "Invalid value for 'training'. Expected 'training'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	}


