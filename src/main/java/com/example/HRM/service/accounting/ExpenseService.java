package com.example.HRM.service.accounting;
import com.example.HRM.entity.accounting.Expense;
import com.example.HRM.repository.accounting.ExpenseRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
	@Autowired
	  private ExpenseRepository expenseRepository;
	  
	  public Iterable<Expense> listAll() {
	    return this.expenseRepository.findAll();
	  }
	  
	  public void SaveorUpdate(Expense expense) {
	    this.expenseRepository.save(expense);
	  }
	  
	  public void save(Expense expense) {
	    this.expenseRepository.save(expense);
	  }
	  
	  public Expense findById(Long expense_id) {
	    return this.expenseRepository.findById(expense_id).get();
	  }
	  
	  public void deleteExpenseIdById(Long expense_id) {
	    this.expenseRepository.deleteById(expense_id);
	  }
	  
	  public Optional<Expense> getExpenseById(Long expense_id) {
	    return this.expenseRepository.findById(expense_id);
	  }
	  
	  public List<Map<String, Object>> allExpenseDetails() {
	    return this.expenseRepository.allExpenseDetails();
	  }
	  
	  public List<Map<String, Object>> findAllByExpenseId(Long expense_id) {
	    return this.expenseRepository.allDetailsOfExpense(expense_id);
	  }
	  
	  public List<Map<String, Object>> dailyExpenseByCurrentDate() {
	    return this.expenseRepository.dailyExpenseByCurrentDate();
	  }
	}

