package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Department;
import com.example.HRM.repository.employee.DepartmentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
	  private DepartmentRepository repo;
	  
	  public Iterable<Department> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(Department department) {
	    this.repo.save(department);
	  }
	  
	  public void save(Department department) {
	    this.repo.save(department);
	  }
	  
	  public Department findById(Long Department) {
	    return this.repo.findById(Department).get();
	  }
	  
	  public void deleteDepartmentRollById(Long Department) {
	    this.repo.deleteById(Department);
	  }
	  
	  public Optional<Department> getdepartmentRollById(Long Department) {
	    return this.repo.findById(Department);
	  }
	  
	  public void deleteById(Long DepartmentId) {
	    this.repo.deleteById(DepartmentId);
	  }
	}

