package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.CompanyPropertyList;
import com.example.HRM.repository.employee.CompanyPropertyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyPropertyListService {

	 @Autowired
	  private CompanyPropertyListRepository repo;
	  
	  public void save(CompanyPropertyList companyPropertyList) {
	    this.repo.save(companyPropertyList);
	  }
	  
	  public CompanyPropertyList findById(Long companyPropertyListId) {
	    return this.repo.findById(companyPropertyListId).get();
	  }
	}

