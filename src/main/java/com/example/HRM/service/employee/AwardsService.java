package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Awards;
import com.example.HRM.repository.employee.AwardsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwardsService {
	@Autowired
	  private AwardsRepository awardsRepository;
	  
	  public void create(Awards awards) {
	    this.awardsRepository.save(awards);
	  }
	  
	  public Optional<Awards> findById(long awardsId) {
	    return this.awardsRepository.findById(Long.valueOf(awardsId));
	  }
	  
	  public Awards createAwards(Awards awards) {
	    return (Awards)this.awardsRepository.save(awards);
	  }
	  
	  public Optional<Awards> getAwardsById(long awardsId) {
	    return this.awardsRepository.findById(Long.valueOf(awardsId));
	  }
	  
	  public List<Awards> getAllAwards() {
	    return this.awardsRepository.findAll();
	  }
	  
	  public Awards update(Awards awards) {
	    return (Awards)this.awardsRepository.save(awards);
	  }
	}

