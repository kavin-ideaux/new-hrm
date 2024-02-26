package com.example.HRM.service.organization;
import com.example.HRM.entity.organization.Company;
import com.example.HRM.repository.organization.CompanyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

	 @Autowired
	  private CompanyRepository repo;
	  
	  public List<Company> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(Company company) {
	    this.repo.save(company);
	  }
	  
	  public void save(Company company) {
	    this.repo.save(company);
	  }
	  
	  public Company findById(Long companyId) {
	    return this.repo.findById(companyId).get();
	  }
	  
	  public void deleteCompanyById(Long companyId) {
	    this.repo.deleteById(companyId);
	  }
	  
	  public Company getCompanyById(Long companyId) {
	    return this.repo.findById(companyId).orElse(null);
	  }
	}

