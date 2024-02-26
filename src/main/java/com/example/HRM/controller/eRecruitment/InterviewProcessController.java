package com.example.HRM.controller.eRecruitment;
import com.example.HRM.entity.erecruitment.InterviewProcess;
import com.example.HRM.repository.erecruitment.InterviewProcessRepository;
import com.example.HRM.service.eRecruitment.InterviewProcessService;
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
public class InterviewProcessController {

	@Autowired
	  private InterviewProcessService interviewProcessService;
	  
	  @Autowired
	  private InterviewProcessRepository interviewProcessRepository;
	  
	  @GetMapping({"/interviewProcess/view"})
	  public ResponseEntity<Object> getAppointment(@RequestParam(required = true) String interviewProcess) {
	    if ("process".equals(interviewProcess))
	      return ResponseEntity.ok(this.interviewProcessService.listAll()); 
	    String errorMessage = "Invalid value for 'InterviewProcess'. Expected 'process'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/interviewProcess/save"})
	  public ResponseEntity<String> saveInterviewProcess(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("candidateId") long candidateId, @RequestParam("feedback") String feedback, @RequestParam("interviewType") String interviewType, @RequestParam("jobPosition") String jobPosition, @RequestParam(value = "started", defaultValue = "false") boolean started, @RequestParam(value = "inProgress", defaultValue = "false") boolean inProgress, @RequestParam(value = "completed", defaultValue = "false") boolean completed) {
	    try {
	      InterviewProcess interviewProcess = new InterviewProcess();
	      interviewProcess.setStartDate(startDate);
	      interviewProcess.setEndDate(endDate);
	      interviewProcess.setCandidateId(candidateId);
	      interviewProcess.setFeedback(feedback);
	      interviewProcess.setInterviewType(interviewType);
	      interviewProcess.setJobPosition(jobPosition);
	      interviewProcess.setStarted(started);
	      interviewProcess.setInProgress(inProgress);
	      interviewProcess.setCompleted(completed);
	      if (started) {
	        interviewProcess.setInProgress(false);
	        interviewProcess.setCompleted(false);
	      } else if (inProgress) {
	        interviewProcess.setStarted(false);
	        interviewProcess.setCompleted(false);
	      } else if (completed) {
	        interviewProcess.setStarted(false);
	        interviewProcess.setInProgress(false);
	      } 
	      this.interviewProcessService.SaveInterviewProcess(interviewProcess);
	      long interviewProcessId = interviewProcess.getInterviewProcessId();
	      return ResponseEntity.ok("InterviewProcess saved successfully. InterviewProcess ID: " + interviewProcessId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving interviewProcess: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/interviewProcess/{id}"})
	  public ResponseEntity<InterviewProcess> updateInterviewProcess(@PathVariable("id") long id, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("candidateId") long candidateId, @RequestParam("feedback") String feedback, @RequestParam("interviewType") String interviewType, @RequestParam("jobPosition") String jobPosition, @RequestParam(value = "started", defaultValue = "false") boolean started, @RequestParam(value = "inProgress", defaultValue = "false") boolean inProgress, @RequestParam(value = "completed", defaultValue = "false") boolean completed) {
	    try {
	      InterviewProcess existingProcess = this.interviewProcessService.findById(Long.valueOf(id));
	      if (existingProcess == null)
	        return ResponseEntity.notFound().build(); 
	      existingProcess.setStartDate(startDate);
	      existingProcess.setEndDate(endDate);
	      existingProcess.setCandidateId(candidateId);
	      existingProcess.setFeedback(feedback);
	      existingProcess.setInterviewType(interviewType);
	      existingProcess.setJobPosition(jobPosition);
	      existingProcess.setStarted(started);
	      existingProcess.setInProgress(inProgress);
	      existingProcess.setCompleted(completed);
	      this.interviewProcessService.SaveInterviewProcess(existingProcess);
	      return ResponseEntity.ok(existingProcess);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/interviewProcess/delete/{id}"})
	  public ResponseEntity<String> deleteInterviewProcess(@PathVariable("id") Long id) {
	    this.interviewProcessService.deleteInterviewProcessId(id);
	    return ResponseEntity.ok("InterviewProcess details deleted successfully");
	  }
	  
	  @GetMapping({"/interviewProcessDetails"})
	  public ResponseEntity<Object> getInterviewSchedulingDetails(@RequestParam(required = true) String interviewProcess) {
	    if ("interviewProcessDetails".equals(interviewProcess)) {
	      List<Map<String, Object>> interviewProcesss = this.interviewProcessRepository.findInterviewProcessDetails();
	      return ResponseEntity.ok(interviewProcesss);
	    } 
	    String errorMessage = "Invalid value for 'interviewProcesss'. Expected 'interviewProcesss'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	}

