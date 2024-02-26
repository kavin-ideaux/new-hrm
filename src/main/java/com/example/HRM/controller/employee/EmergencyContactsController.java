package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.EmergencyContacts;
import com.example.HRM.service.employee.EmergencyContactsService;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EmergencyContactsController {
	@Autowired
	  private EmergencyContactsService service;
	  
	  @GetMapping({"/emergencycontacts"})
	  public ResponseEntity<?> getEmergencyContacts(@RequestParam(required = true) String contacts) {
	    try {
	      if ("emergency".equals(contacts)) {
	        List<EmergencyContacts> emergencyContacts = this.service.listAll();
	        return ResponseEntity.ok(emergencyContacts);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided parameter is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving emergency contacts: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/emergencycontacts/save"})
	  public ResponseEntity<String> saveEmergencyContacts(@RequestBody EmergencyContacts emergencyContacts) {
	    try {
	      emergencyContacts.setStatus(true);
	      this.service.saveOrUpdate(emergencyContacts);
	      return ResponseEntity.ok("EmergencyContacts saved with id: " + emergencyContacts.getEmergencyContactsId());
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving EmergencyContacts: " + e.getMessage());
	    } 
	  }
	  
	  @RequestMapping({"/emergencycontacts/{emergencycontactsId}"})
	  public Optional<?> getDesignation(@PathVariable(name = "emergencycontactsId") long emergencycontactsId) {
	    return this.service.getDesignationById(Long.valueOf(emergencycontactsId));
	  }
	  
	  @PutMapping({"/emergencycontacts/or/{id}"})
	  public ResponseEntity<?> getEmergencyContactsById(@PathVariable(name = "id") long id) {
	    try {
	      EmergencyContacts EmergencyContacts = this.service.getByEmployeeId(id);
	      if (EmergencyContacts != null) {
	        boolean currentStatus = EmergencyContacts.isStatus();
	        EmergencyContacts.setStatus(!currentStatus);
	        this.service.saveOrUpdate(EmergencyContacts);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(EmergencyContacts.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/emergencycontacts/edit/{id}"})
	  public ResponseEntity<EmergencyContacts> updateEmergencyContacts(@PathVariable("id") long id, @RequestBody EmergencyContacts emergencyContacts) {
	    try {
	      EmergencyContacts existingEmergencyContacts = this.service.getByEmployeeId(id);
	      if (existingEmergencyContacts == null)
	        return ResponseEntity.notFound().build(); 
	      existingEmergencyContacts.setAddress(emergencyContacts.getAddress());
	      existingEmergencyContacts.setRelatinoName(emergencyContacts.getRelatinoName());
	      existingEmergencyContacts.setPhoneNumber(emergencyContacts.getPhoneNumber());
	      this.service.saveOrUpdate(existingEmergencyContacts);
	      return ResponseEntity.ok(existingEmergencyContacts);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/emergencycontacts/delete/{id}"})
	  public ResponseEntity<String> deleteEmergencyContacts(@PathVariable("id") long id) {
	    try {
	      this.service.deleteById(id);
	      return ResponseEntity.ok("EmergencyContacts deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting EmergencyContacts: " + e.getMessage());
	    } 
	  }
	}

