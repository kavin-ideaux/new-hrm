package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Complaints;
import com.example.HRM.repository.employee.ComplaintsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintsService {
	@Autowired
	  private ComplaintsRepository repo;
	  
	  public List<Complaints> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(Complaints complaints) {
	    this.repo.save(complaints);
	  }
	  
	  public Complaints getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	  
	  public Optional<Complaints> getById1(long id) {
	    return Optional.of(this.repo.findById(Long.valueOf(id)).get());
	  }
	}

