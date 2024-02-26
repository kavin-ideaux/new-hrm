package com.example.HRM.service.employee;

import com.example.HRM.entity.employee.Promotions;
import com.example.HRM.repository.employee.PromotionsRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionsService {
	@Autowired
	  private PromotionsRepository repo;
	  
	  public List<Promotions> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(Promotions promotions) {
	    this.repo.save(promotions);
	  }
	  
	  public Promotions getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	  
	  public List<Map<String, Object>> AllFine() {
	    return this.repo.GoodAllWorks();
	  }
	}


