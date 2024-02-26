package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Employee;
import com.example.HRM.entity.employee.EmployeeExit;
import com.example.HRM.repository.employee.EmployeeExitRepository;
import com.example.HRM.service.employee.EmployeeExitService;
import com.example.HRM.service.employee.EmployeeService;
import java.util.Collections;
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
public class EmployeeExitController {
	 @Autowired
	  private EmployeeExitService service;
	  
	  @Autowired
	  private EmployeeExitRepository repo;
	  
	  @Autowired
	  private EmployeeService employeeService;
	  
	  @GetMapping({"/employeeexit"})
	  public ResponseEntity<?> getEmployeeExits(@RequestParam(required = true) String exitParam) {
	    try {
	      if ("employeeexit".equals(exitParam)) {
	        List<EmployeeExit> employeeExits = this.service.listAll();
	        return ResponseEntity.ok(employeeExits);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided departmentParam is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving EmployeeExits: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/employeeexit/save"})
	  public ResponseEntity<String> saveEmployeeExit(@RequestBody EmployeeExit employeeExit) {
	    try {
	      employeeExit.setStatus(false);
	      Long employeeId = employeeExit.getEmployeeId();
	      Employee employee = this.employeeService.getEmployeeById(employeeId);
	      if (employee != null) {
	        employee.setStatus(false);
	        this.employeeService.saveOrUpdate(employee);
	      } 
	      this.service.saveOrUpdate(employeeExit);
	      return ResponseEntity.ok("EmployeeExit saved with id: " + employeeExit.getEmployeeExitId());
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving EmployeeExit: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/employeeexit/or/{id}"})
	  public ResponseEntity<?> getEmployeeExitById(@PathVariable(name = "id") long id) {
	    try {
	      EmployeeExit EmployeeExit = this.service.getById(id);
	      if (EmployeeExit != null) {
	        boolean currentStatus = EmployeeExit.isStatus();
	        EmployeeExit.setStatus(!currentStatus);
	        this.service.saveOrUpdate(EmployeeExit);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(EmployeeExit.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/employeeexit/edit/{id}"})
	  public ResponseEntity<EmployeeExit> updateEmployeeExit(@PathVariable("id") long id, @RequestBody EmployeeExit updatedEmployeeExit) {
	    try {
	      EmployeeExit existingEmployeeExit = this.service.getById(id);
	      if (existingEmployeeExit == null)
	        return ResponseEntity.notFound().build(); 
	      existingEmployeeExit.setDescription(updatedEmployeeExit.getDescription());
	      existingEmployeeExit.setEmployeeId(updatedEmployeeExit.getEmployeeId());
	      existingEmployeeExit.setDate(updatedEmployeeExit.getDate());
	      existingEmployeeExit.setCompanyProperty(updatedEmployeeExit.getCompanyProperty());
	      existingEmployeeExit.setExitReason(updatedEmployeeExit.getExitReason());
	      existingEmployeeExit.setExitType(updatedEmployeeExit.getExitType());
	      existingEmployeeExit.setDate(updatedEmployeeExit.getDate());
	      existingEmployeeExit.setStatus(updatedEmployeeExit.isStatus());
	      this.service.saveOrUpdate(existingEmployeeExit);
	      Long employeeId = updatedEmployeeExit.getEmployeeId();
	      Employee employee = this.employeeService.getEmployeeById(employeeId);
	      if (employee != null) {
	        employee.setStatus(false);
	        this.employeeService.saveOrUpdate(employee);
	      } 
	      return ResponseEntity.ok(existingEmployeeExit);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/employeeexit/delete/{id}"})
	  public ResponseEntity<String> deleteEmployeeExit(@PathVariable("id") long id) {
	    try {
	      this.service.deleteById(id);
	      return ResponseEntity.ok("EmployeeExit deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting EmployeeExit: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/employeeexit/view"})
	  public List<Map<String, Object>> AllWorks(@RequestParam(required = true) String viewParam) {
	    try {
	      if ("view".equals(viewParam))
	        return this.repo.getAllProjectWork(); 
	      throw new IllegalArgumentException("The provided exitParam is not supported.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	}

