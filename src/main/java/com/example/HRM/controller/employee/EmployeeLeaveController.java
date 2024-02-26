package com.example.HRM.controller.employee;

import com.example.HRM.entity.employee.EmployeeLeave;
import com.example.HRM.repository.employee.EmployeeLeaveRepository;
import com.example.HRM.service.employee.EmployeeLeaveService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
public class EmployeeLeaveController {
	 @Autowired
	  private EmployeeLeaveService service;
	  
	  @Autowired
	  private EmployeeLeaveRepository repo;
	  
	  @GetMapping({"/employeeleave"})
	  public ResponseEntity<?> getEmployeeLeaves(@RequestParam(required = true) String leave) {
	    try {
	      if ("leave".equals(leave)) {
	        List<EmployeeLeave> employeeLeaves = this.service.listAll();
	        return ResponseEntity.ok(employeeLeaves);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	        .body("The provided leave is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving EmployeeLeaves: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/employeeleave/save"})
	  public ResponseEntity<String> saveEmployeeLeave(@RequestBody EmployeeLeave employeeLeave) {
	    try {
	      if (employeeLeave.getDate() != null && employeeLeave.getToDate() != null && employeeLeave
	        .getDate().after(employeeLeave.getToDate())) {
	        String errorMessage = "FromDate cannot be later than ToDate.";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	      } 
	      Date date = employeeLeave.getDate();
	      Date toDate = employeeLeave.getToDate();
	      int totalDuration = calculateDuration(date, toDate);
	      employeeLeave.setTotalDay(totalDuration);
	      employeeLeave.setStatus(true);
	      this.service.saveOrUpdate(employeeLeave);
	      return ResponseEntity.ok("EmployeeLeave saved with id: " + employeeLeave.getEmployeeLeaveId());
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving EmployeeLeave: " + e.getMessage());
	    } 
	  }
	  
	  private int calculateDuration(Date date1, Date date2) {
	    long diffInMillis = Math.abs(date2.getTime() - date1.getTime());
	    int daysDifference = (int)(diffInMillis / 86400000L);
	    return daysDifference;
	  }
	  
	  @PutMapping({"/employeeleave/or/{id}"})
	  public ResponseEntity<?> getEmployeeLeaveById(@PathVariable(name = "id") long id) {
	    try {
	      EmployeeLeave EmployeeLeave = this.service.getById(id);
	      if (EmployeeLeave != null) {
	        boolean currentStatus = EmployeeLeave.isStatus();
	        EmployeeLeave.setStatus(!currentStatus);
	        this.service.saveOrUpdate(EmployeeLeave);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(EmployeeLeave.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/employeeleave/edit/{id}"})
	  public ResponseEntity<?> updateEmployeeLeave(@PathVariable("id") long id, @RequestBody EmployeeLeave employeeLeave) {
	    try {
	      if (employeeLeave.getDate() != null && employeeLeave.getToDate() != null && employeeLeave
	        .getDate().after(employeeLeave.getToDate())) {
	        String errorMessage = "FromDate cannot be later than ToDate.";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	      } 
	      EmployeeLeave existingEmployeeLeave = this.service.getById(id);
	      if (existingEmployeeLeave == null)
	        return ResponseEntity.notFound().build(); 
	      existingEmployeeLeave.setApprovedBy(employeeLeave.isApprovedBy());
	      existingEmployeeLeave.setEmployeeId(employeeLeave.getEmployeeId());
	      existingEmployeeLeave.setReason(employeeLeave.getReason());
	      existingEmployeeLeave.setDate(employeeLeave.getDate());
	      existingEmployeeLeave.setToDate(employeeLeave.getToDate());
	      existingEmployeeLeave.setTotalDay(employeeLeave.getTotalDay());
	      int totalDuration = calculateDuration(employeeLeave.getDate(), employeeLeave.getToDate());
	      existingEmployeeLeave.setTotalDay(totalDuration);
	      this.service.saveOrUpdate(existingEmployeeLeave);
	      return ResponseEntity.ok(existingEmployeeLeave);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/employeeleave/delete/{id}"})
	  public ResponseEntity<String> deleteEmployeeLeave(@PathVariable("id") long id) {
	    try {
	      this.service.deleteById(id);
	      return ResponseEntity.ok("EmployeeLeave deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting EmployeeLeave: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/employeeleave/view"})
	  public List<Map<String, Object>> AllWorks(@RequestParam(required = true) String employeeleave) {
	    try {
	      if ("employeeleave".equals(employeeleave))
	        return this.repo.getAllProjectWork(); 
	      throw new IllegalArgumentException("The provided employeeleave is not supported.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/employeeleave/{employee_id}"})
	  private List<Map<String, Object>> idbasedAnnouncement(@PathVariable("employee_id") Long employee_id) {
	    List<Map<String, Object>> employeeleave = new ArrayList<>();
	    List<Map<String, Object>> list = this.repo.Allemployeeleave(employee_id);
	    Map<String, List<Map<String, Object>>> announcementGroupMap = (Map<String, List<Map<String, Object>>>)StreamSupport.stream(list.spliterator(), true).collect(Collectors.groupingBy(action -> String.valueOf(action.get("employee_id"))));
	    for (Map.Entry<String, List<Map<String, Object>>> totalList : announcementGroupMap.entrySet()) {
	      Map<String, Object> employeeleaves = new HashMap<>();
	      employeeleaves.put("employee_id", totalList.getKey());
	      employeeleaves.put("approved_by",totalList.getValue().get(0).get("approved_by"));
	      employeeleaves.put("employeeleave Details", totalList.getValue());
	      employeeleave.add(employeeleaves);
	    } 
	    return employeeleave;
	  }
	}
