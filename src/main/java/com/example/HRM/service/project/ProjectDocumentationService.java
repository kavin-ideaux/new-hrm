package com.example.HRM.service.project;

import com.example.HRM.entity.project.ProjectDocumentation;
import com.example.HRM.repository.project.ProjectDocumentationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectDocumentationService {

	@Autowired
	  private ProjectDocumentationRepository projectDocumentationRepository;
	  
	  public List<ProjectDocumentation> projectDocumentationView() {
	    return this.projectDocumentationRepository.findAll();
	  }
	  
	  public void saveProjectDocumentation(ProjectDocumentation project) {
	    this.projectDocumentationRepository.save(project);
	  }
	  
	  public void deleteProjectDocumentation(Long id) {
	    this.projectDocumentationRepository.deleteById(id);
	  }
	  
	  public ProjectDocumentation findProjectDocumentationUploadedId(Long id) {
	    return this.projectDocumentationRepository.findById(id).get();
	  }
	}

