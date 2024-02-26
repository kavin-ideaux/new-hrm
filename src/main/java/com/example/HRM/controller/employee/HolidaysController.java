package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Holidays;
import com.example.HRM.repository.employee.HolidaysRepository;
import com.example.HRM.service.employee.HolidaysService;
import java.util.List;
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
public class HolidaysController {
	 @Autowired
	  private HolidaysService service;
	  
	  @Autowired
	  private HolidaysRepository repo;
	  
	  @GetMapping({"/leavetype"})
	  public ResponseEntity<?> getLeaveTypes(@RequestParam(required = true) String LeaveType) {
	    try {
	      if ("leaveType".equals(LeaveType)) {
	        List<Holidays> leaveTypes = this.service.listAll();
	        return ResponseEntity.ok(leaveTypes);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	        .body("The provided leave parameter is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving LeaveTypes: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/leavetype/save"})
	  public ResponseEntity<String> saveLeaveType(@RequestBody Holidays LeaveType) {
	    try {
	      this.service.saveOrUpdate(LeaveType);
	      return ResponseEntity.ok("LeaveType saved with id: " + LeaveType.getHolidaysId());
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving LeaveType: " + e.getMessage());
	    } 
	  }
	  
	  @RequestMapping({"/leavetype/{id}"})
	  public ResponseEntity<?> getLeaveTypeById(@PathVariable(name = "id") long id) {
	    try {
	      Holidays LeaveType = this.service.getById(id);
	      if (LeaveType != null)
	        return ResponseEntity.ok(LeaveType); 
	      return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving LeaveType: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/leavetype/edit/{id}"})
	  public ResponseEntity<Holidays> updateLeaveType(@PathVariable("id") long id, @RequestBody Holidays LeaveType) {
	    try {
	      Holidays existingLeaveType = this.service.getById(id);
	      if (existingLeaveType == null)
	        return ResponseEntity.notFound().build(); 
	      existingLeaveType.setDay(LeaveType.getDay());
	      existingLeaveType.setTitle(LeaveType.getTitle());
	      existingLeaveType.setDate(LeaveType.getDate());
	      this.service.saveOrUpdate(existingLeaveType);
	      return ResponseEntity.ok(existingLeaveType);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/leavetype/delete/{id}"})
	  public ResponseEntity<String> deleteLeaveType(@PathVariable("id") long id) {
	    try {
	      this.service.deleteById(id);
	      return ResponseEntity.ok("LeaveType deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting LeaveType: " + e.getMessage());
	    } 
	  }
	}

