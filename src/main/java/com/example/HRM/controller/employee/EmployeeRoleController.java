package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.EmployeeRole;
import com.example.HRM.service.employee.EmployeeRoleService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EmployeeRoleController {
	@Autowired
	  private EmployeeRoleService EmployeeRoleService;
	  
	  @GetMapping({"/employeerole"})
	  public ResponseEntity<?> getDetails() {
	    try {
	      Iterable<EmployeeRole> EmployeeRoleDetails = this.EmployeeRoleService.listAll();
	      return new ResponseEntity(EmployeeRoleDetails, HttpStatus.OK);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving Employees: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/employeerole/save"})
	  public ResponseEntity<?> saveEmployeeRole(@RequestBody EmployeeRole employeeRole) {
	    try {
	      this.EmployeeRoleService.SaveorUpdate(employeeRole);
	      long id = employeeRole.getEmployeeRoleId().longValue();
	      return ResponseEntity.status(HttpStatus.OK).body("EmployeeRole details saved successfully." + id);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving EmployeeRole details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @PutMapping({"/employeerole/edit/{employeerole}"})
	  public ResponseEntity<EmployeeRole> updateEmployeeRoleId(@PathVariable("employeerole") Long EmployeeRoleId, @RequestBody EmployeeRole EmployeeRoleIdDetails) {
	    try {
	      EmployeeRole existingEmployeeRole = this.EmployeeRoleService.findById(EmployeeRoleId);
	      if (existingEmployeeRole == null)
	        return ResponseEntity.notFound().build(); 
	      existingEmployeeRole.setPayrolDate(EmployeeRoleIdDetails.getPayrolDate());
	      existingEmployeeRole.setWorkingDays(EmployeeRoleIdDetails.getWorkingDays());
	      existingEmployeeRole.setWorkingHour(EmployeeRoleIdDetails.getWorkingHour());
	      this.EmployeeRoleService.save(existingEmployeeRole);
	      return ResponseEntity.ok(existingEmployeeRole);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/employeerole/delete/{employeerole}"})
	  public ResponseEntity<String> deletEmployeeRoleName(@PathVariable("employeerole") Long EmployeeRoleId) {
	    this.EmployeeRoleService.deleteById(EmployeeRoleId);
	    return ResponseEntity.ok("EmployeeRole deleted successfully");
	  }
	}
