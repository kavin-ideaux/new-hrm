package com.example.HRM.service.project;
import com.example.HRM.entity.project.ProjectAssigned;
import com.example.HRM.repository.project.ProjectAssignedRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectAssignedService {

	@Autowired
	  private ProjectAssignedRepository projectAssignedRepository;
	  
	  public List<ProjectAssigned> projectAssignedForEmployees() {
	    return this.projectAssignedRepository.findAll();
	  }
	  
	  public void saveProjectAssignedForEmployees(ProjectAssigned user) {
	    this.projectAssignedRepository.save(user);
	  }
	  
	  public void deleteProjectAssignedForEmployees(Long id) {
	    this.projectAssignedRepository.deleteById(id);
	  }
	  
	  public ProjectAssigned findProjectAssignedForEmployeesById(Long id) {
	    return this.projectAssignedRepository.findById(id).get();
	  }
	}

