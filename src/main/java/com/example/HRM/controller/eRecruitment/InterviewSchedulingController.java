package com.example.HRM.controller.eRecruitment;
import com.example.HRM.entity.erecruitment.InterviewSchedule;
import com.example.HRM.entity.erecruitment.TaskAssigned;
import com.example.HRM.repository.erecruitment.InterviewSchedulingRepository;
import com.example.HRM.service.eRecruitment.InterviewSchedulingService;
import com.example.HRM.service.eRecruitment.TaskAssignedService;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class InterviewSchedulingController {
	@Autowired
	  private InterviewSchedulingService interviewScheduleService;
	  
	  @Autowired
	  private TaskAssignedService service;
	  
	  @Autowired
	  private InterviewSchedulingRepository interviewSchedulingRepository;
	  
	  @GetMapping({"/interviewSchedule"})
	  public ResponseEntity<Object> getInterviewSchedule(@RequestParam(required = true) String interviewSchedule) {
	    if ("interviewScheduleDetails".equals(interviewSchedule))
	      return ResponseEntity.ok(this.interviewScheduleService.listAll()); 
	    String errorMessage = "Invalid value for 'interviewSchedule'. Expected 'interviewSchedule'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/interviewSchedule/save"})
	  public ResponseEntity<?> saveGroup(@RequestBody InterviewSchedule interviewSchedule) {
	    if (interviewSchedule.getInterviewType().equals("scheduled")) {
	      interviewSchedule.setCanceled(false);
	      interviewSchedule.setCompleted(false);
	      interviewSchedule.setScheduled(true);
	    } else if (interviewSchedule.getInterviewType().equals("cancelled")) {
	      interviewSchedule.setCanceled(true);
	      interviewSchedule.setCompleted(false);
	      interviewSchedule.setScheduled(false);
	    } else if (interviewSchedule.getInterviewType().equals("completed")) {
	      interviewSchedule.setCanceled(false);
	      interviewSchedule.setCompleted(true);
	      interviewSchedule.setScheduled(false);
	    } else {
	      interviewSchedule.setCanceled(false);
	      interviewSchedule.setCompleted(false);
	      interviewSchedule.setScheduled(false);
	      SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
	      if (interviewSchedule.getStartTime() != null && !interviewSchedule.getStartTime().isEmpty())
	        try {
	          String formattedIntime = formatTime(interviewSchedule.getStartTime(), timeFormatter);
	          interviewSchedule.setStartTime(formattedIntime);
	        } catch (Exception exception) {} 
	      if (interviewSchedule.getEndTime() != null && !interviewSchedule.getEndTime().isEmpty())
	        try {
	          String formattedOuttime = formatTime(interviewSchedule.getEndTime(), timeFormatter);
	          interviewSchedule.setEndTime(formattedOuttime);
	        } catch (Exception exception) {} 
	    } 
	    this.interviewScheduleService.SaveInterviewSchedule(interviewSchedule);
	    long interviewScheduleId = interviewSchedule.getInterviewScheduleId();
	    return ResponseEntity.ok("Interview Schedule Saved Successfully with id :" + interviewScheduleId);
	  }
	  
	  private String formatTime(String time, SimpleDateFormat formatter) throws Exception {
	    SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm:ss a");
	    Date date = inputFormat.parse(time);
	    return formatter.format(date);
	  }
	  
	  @PutMapping({"/interviewSchedule/edit/{InterviewScheduleId}"})
	  public ResponseEntity<InterviewSchedule> updateDesignation(@PathVariable("InterviewScheduleId") Long interviewScheduleId, @RequestBody InterviewSchedule designationDetails) {
	    try {
	      InterviewSchedule existingdesignation = this.interviewScheduleService.findById(interviewScheduleId);
	      if (existingdesignation == null)
	        return ResponseEntity.notFound().build(); 
	      existingdesignation.setCancellationReason(designationDetails.getCancellationReason());
	      existingdesignation.setEndTime(designationDetails.getEndTime());
	      existingdesignation.setInterviewerContact(designationDetails.getInterviewerContact());
	      existingdesignation.setInterviewerName(designationDetails.getInterviewerName());
	      existingdesignation.setInterviewFormat(designationDetails.getInterviewFormat());
	      existingdesignation.setInterviewType(designationDetails.getInterviewType());
	      existingdesignation.setStartTime(designationDetails.getStartTime());
	      if ("scheduled".equals(existingdesignation.getInterviewType())) {
	        existingdesignation.setCanceled(false);
	        existingdesignation.setCompleted(false);
	        existingdesignation.setScheduled(true);
	      } else if ("cancelled".equals(existingdesignation.getInterviewType())) {
	        existingdesignation.setCanceled(true);
	        existingdesignation.setCompleted(false);
	        existingdesignation.setScheduled(false);
	      } else if ("completed".equals(existingdesignation.getInterviewType())) {
	        existingdesignation.setCanceled(false);
	        existingdesignation.setCompleted(true);
	        existingdesignation.setScheduled(false);
	      } else {
	        existingdesignation.setCanceled(false);
	        existingdesignation.setCompleted(false);
	        existingdesignation.setScheduled(false);
	        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
	        try {
	          if (existingdesignation.getStartTime() != null && !existingdesignation.getStartTime().isEmpty()) {
	            String formattedIntime = formatTime(existingdesignation.getStartTime(), timeFormatter);
	            existingdesignation.setStartTime(formattedIntime);
	          } 
	          if (existingdesignation.getEndTime() != null && !existingdesignation.getEndTime().isEmpty()) {
	            String formattedOuttime = formatTime(existingdesignation.getEndTime(), timeFormatter);
	            existingdesignation.setEndTime(formattedOuttime);
	          } 
	        } catch (Exception e) {
	          e.printStackTrace();
	        } 
	        existingdesignation.setCompleted(true);
	      } 
	      this.interviewScheduleService.SaveInterviewSchedule(existingdesignation);
	      TaskAssigned task = new TaskAssigned();
	      task.setCandidateId(existingdesignation.getCandidateId());
	      this.service.SaveTaskAssigned(task);
	      return ResponseEntity.ok(existingdesignation);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/interviewSchedule/delete/{id}"})
	  public ResponseEntity<String> deleteInterviewSchedule(@PathVariable("id") Long id) {
	    this.interviewScheduleService.deleteInterviewSchedulingId(id);
	    return ResponseEntity.ok("InterviewSchedule details deleted successfully");
	  }
	  
	  @GetMapping({"/schedulingDetails"})
	  public ResponseEntity<Object> getInterviewSchedulingDetails(@RequestParam(required = true) String interviewSchedule) {
	    if ("interviewschedulingDetails".equals(interviewSchedule)) {
	      List<Map<String, Object>> interviewSchedules = this.interviewSchedulingRepository.FindInterviewSchedulingDetails();
	      return ResponseEntity.ok(interviewSchedules);
	    } 
	    String errorMessage = "Invalid value for 'interviewSchedule'. Expected 'interviewSchedule'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @GetMapping({"notification/interviewScheduled"})
	  public List<Map<String, Object>> interviewScheduled() {
	    return this.interviewSchedulingRepository.findInterviewScheduleNotification();
	  }
	}

