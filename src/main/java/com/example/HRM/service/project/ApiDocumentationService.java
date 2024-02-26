package com.example.HRM.service.project;
import com.example.HRM.entity.project.ApiDocumentation;
import com.example.HRM.repository.project.ApiDocumentationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiDocumentationService {
	 @Autowired
	  private ApiDocumentationRepository apiDocumentationRepository;
	  
	  public List<ApiDocumentation> apiDocumentationView() {
	    return this.apiDocumentationRepository.findAll();
	  }
	  
	  public void saveApiDocumentation(ApiDocumentation user) {
	    this.apiDocumentationRepository.save(user);
	  }
	  
	  public void deleteApiDocumentation(Long id) {
	    this.apiDocumentationRepository.deleteById(id);
	  }
	  
	  public ApiDocumentation findApiDocumentationById(Long id) {
	    return this.apiDocumentationRepository.findById(id).get();
	  }
	}

