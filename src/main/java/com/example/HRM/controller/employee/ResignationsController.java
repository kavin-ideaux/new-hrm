package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Employee;
import com.example.HRM.entity.employee.Resignations;
import com.example.HRM.repository.employee.ResignationsRepository;
import com.example.HRM.service.employee.EmployeeService;
import com.example.HRM.service.employee.ResignationsService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

@CrossOrigin
@RestController
public class ResignationsController {
	@Autowired
	  private ResignationsService service;
	  
	  @Autowired
	  private ResignationsRepository repo;
	  
	  @Autowired
	  private EmployeeService employeeService;
	  
	  @GetMapping({"/resignations"})
	  public ResponseEntity<?> getResignations(@RequestParam(required = true) String resignationsParam) {
	    try {
	      if ("resignations".equalsIgnoreCase(resignationsParam)) {
	        List<Resignations> resignations = this.service.listAll();
	        return ResponseEntity.ok(resignations);
	      } 
	      return ResponseEntity.badRequest().body("Invalid parameter value. 'personalParam' must be 'Personal'.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving resignations: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/resignations/save"})
	  public ResponseEntity<String> saveRelationType(@RequestBody Resignations resignations) {
	    try {
	      Employee employee = this.employeeService.getEmployeeById(Long.valueOf(resignations.getEmployeeId()));
	      employee.setStatus(false);
	      this.employeeService.saveOrUpdate(employee);
	      this.service.saveOrUpdate(resignations);
	      return ResponseEntity.ok("Resignations saved with id: " + resignations.getResignationsId());
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving RelationType: " + e.getMessage());
	    } 
	  }
	  
	  private int calculateDuration(Date date1, Date date2) {
	    long diffInMillis = Math.abs(date2.getTime() - date1.getTime());
	    int daysDifference = (int)(diffInMillis / 86400000L);
	    return daysDifference;
	  }
	  
	  @PutMapping({"/resignations/or/{id}"})
	  public ResponseEntity<?> toggleResignationStatus(@PathVariable(name = "id") long id) {
	    try {
	      Resignations resignation = this.service.getById(id);
	      if (resignation != null) {
	        boolean currentStatus = resignation.isStatus();
	        resignation.setStatus(!currentStatus);
	        this.service.saveOrUpdate(resignation);
	        return ResponseEntity.ok(Boolean.valueOf(resignation.isStatus()));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(false));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/resignations/edit/{id}"})
	  public ResponseEntity<?> updateResignations(@PathVariable("id") long id, @RequestBody Resignations resignations) {
	    try {
	      Resignations existingResignations = this.service.getById(id);
	      if (existingResignations == null)
	        return ResponseEntity.notFound().build(); 
	      existingResignations.setResignationsDate(resignations.getResignationsDate());
	      existingResignations.setReason(resignations.getReason());
	      existingResignations.setEmployeeId(resignations.getEmployeeId());
	      existingResignations.setType(resignations.getType());
	      this.service.saveOrUpdate(existingResignations);
	      return ResponseEntity.ok(existingResignations);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/resignations/delete/{id}"})
	  public ResponseEntity<String> deleteResignations(@PathVariable("id") long id) {
	    try {
	      this.service.deleteById(id);
	      return ResponseEntity.ok("Resignations deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting Resignations: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/resignations/view"})
	  public List<Map<String, Object>> getAllReg(@RequestParam(required = true) String resignationsParam) {
	    try {
	      if ("resignationsview".equalsIgnoreCase(resignationsParam))
	        return this.service.ALLOver(); 
	      throw new IllegalArgumentException("Invalid parameter value. 'resignationsParam' must be 'resignations'.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/resignations/{employee_id}"})
	  private List<Map<String, Object>> idbasedAnnouncement(@PathVariable("employee_id") Long employee_id) {
	    List<Map<String, Object>> announcementlist = new ArrayList<>();
	    List<Map<String, Object>> list = this.repo.AllTimeGoat(employee_id.longValue());
	    Map<String, List<Map<String, Object>>> announcementGroupMap = (Map<String, List<Map<String, Object>>>)StreamSupport.stream(list.spliterator(), false).collect(Collectors.groupingBy(action -> String.valueOf(action.get("employee_id"))));
	    for (Map.Entry<String, List<Map<String, Object>>> totalList : announcementGroupMap.entrySet()) {
	      Map<String, Object> announcementMap = new HashMap<>();
	      announcementMap.put("employee_id", totalList.getKey());
	      announcementMap.put("resignations_date", totalList.getValue().get(0).get("resignations_date"));
	      announcementMap.put("resignations Details", totalList.getValue());
	      announcementlist.add(announcementMap);
	    } 
	    return announcementlist;
	  }
	  
	  @PostMapping({"/resignations/date"})
	  public List<Map<String, Object>> getAllVoucherBetweenDates(@RequestBody Map<String, Object> requestBody) {
	    LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
	    LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
	    return this.repo.getAllReceiptBetweenDate(startDate, endDate);
	  }
	  
	  @GetMapping({"/resignations/durationcount"})
	  public List<Map<String, Object>> getAllDurationSDate() {
	    return this.repo.getAllDurationDate();
	  }
	}

