package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.EmployeeAttendance;
import com.example.HRM.repository.employee.EmployeeAttendanceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAttendanceService {

	 @Autowired
	  private EmployeeAttendanceRepository repo;
	  
	  public List<EmployeeAttendance> listAll1() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(EmployeeAttendance employeeAtt) {
	    this.repo.save(employeeAtt);
	  }
	  
	  public EmployeeAttendance getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	  
	  public EmployeeAttendance getEmployeeById(Long employeeAttId) {
	    return this.repo.findById(employeeAttId).orElse(null);
	  }
	  
	  public void save(EmployeeAttendance existingEntry) {
	    this.repo.save(existingEntry);
	  }
	}

