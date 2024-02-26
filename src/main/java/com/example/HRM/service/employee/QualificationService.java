package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Qualification;
import com.example.HRM.repository.employee.QualificationRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualificationService {
	@Autowired
	  private QualificationRepository repo;
	  
	  public void create(Qualification qualification) {
	    this.repo.save(qualification);
	  }
	  
	  public List<Qualification> getAllQualifications() {
	    return this.repo.findAll();
	  }
	  
	  public Optional<Qualification> getQualificationsByEmployeeId(long employeeId) {
	    return this.repo.findById(Long.valueOf(employeeId));
	  }
	  
	  public Optional<Qualification> getAwardsPhotoById(Long id) {
	    return this.repo.findById(id);
	  }
	  
	  public Qualification update(Qualification qualification) {
	    return (Qualification)this.repo.save(qualification);
	  }
	  
	  public List<Map<String, Object>> getAllQualificationsByImage() {
	    return this.repo.getAllQualificationsByImage();
	  }
	  
	  public Optional<Qualification> getQualificationById1(long id) {
	    return this.repo.findById(Long.valueOf(id));
	  }
	  
	  public Optional<Qualification> getQualificationById(long id) {
	    return this.repo.findByEmployeeId(id);
	  }
	}

