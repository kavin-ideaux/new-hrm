package com.example.HRM.controller.eRecruitment;
import com.example.HRM.entity.erecruitment.Appointment;
import com.example.HRM.repository.erecruitment.AppointmentRepository;
import com.example.HRM.service.eRecruitment.AppointmentService;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
public class AppointmentController {
	 @Autowired
	  private AppointmentService appointmentService;
	  
	  @Autowired
	  private AppointmentRepository appointmentRepository;
	  
	  @GetMapping({"/appointment"})
	  public ResponseEntity<Object> getAppointment(@RequestParam(required = true) String appointment) {
	    if ("appointments".equals(appointment))
	      return ResponseEntity.ok(this.appointmentService.listAll()); 
	    String errorMessage = "Invalid value for 'appointment'. Expected 'appointment'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/appointment/save"})
	  public ResponseEntity<String> saveAppointment(@RequestParam("candidateId") long candidateId, @RequestParam("date") Date date, @RequestParam("time") Time time, @RequestParam("position") String position, @RequestParam("confirmationStatus") boolean confirmationStatus, @RequestParam("hrInterviewId") long hrInterviewId) {
	    try {
	      Appointment appointment = new Appointment();
	      appointment.setCandidateId(candidateId);
	      appointment.setDate(date);
	      appointment.setTime(time);
	      appointment.setPosition(position);
	      appointment.setConfirmationStatus(confirmationStatus);
	      appointment.setHrInterviewId(hrInterviewId);
	      this.appointmentService.SaveAppointment(appointment);
	      long appointmentId = appointment.getAppointmentId();
	      return ResponseEntity.ok("Appointment saved successfully. Appointment ID: " + appointmentId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving appointment: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/appointment/{id}"})
	  public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") long id, @RequestParam("candidateId") long candidateId, @RequestParam("date") Date date, @RequestParam("time") Time time, @RequestParam("position") String position, @RequestParam("confirmationStatus") boolean confirmationStatus, @RequestParam("hrInterviewId") long hrInterviewId) {
	    try {
	      Appointment existingAppointment = this.appointmentService.findById(Long.valueOf(id));
	      if (existingAppointment == null)
	        return ResponseEntity.notFound().build(); 
	      existingAppointment.setCandidateId(candidateId);
	      existingAppointment.setDate(date);
	      existingAppointment.setTime(time);
	      existingAppointment.setPosition(position);
	      existingAppointment.setConfirmationStatus(confirmationStatus);
	      existingAppointment.setHrInterviewId(hrInterviewId);
	      this.appointmentService.SaveAppointment(existingAppointment);
	      return ResponseEntity.ok(existingAppointment);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/appointment/delete/{id}"})
	  public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id) {
	    this.appointmentService.deleteAppointmentId(id);
	    return ResponseEntity.ok("Appointment details deleted successfully");
	  }
	  
	  @GetMapping({"/findAppointmentDetails"})
	  public ResponseEntity<?> getAppointmentDetails() {
	    try {
	      List<Map<String, Object>> appointments = this.appointmentRepository.findAppointmentDetails();
	      List<Map<String, Object>> appointmentList = new ArrayList<>();
	      for (Map<String, Object> appointment : appointments) {
	        Map<String, Object> appointmentMap = new HashMap<>();
	        String resumeUrl = "/candidateResume/" + appointment.get("candidate_id");
	        String coverLetterUrl = "/candidateCoverLetter/" + appointment.get("candidate_id");
	        appointmentMap.put("resumeUrl", resumeUrl);
	        appointmentMap.put("coverLetterUrl", coverLetterUrl);
	        appointmentMap.putAll(appointment);
	        appointmentList.add(appointmentMap);
	      } 
	      return ResponseEntity.ok(appointmentList);
	    } catch (Exception e) {
	      String errorMessage = "Error occurred while retrieving appointment details";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Collections.singletonMap("error", errorMessage));
	    } 
	  }
	}

