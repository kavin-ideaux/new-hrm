package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Bank;
import com.example.HRM.repository.employee.BankRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
	@Autowired
	  private BankRepository repo;
	  
	  public Iterable<Bank> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(Bank bank) {
	    this.repo.save(bank);
	  }
	  
	  public Bank getByEmployeeId(long id) {
	    return this.repo.findByEmployeeId(id).get();
	  }
	  
	  public void save(Bank bank) {
	    this.repo.save(bank);
	  }
	  
	  public Bank findById(Long bank) {
	    return this.repo.findById(bank).get();
	  }
	  
	  public void deleteDepartmentRollById(Long bank) {
	    this.repo.deleteById(bank);
	  }
	  
	  public Optional<Bank> getdepartmentRollById(Long bank) {
	    return this.repo.findById(bank);
	  }
	  
	  public void deleteById(Long bankId) {
	    this.repo.deleteById(bankId);
	  }
	  
	  public Optional<Bank> getDesignationById(Long bankId) {
	    return this.repo.findById(bankId);
	  }
	}


