package com.example.HRM.controller.eRecruitment;

import com.example.HRM.entity.erecruitment.GroupDiscussion;
import com.example.HRM.repository.erecruitment.GroupDiscussionRepository;
import com.example.HRM.service.eRecruitment.GroupDiscussionService;
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
public class GroupDiscussionController {
	@Autowired
	  private GroupDiscussionService groupDiscussionService;
	  
	  @Autowired
	  private GroupDiscussionRepository groupDiscussionRepository;
	  
	  @GetMapping({"/groupDiscussion"})
	  public ResponseEntity<Object> getGroupDiscussion(@RequestParam(required = true) String groupDiscussion) {
	    if ("groupDiscussionDetails".equals(groupDiscussion))
	      return ResponseEntity.ok(this.groupDiscussionService.listAll()); 
	    String errorMessage = "Invalid value for 'Discussion'. Expected 'groupDiscussion'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/groupDiscussion/save"})
	  public ResponseEntity<?> saveDepartment(@RequestBody GroupDiscussion groupDiscussion) {
	    try {
	      this.groupDiscussionService.SaveGroupDiscussion(groupDiscussion);
	      long id = groupDiscussion.getGroupDiscussionId();
	      return ResponseEntity.status(HttpStatus.OK).body("groupDiscussion details saved successfully." + id);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving groupDiscussion details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @PutMapping({"/groupDiscussion/{id}"})
	  public ResponseEntity<GroupDiscussion> updateGroupDiscussion(@PathVariable("id") long id, @RequestParam("candidateId") long candidateId, @RequestParam("topic") String topic, @RequestParam("date") Date date, @RequestParam("time") Time time, @RequestParam("feedback") String feedback, @RequestParam("rules") String rules, @RequestParam("format") String format, @RequestParam("taskId") Long taskId, @RequestParam(value = "started", defaultValue = "false") boolean started, @RequestParam(value = "inProgress", defaultValue = "false") boolean inProgress, @RequestParam(value = "completed", defaultValue = "false") boolean completed, @RequestParam("status") boolean status) {
	    try {
	      GroupDiscussion existingDiscussion = this.groupDiscussionService.findById(Long.valueOf(id));
	      if (existingDiscussion == null)
	        return ResponseEntity.notFound().build(); 
	      existingDiscussion.setCandidateId(candidateId);
	      existingDiscussion.setTopic(topic);
	      existingDiscussion.setDate(date);
	      existingDiscussion.setTime(time);
	      existingDiscussion.setFeedback(feedback);
	      existingDiscussion.setRules(rules);
	      existingDiscussion.setFormat(format);
	      existingDiscussion.setTaskId(taskId.longValue());
	      existingDiscussion.setStarted(started);
	      existingDiscussion.setInProgress(inProgress);
	      existingDiscussion.setCompleted(completed);
	      existingDiscussion.setStatus(status);
	      if (started) {
	        existingDiscussion.setInProgress(false);
	        existingDiscussion.setCompleted(false);
	      } else if (inProgress) {
	        existingDiscussion.setStarted(false);
	        existingDiscussion.setCompleted(false);
	      } else if (completed) {
	        existingDiscussion.setStarted(false);
	        existingDiscussion.setInProgress(false);
	      } 
	      this.groupDiscussionService.SaveGroupDiscussion(existingDiscussion);
	      return ResponseEntity.ok(existingDiscussion);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/groupDiscussion/delete/{id}"})
	  public ResponseEntity<String> deleteGroupDiscussion(@PathVariable("id") Long id) {
	    this.groupDiscussionService.deletegroupDiscussionId(id);
	    return ResponseEntity.ok("Group Discussion details deleted successfully");
	  }
	  
	  @GetMapping({"/groupDiscussionDetails"})
	  public ResponseEntity<Object> getGroupDiscussionDetails(@RequestParam(required = true) String groupDiscussion) {
	    if ("groupDiscussionDetail".equals(groupDiscussion)) {
	      List<Map<String, Object>> groupDiscussions = this.groupDiscussionRepository.findGroupDiscussionDetails();
	      return ResponseEntity.ok(groupDiscussions);
	    } 
	    String errorMessage = "Invalid value for 'groupDiscussion'. Expected 'groupDiscussion'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	}

