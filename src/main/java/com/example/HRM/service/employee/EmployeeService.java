package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Employee;
import com.example.HRM.repository.employee.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	  private EmployeeRepository repo;
	  
	  public List<Employee> listAll1() {
	    return this.repo.findAll();
	  }
	  
	  public List<Employee> listAll() {
	    List<Employee> allEmployees = this.repo.findAll();
	    List<Employee> trueStatusEmployees = new ArrayList<>();
	    for (Employee employee : allEmployees) {
	      if (employee.isStatus())
	        trueStatusEmployees.add(employee); 
	    } 
	    return trueStatusEmployees;
	  }
	  
	  public void saveOrUpdate(Employee employee) {
	    this.repo.save(employee);
	  }
	  
	  public Employee getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	  
	  public Employee getEmployeeById(Long employeeId) {
	    return this.repo.findById(employeeId).orElse(null);
	  }
	}

