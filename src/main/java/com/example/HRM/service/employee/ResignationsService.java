package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Resignations;
import com.example.HRM.repository.employee.ResignationsRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResignationsService {
	@Autowired
	  private ResignationsRepository repo;
	  
	  public List<Resignations> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(Resignations resignations) {
	    this.repo.save(resignations);
	  }
	  
	  public Resignations getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	  
	  public List<Map<String, Object>> ALLOver() {
	    return this.repo.AllGoat();
	  }
	  
	  public Optional<Resignations> getResignationByEmployeeId(long employeeId) {
	    return this.repo.findById(Long.valueOf(employeeId));
	  }
	}

