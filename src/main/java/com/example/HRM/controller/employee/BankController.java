package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Bank;
import com.example.HRM.service.employee.BankService;
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
public class BankController {

	@Autowired
	  private BankService Service;
	  
	  @GetMapping({"/bank"})
	  public ResponseEntity<?> getDetails(@RequestParam(required = true) String bankParam) {
	    try {
	      if ("bank".equals(bankParam)) {
	        Iterable<Bank> departmentDetails = this.Service.listAll();
	        return new ResponseEntity(departmentDetails, HttpStatus.OK);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided bank is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error retrieving bank details: " + e.getMessage());
	    } 
	  }
	  
	  @PostMapping({"/bank/save"})
	  public ResponseEntity<?> saveDepartment(@RequestBody Bank bank) {
	    try {
	      this.Service.SaveorUpdate(bank);
	      long id = bank.getBankId().longValue();
	      return ResponseEntity.status(HttpStatus.OK).body("Department details saved successfully." + id);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving Department details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @RequestMapping({"/bank/{bankId}"})
	  private Optional<?> getDesignation(@PathVariable(name = "bankId") long bankId) {
	    return this.Service.getDesignationById(Long.valueOf(bankId));
	  }
	  
	  @PutMapping({"/bank/edit/{bankId}"})
	  public ResponseEntity<Bank> updateDepartmentId(@PathVariable("bankId") Long BankId, @RequestBody Bank DepartmentIdDetails) {
	    try {
	      Bank existingDepartment = this.Service.findById(BankId);
	      if (existingDepartment == null)
	        return ResponseEntity.notFound().build(); 
	      existingDepartment.setBankName(DepartmentIdDetails.getBankName());
	      existingDepartment.setAccountNumber(DepartmentIdDetails.getAccountNumber());
	      existingDepartment.setBranchName(DepartmentIdDetails.getBranchName());
	      existingDepartment.setHolderName(DepartmentIdDetails.getHolderName());
	      existingDepartment.setPanNumber(DepartmentIdDetails.getPanNumber());
	      existingDepartment.setIfseCode(DepartmentIdDetails.getIfseCode());
	      this.Service.save(existingDepartment);
	      return ResponseEntity.ok(existingDepartment);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/bank/edit/employee/{id}"})
	  public ResponseEntity<Bank> updateBank(@PathVariable("id") Long employeeId, @RequestBody Bank DepartmentIdDetails) {
	    try {
	      Bank existingDepartment = this.Service.getByEmployeeId(employeeId.longValue());
	      if (existingDepartment == null)
	        return ResponseEntity.notFound().build(); 
	      existingDepartment.setBankName(DepartmentIdDetails.getBankName());
	      existingDepartment.setAccountNumber(DepartmentIdDetails.getAccountNumber());
	      existingDepartment.setBranchName(DepartmentIdDetails.getBranchName());
	      existingDepartment.setHolderName(DepartmentIdDetails.getHolderName());
	      existingDepartment.setPanNumber(DepartmentIdDetails.getPanNumber());
	      existingDepartment.setIfseCode(DepartmentIdDetails.getIfseCode());
	      this.Service.save(existingDepartment);
	      return ResponseEntity.ok(existingDepartment);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/bank/delete/{bankId}"})
	  public ResponseEntity<String> deletDepartmentName(@PathVariable("bankId") Long bankId) {
	    this.Service.deleteById(bankId);
	    return ResponseEntity.ok("bank deleted successfully");
	  }
	}

