package com.example.HRM.controller.eRecruitment;
import com.example.HRM.entity.erecruitment.HrInterview;
import com.example.HRM.repository.erecruitment.HrInterviewRepository;
import com.example.HRM.service.eRecruitment.HrInterviewService;
import java.sql.Date;
import java.sql.Time;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HrInterviewController {

	 @Autowired
	  private HrInterviewService hrInterviewService;
	  
	  @Autowired
	  private HrInterviewRepository hrInterviewRepository;
	  
	  @GetMapping({"/ hrInterview"})
	  public ResponseEntity<Object> getHrInterview(@RequestParam(required = true) String hrInterview) {
	    if (" hrInterviewDetails".equals(hrInterview))
	      return ResponseEntity.ok(this.hrInterviewService.listAll()); 
	    String errorMessage = "Invalid value for 'hrInterview'. Expected 'hrInterview'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/hrInterview/save"})
	  public ResponseEntity<?> saveHrInterview(@RequestBody HrInterview hrInterview) {
	    return null;
	  }
	  
	  @PutMapping({"/hrInterview/{id}"})
	  public ResponseEntity<HrInterview> updateHrInterview(@PathVariable("id") long id, @RequestParam("candidateId") long candidateId, @RequestParam("interviewerName") String interviewerName, @RequestParam("date") Date date, @RequestParam("time") Time time, @RequestParam("interviewerContact") String interviewerContact, @RequestParam("feedback") String feedback, @RequestParam("candidateQuestions") String candidateQuestions, @RequestParam("groupDiscussionId") Long groupDiscussionId, @RequestParam(value = "started", defaultValue = "false") boolean started, @RequestParam(value = "inProgress", defaultValue = "false") boolean inProgress, @RequestParam(value = "completed", defaultValue = "false") boolean completed, @RequestParam("status") boolean status) {
	    try {
	      HrInterview existingHrInterview = this.hrInterviewService.findById(Long.valueOf(id));
	      if (existingHrInterview == null)
	        return ResponseEntity.notFound().build(); 
	      existingHrInterview.setCandidateId(candidateId);
	      existingHrInterview.setInterviewerName(interviewerName);
	      existingHrInterview.setInterviewerContact(interviewerContact);
	      existingHrInterview.setDate(date);
	      existingHrInterview.setCandidateQuestions(candidateQuestions);
	      existingHrInterview.setFeedback(feedback);
	      existingHrInterview.setGroupDiscussionId(groupDiscussionId.longValue());
	      existingHrInterview.setStarted(started);
	      existingHrInterview.setInProgress(inProgress);
	      existingHrInterview.setCompleted(completed);
	      existingHrInterview.setStatus(status);
	      if (started) {
	        existingHrInterview.setInProgress(false);
	        existingHrInterview.setCompleted(false);
	      } else if (inProgress) {
	        existingHrInterview.setStarted(false);
	        existingHrInterview.setCompleted(false);
	      } else if (completed) {
	        existingHrInterview.setStarted(false);
	        existingHrInterview.setInProgress(false);
	      } 
	      this.hrInterviewService.SaveHrInterview(existingHrInterview);
	      return ResponseEntity.ok(existingHrInterview);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/hrInterview/delete/{id}"})
	  public ResponseEntity<String> deleteHrInterview(@PathVariable("id") Long id) {
	    this.hrInterviewService.deleteHrInterviewId(id);
	    return ResponseEntity.ok("HrInterview details deleted successfully");
	  }
	  
	  @GetMapping({"/hrInterviewDetails"})
	  public ResponseEntity<Object> getHrInterviewDetails(@RequestParam(required = true) String hrInterview) {
	    if ("hrInterviewDetails".equals(hrInterview)) {
	      List<Map<String, Object>> hrInterviews = this.hrInterviewRepository.findHrInterviewDetails();
	      return ResponseEntity.ok(hrInterviews);
	    } 
	    String errorMessage = "Invalid value for 'hrInterview'. Expected 'hrInterview'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	}

