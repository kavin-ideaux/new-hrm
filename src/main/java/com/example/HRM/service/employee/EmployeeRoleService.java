package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.EmployeeRole;
import com.example.HRM.repository.employee.EmployeeRoleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRoleService {

	 @Autowired
	  private EmployeeRoleRepository repo;
	  
	  public Iterable<EmployeeRole> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(EmployeeRole employeeRole) {
	    this.repo.save(employeeRole);
	  }
	  
	  public void save(EmployeeRole employeeRole) {
	    this.repo.save(employeeRole);
	  }
	  
	  public EmployeeRole findById(Long EmployeeRole) {
	    return this.repo.findById(EmployeeRole).get();
	  }
	  
	  public void deleteEmployeeRoleRollById(Long EmployeeRole) {
	    this.repo.deleteById(EmployeeRole);
	  }
	  
	  public Optional<EmployeeRole> getEmployeeRoleRollById(Long EmployeeRole) {
	    return this.repo.findById(EmployeeRole);
	  }
	  
	  public void deleteById(Long EmployeeRoleId) {
	    this.repo.deleteById(EmployeeRoleId);
	  }
	}

