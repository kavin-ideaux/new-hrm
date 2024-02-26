package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Assest;
import com.example.HRM.repository.employee.AssestRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssestService {
	@Autowired
	  private AssestRepository Repo;
	  
	  public Iterable<Assest> listAll() {
	    return this.Repo.findAll();
	  }
	  
	  public void SaveorUpdate(Assest assest) {
	    this.Repo.save(assest);
	  }
	  
	  public void save(Assest assest) {
	    this.Repo.save(assest);
	  }
	  
	  public Assest findById(Long assest_id) {
	    return this.Repo.findById(assest_id).get();
	  }
	  
	  public void deleteAssestIdById(Long assest_id) {
	    this.Repo.deleteById(assest_id);
	  }
	  
	  public Assest getById(long id) {
	    return this.Repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public List<Map<String, Object>> allAssestDetails() {
	    return this.Repo.allAsesstDetails();
	  }
	}

