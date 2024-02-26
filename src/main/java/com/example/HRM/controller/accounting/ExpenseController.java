package com.example.HRM.controller.accounting;
import com.example.HRM.entity.accounting.Expense;
import com.example.HRM.repository.accounting.ExpenseRepository;
import com.example.HRM.service.accounting.ExpenseService;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ExpenseController {
	 @Autowired
	  private ExpenseService expenseService;
	  
	  @Autowired
	  private ExpenseRepository repo;
	  
	  @GetMapping({"/expense"})
	  public ResponseEntity<?> getDetails() {
	    try {
	      Iterable<Expense> expenseDetails = this.expenseService.listAll();
	      return new ResponseEntity(expenseDetails, HttpStatus.OK);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while retrieving l details.";
	      return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	  }
	  
	  @PostMapping({"/expense/save"})
	  public ResponseEntity<?> saveBank(@RequestBody Expense expense) {
	    try {
	      expense.setStatus(true);
	      this.expenseService.SaveorUpdate(expense);
	      return ResponseEntity.status(HttpStatus.CREATED).body("expense details saved successfully.");
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving expense details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @RequestMapping({"/expense/{expenseId}"})
	  private Optional<Expense> getExpense(@PathVariable(name = "expenseId") long expenseId) {
	    return this.expenseService.getExpenseById(Long.valueOf(expenseId));
	  }
	  
	  @PutMapping({"/expense/or/{id}"})
	  public ResponseEntity<Boolean> toggleCustomerStatus(@PathVariable(name = "id") long id) {
	    try {
	      Expense expense = this.expenseService.findById(Long.valueOf(id));
	      if (expense != null) {
	        boolean currentStatus = expense.isStatus();
	        expense.setStatus(!currentStatus);
	        this.expenseService.SaveorUpdate(expense);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(expense.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/expense/editdeexpense/{expenseId}"})
	  public ResponseEntity<Expense> updateExpense(@PathVariable("expenseId") Long expenseId, @RequestBody Expense expenseDetails) {
	    try {
	      Expense existingexpense = this.expenseService.findById(expenseId);
	      if (existingexpense == null)
	        return ResponseEntity.notFound().build(); 
	      existingexpense.setExpenseTypeId(expenseDetails.getExpenseTypeId());
	      existingexpense.setExpenseName(expenseDetails.getExpenseName());
	      existingexpense.setDate(expenseDetails.getDate());
	      existingexpense.setDescription(expenseDetails.getDescription());
	      existingexpense.setAmount(expenseDetails.getAmount());
	      existingexpense.setStatus(expenseDetails.isStatus());
	      this.expenseService.save(existingexpense);
	      return ResponseEntity.ok(existingexpense);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/expense/expensedelete/{expenseId}"})
	  public ResponseEntity<String> deleteAmount(@PathVariable("expenseId") Long expenseId) {
	    this.expenseService.deleteExpenseIdById(expenseId);
	    return ResponseEntity.ok("expense deleted successfully");
	  }
	  
	  @GetMapping({"/expensedetails/view"})
	  public List<Map<String, Object>> allDeptDetails() {
	    return this.expenseService.allExpenseDetails();
	  }
	}


