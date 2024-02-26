package com.example.HRM.controller.clientDetails;

import com.example.HRM.entity.clientDetails.ProjectType;
import com.example.HRM.repository.clientDetails.ProjectTypeRepository;
import com.example.HRM.service.clientDetails.ProjectTypeService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProjectTypeController {

	@Autowired
	  private ProjectTypeService projectTypeService;
	  
	  @Autowired
	  private ProjectTypeRepository projectTypeRepository;
	  
	  @GetMapping({"/projectType/view"})
	  public ResponseEntity<Object> getProjectType(@RequestParam(required = true) String projectType) {
	    if ("projectTypeDetails".equals(projectType))
	      return ResponseEntity.ok(this.projectTypeService.listAll()); 
	    String errorMessage = "Invalid value for 'projectType'. Expected 'projectType'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/projectType/save"})
	  public ResponseEntity<String> saveAppointment(@RequestBody ProjectType projectType) {
	    try {
	      this.projectTypeService.SaveProjectType(projectType);
	      long projectTypeId = projectType.getProjectTypeId();
	      return ResponseEntity.ok("ProjectType saved successfully. ProjectType ID: " + projectTypeId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving projectType: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/updateProjectType/{id}"})
	  public ResponseEntity<ProjectType> updateAppointment(@PathVariable("id") long id, @RequestBody ProjectType projectType) {
	    try {
	      ProjectType existingprojectType = this.projectTypeService.findById(Long.valueOf(id));
	      if (existingprojectType == null)
	        return ResponseEntity.notFound().build(); 
	      existingprojectType.setProjectType(projectType.getProjectType());
	      this.projectTypeService.SaveProjectType(existingprojectType);
	      return ResponseEntity.ok(existingprojectType);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/projectType/delete/{id}"})
	  public ResponseEntity<String> deleteProjectType(@PathVariable("id") Long id) {
	    this.projectTypeService.deleteProjectTypeId(id);
	    return ResponseEntity.ok("ProjectType details deleted successfully");
	  }
	  
	  @GetMapping({"/findProjectTypeDetails"})
	  public ResponseEntity<?> getProjectTypeDetails() {
	    try {
	      List<Map<String, Object>> projectTypes = this.projectTypeRepository.findProjectTypeDetails();
	      List<Map<String, Object>> projectTypeList = new ArrayList<>();
	      for (Map<String, Object> projectType : projectTypes) {
	        Map<String, Object> projectTypeMap = new HashMap<>();
	        String fileUploadUrl = "/fileUpload/" + projectType.get("projectId");
	        projectTypeMap.put("fileUploadUrl", fileUploadUrl);
	        projectTypeMap.putAll(projectType);
	        projectTypeList.add(projectTypeMap);
	      } 
	      return ResponseEntity.ok(projectTypeList);
	    } catch (Exception e) {
	      String errorMessage = "Error occurred while retrieving project type details";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Collections.singletonMap("error", errorMessage));
	    } 
	  }
	}

