package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.EmployeeExit;
import com.example.HRM.repository.employee.EmployeeExitRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeExitService {
	@Autowired
	  private EmployeeExitRepository repo;
	  
	  public List<EmployeeExit> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(EmployeeExit employeeExit) {
	    this.repo.save(employeeExit);
	  }
	  
	  public EmployeeExit getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	}
