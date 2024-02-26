package com.example.HRM.service.accounting;
import com.example.HRM.entity.accounting.ExpenseType;
import com.example.HRM.repository.accounting.ExpenseTypeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseTypeService {

	@Autowired
	  private ExpenseTypeRepository repo;
	  
	  public Iterable<ExpenseType> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(ExpenseType expenseType) {
	    this.repo.save(expenseType);
	  }
	  
	  public void save(ExpenseType expenseType) {
	    this.repo.save(expenseType);
	  }
	  
	  public ExpenseType findById(Long expenseType_id) {
	    return this.repo.findById(expenseType_id).get();
	  }
	  
	  public void deleteExpenseTypeIdById(Long expenseType_id) {
	    this.repo.deleteById(expenseType_id);
	  }
	  
	  public Optional<ExpenseType> getExpenseTypeById(Long expenseType_id) {
	    return this.repo.findById(expenseType_id);
	  }
	}
