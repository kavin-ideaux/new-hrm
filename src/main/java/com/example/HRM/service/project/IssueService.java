package com.example.HRM.service.project;
import com.example.HRM.entity.project.Issue;
import com.example.HRM.repository.project.IssueRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

	@Autowired
	  private IssueRepository repo;
	  
	  public Iterable<Issue> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(Issue issue) {
	    this.repo.save(issue);
	  }
	  
	  public void save(Issue issue) {
	    this.repo.save(issue);
	  }
	  
	  public Issue findById(Long Issue) {
	    return this.repo.findById(Issue).get();
	  }
	  
	  public void deleteDepartmentRollById(Long Issue) {
	    this.repo.deleteById(Issue);
	  }
	  
	  public Optional<Issue> getdepartmentRollById(Long Issue) {
	    return this.repo.findById(Issue);
	  }
	  
	  public void deleteById(Long IssueId) {
	    this.repo.deleteById(IssueId);
	  }
	}

