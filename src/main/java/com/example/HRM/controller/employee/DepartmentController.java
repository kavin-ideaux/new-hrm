package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Department;
import com.example.HRM.service.employee.DepartmentService;
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
public class DepartmentController {

	@Autowired
	  private DepartmentService departmentService;
	  
	  @GetMapping({"/department"})
	  public ResponseEntity<?> getDetails(@RequestParam(required = true) String departmentParam) {
	    try {
	      if ("department".equals(departmentParam)) {
	        Iterable<Department> departmentDetails = this.departmentService.listAll();
	        return new ResponseEntity(departmentDetails, HttpStatus.OK);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided department is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving department details: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/department/save"})
	  public ResponseEntity<?> saveDepartment(@RequestBody Department department) {
	    try {
	      this.departmentService.SaveorUpdate(department);
	      long id = department.getDepartmentId().longValue();
	      return ResponseEntity.status(HttpStatus.OK).body("Department details saved successfully." + id);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving Department details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @PutMapping({"/department/edit/{DepartmentId}"})
	  public ResponseEntity<Department> updateDepartmentId(@PathVariable("DepartmentId") Long DepartmentId, @RequestBody Department DepartmentIdDetails) {
	    try {
	      Department existingDepartment = this.departmentService.findById(DepartmentId);
	      if (existingDepartment == null)
	        return ResponseEntity.notFound().build(); 
	      existingDepartment.setDepartmentName(DepartmentIdDetails.getDepartmentName());
	      existingDepartment.setColor(DepartmentIdDetails.getColor());
	      this.departmentService.save(existingDepartment);
	      return ResponseEntity.ok(existingDepartment);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/department/delete/{DepartmentId}"})
	  public ResponseEntity<String> deletDepartmentName(@PathVariable("DepartmentId") Long DepartmentId) {
	    this.departmentService.deleteById(DepartmentId);
	    return ResponseEntity.ok("Department deleted successfully");
	  }
	}

