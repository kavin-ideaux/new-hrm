package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.EmployeeLeave;
import com.example.HRM.repository.employee.EmployeeLeaveRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLeaveService {

	 @Autowired
	  private EmployeeLeaveRepository repo;
	  
	  public List<EmployeeLeave> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(EmployeeLeave employeeLeave) {
	    this.repo.save(employeeLeave);
	  }
	  
	  public EmployeeLeave getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	}

