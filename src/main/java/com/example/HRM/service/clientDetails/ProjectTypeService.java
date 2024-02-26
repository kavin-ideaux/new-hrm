package com.example.HRM.service.clientDetails;
import com.example.HRM.entity.clientDetails.ProjectType;
import com.example.HRM.repository.clientDetails.ProjectTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTypeService {

	@Autowired
	  private ProjectTypeRepository projectTypeRepository;
	  
	  public List<ProjectType> listAll() {
	    return this.projectTypeRepository.findAll();
	  }
	  
	  public void SaveProjectType(ProjectType projectType) {
	    this.projectTypeRepository.save(projectType);
	  }
	  
	  public ProjectType findById(Long id) {
	    return this.projectTypeRepository.findById(id).get();
	  }
	  
	  public void deleteProjectTypeId(Long id) {
	    this.projectTypeRepository.deleteById(id);
	  }
	}

