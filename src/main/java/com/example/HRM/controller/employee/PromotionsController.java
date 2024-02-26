package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Employee;
import com.example.HRM.entity.employee.Promotions;
import com.example.HRM.repository.employee.PromotionsRepository;
import com.example.HRM.service.employee.EmployeeService;
import com.example.HRM.service.employee.PromotionsService;
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

@CrossOrigin
@RestController
public class PromotionsController {
	@Autowired
	  private PromotionsService service;
	  
	  @Autowired
	  private EmployeeService employeeService;
	  
	  @Autowired
	  private PromotionsRepository repo;
	  
	  @GetMapping({"/promotions"})
	  public ResponseEntity<?> getPromotions(@RequestParam(required = true) String promotionsType) {
	    try {
	      if ("proemployee".equals(promotionsType)) {
	        List<Promotions> promotions = this.service.listAll();
	        return ResponseEntity.ok(promotions);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	        .body("The provided LeaveType is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving Promotions: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/promotions/save"})
	  public ResponseEntity<String> savePromotions(@RequestBody Promotions promotions) {
	    try {
	      promotions.setStatus(true);
	      Long employeeId = Long.valueOf(promotions.getEmployeeId());
	      Employee employee = this.employeeService.getEmployeeById(employeeId);
	      if (employee != null) {
	        if ("Manager".equals(promotions.getRoleType())) {
	          promotions.setRoleId(2L);
	        } else if ("ProjectHead".equals(promotions.getRoleType())) {
	          promotions.setRoleId(6L);
	        } else if ("TL".equals(promotions.getRoleType())) {
	          promotions.setRoleId(7L);
	        } else {
	          promotions.setRoleId(0L);
	        } 
	        this.service.saveOrUpdate(promotions);
	        this.employeeService.saveOrUpdate(employee);
	        return ResponseEntity.ok("Promotions saved with id: " + promotions.getPromotionsId());
	      } 
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with employeeId " + employeeId + " not found.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving Promotions: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/promotions/or/{id}"})
	  public ResponseEntity<?> getPromotionsById(@PathVariable(name = "id") long id) {
	    try {
	      Promotions Promotions = this.service.getById(id);
	      if (Promotions != null) {
	        boolean currentStatus = Promotions.isStatus();
	        Promotions.setStatus(!currentStatus);
	        this.service.saveOrUpdate(Promotions);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(Promotions.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/promotions/edit/{id}"})
	  public ResponseEntity<Promotions> updatePromotions(@PathVariable("id") long id, @RequestBody Promotions promotions) {
	    try {
	      Promotions existingPromotions = this.service.getById(id);
	      if (existingPromotions == null)
	        return ResponseEntity.notFound().build(); 
	      Long employeeId = Long.valueOf(promotions.getEmployeeId());
	      Employee employee = this.employeeService.getEmployeeById(employeeId);
	      if (employee != null) {
	        if ("Manager".equals(promotions.getRoleType())) {
	          promotions.setRoleId(2L);
	        } else if ("ProjectHead".equals(promotions.getRoleType())) {
	          promotions.setRoleId(6L);
	        } else if ("TL".equals(promotions.getRoleType())) {
	          promotions.setRoleId(7L);
	        } else {
	          promotions.setRoleId(0L);
	        } 
	      } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	      } 
	      existingPromotions.setDescription(promotions.getDescription());
	      existingPromotions.setEmployeeId(promotions.getEmployeeId());
	      existingPromotions.setRoleId(promotions.getRoleId());
	      existingPromotions.setPromotionsBy(promotions.getPromotionsBy());
	      existingPromotions.setRoleType(promotions.getRoleType());
	      this.service.saveOrUpdate(existingPromotions);
	      return ResponseEntity.ok(existingPromotions);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/promotions/delete/{id}"})
	  public ResponseEntity<String> deletePromotions(@PathVariable("id") long id) {
	    try {
	      this.service.deleteById(id);
	      return ResponseEntity.ok("Promotions deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting Promotions: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/promotions/view"})
	  public List<Map<String, Object>> INeedList(@RequestParam(required = true) String promotions) {
	    try {
	      if ("promotions".equals(promotions))
	        return this.service.AllFine(); 
	      throw new IllegalArgumentException("The provided LeaveType is not supported.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	}

