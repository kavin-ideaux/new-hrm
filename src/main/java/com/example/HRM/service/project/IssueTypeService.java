package com.example.HRM.service.project;
import com.example.HRM.entity.project.IssueType;
import com.example.HRM.repository.project.IssueTypeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueTypeService {
	@Autowired
	  private IssueTypeRepository repo;
	  
	  public Iterable<IssueType> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(IssueType issueType) {
	    this.repo.save(issueType);
	  }
	  
	  public void save(IssueType issueType) {
	    this.repo.save(issueType);
	  }
	  
	  public IssueType findById(Long IssueType) {
	    return this.repo.findById(IssueType).get();
	  }
	  
	  public void deleteDepartmentRollById(Long IssueType) {
	    this.repo.deleteById(IssueType);
	  }
	  
	  public Optional<IssueType> getdepartmentRollById(Long IssueType) {
	    return this.repo.findById(IssueType);
	  }
	  
	  public void deleteById(Long IssueTypeId) {
	    this.repo.deleteById(IssueTypeId);
	  }
	}

