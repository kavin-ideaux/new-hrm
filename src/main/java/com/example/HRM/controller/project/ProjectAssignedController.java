package com.example.HRM.controller.project;
import com.example.HRM.entity.project.ProjectAssigned;
import com.example.HRM.repository.project.ProjectAssignedRepository;
import com.example.HRM.service.project.ProjectAssignedService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public class ProjectAssignedController {
	@Autowired
	  private ProjectAssignedService projectAssignedService;
	  
	  @Autowired
	  private ProjectAssignedRepository projectAssignedRepository;
	  
	  @GetMapping({"/project/assigned"})
	  public ResponseEntity<Object> getUserDetails(@RequestParam(required = true) String user) {
	    if ("project".equals(user))
	      return ResponseEntity.ok(this.projectAssignedService.projectAssignedForEmployees()); 
	    String errorMessage = "Invalid value for 'user'. Expected 'project'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/project/assigned/save"})
	  private long saveSingleUser(@RequestBody ProjectAssigned project) {
	    if (project.getProcess().equals("started")) {
	      project.setStarted(true);
	      project.setCompleted(false);
	      project.setPending(false);
	      project.setHold(false);
	      project.setOnProcess(false);
	    } else if (project.getProcess().equals("completed")) {
	      project.setStarted(false);
	      project.setCompleted(true);
	      project.setPending(false);
	      project.setHold(false);
	      project.setOnProcess(false);
	    } else if (project.getProcess().equals("pending")) {
	      project.setStarted(false);
	      project.setCompleted(false);
	      project.setPending(true);
	      project.setHold(false);
	      project.setOnProcess(false);
	    } else if (project.getProcess().equals("hold")) {
	      project.setStarted(false);
	      project.setCompleted(false);
	      project.setPending(false);
	      project.setHold(true);
	      project.setOnProcess(false);
	    } else if (project.getProcess().equals("onProcess")) {
	      project.setStarted(false);
	      project.setCompleted(false);
	      project.setPending(false);
	      project.setHold(false);
	      project.setOnProcess(true);
	    } else {
	      project.setStarted(false);
	      project.setCompleted(false);
	      project.setPending(false);
	      project.setHold(false);
	      project.setOnProcess(false);
	    } 
	    this.projectAssignedService.saveProjectAssignedForEmployees(project);
	    return project.getProjectAssignedId();
	  }
	  
	  @PutMapping({"/project/assigned/edit/{id}"})
	  public ResponseEntity<?> updateOrder(@PathVariable("id") Long projectAssignedId, @RequestBody ProjectAssigned project) {
	    try {
	      ProjectAssigned existingProjectAssigned = this.projectAssignedService.findProjectAssignedForEmployeesById(projectAssignedId);
	      if (existingProjectAssigned == null)
	        return ResponseEntity.notFound().build(); 
	      existingProjectAssigned.setCompleted(project.isCompleted());
	      existingProjectAssigned.setPending(project.isPending());
	      existingProjectAssigned.setHold(project.isHold());
	      existingProjectAssigned.setOnProcess(project.isOnProcess());
	      existingProjectAssigned.setEndDate(project.getEndDate());
	      existingProjectAssigned.setStartDate(project.getStartDate());
	      existingProjectAssigned.setDepartmentId(project.getDepartmentId());
	      existingProjectAssigned.setProjectNumber(project.getProjectNumber());
	      existingProjectAssigned.setProcess(project.getProcess());
	      String updatedProject = project.getProcess();
	      if (project.isCompleted() == true) {
	        String message = "already completed";
	        return ResponseEntity.ok(message);
	      } 
	      switch (updatedProject) {
	        case "completed":
	          existingProjectAssigned.setCompleted(true);
	          existingProjectAssigned.setStarted(false);
	          existingProjectAssigned.setPending(false);
	          existingProjectAssigned.setOnProcess(false);
	          existingProjectAssigned.setHold(false);
	          this.projectAssignedService.saveProjectAssignedForEmployees(existingProjectAssigned);
	          return ResponseEntity.ok(existingProjectAssigned);
	        case "started":
	          existingProjectAssigned.setCompleted(false);
	          existingProjectAssigned.setStarted(true);
	          existingProjectAssigned.setPending(false);
	          existingProjectAssigned.setOnProcess(false);
	          existingProjectAssigned.setHold(false);
	          this.projectAssignedService.saveProjectAssignedForEmployees(existingProjectAssigned);
	          return ResponseEntity.ok(existingProjectAssigned);
	        case "pending":
	          existingProjectAssigned.setCompleted(false);
	          existingProjectAssigned.setStarted(false);
	          existingProjectAssigned.setPending(true);
	          existingProjectAssigned.setOnProcess(false);
	          existingProjectAssigned.setHold(false);
	          this.projectAssignedService.saveProjectAssignedForEmployees(existingProjectAssigned);
	          return ResponseEntity.ok(existingProjectAssigned);
	        case "onProcess":
	          existingProjectAssigned.setCompleted(false);
	          existingProjectAssigned.setStarted(false);
	          existingProjectAssigned.setPending(false);
	          existingProjectAssigned.setOnProcess(true);
	          existingProjectAssigned.setHold(false);
	          this.projectAssignedService.saveProjectAssignedForEmployees(existingProjectAssigned);
	          return ResponseEntity.ok(existingProjectAssigned);
	        case "hold":
	          existingProjectAssigned.setCompleted(false);
	          existingProjectAssigned.setStarted(false);
	          existingProjectAssigned.setPending(false);
	          existingProjectAssigned.setOnProcess(false);
	          existingProjectAssigned.setHold(true);
	          this.projectAssignedService.saveProjectAssignedForEmployees(existingProjectAssigned);
	          return ResponseEntity.ok(existingProjectAssigned);
	      } 
	      existingProjectAssigned.setCompleted(false);
	      existingProjectAssigned.setStarted(false);
	      existingProjectAssigned.setPending(false);
	      existingProjectAssigned.setOnProcess(false);
	      existingProjectAssigned.setHold(false);
	      this.projectAssignedService.saveProjectAssignedForEmployees(existingProjectAssigned);
	      return ResponseEntity.ok(existingProjectAssigned);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/project/assigned/delete/{id}"})
	  public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long projectAssignedId) {
	    this.projectAssignedService.deleteProjectAssignedForEmployees(projectAssignedId);
	    return ResponseEntity.ok("project-assigned was deleted successfully");
	  }
	  
	  @GetMapping({"/generate"})
	  public Map<String, Object> generateProjectNumber() {
	    Optional<ProjectAssigned> lastProject = this.projectAssignedRepository.findTopByOrderByProjectNumberDesc();
	    int nextProjectNumber = 1;
	    if (lastProject.isPresent()) {
	      String lastProjectNumber = ((ProjectAssigned)lastProject.get()).getProjectNumber();
	      try {
	        nextProjectNumber = Integer.parseInt(lastProjectNumber.substring(1)) + 1;
	      } catch (NumberFormatException numberFormatException) {}
	    } 
	    String projectNumber = String.format("P%03d", new Object[] { Integer.valueOf(nextProjectNumber) });
	    ProjectAssigned projectNumberEntity = new ProjectAssigned();
	    projectNumberEntity.setProjectNumber(projectNumber);
	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("projectNumber", projectNumber);
	    return responseData;
	  }
	  
	  @GetMapping({"/ok/{employee_id}"})
	  public List<Map<String, Object>> getAllProjectCompletionAndInCompletionCount(@PathVariable("employee_id") Long employee_id) {
	    return this.projectAssignedRepository.getAllProjectCompletionAndInCompletionCount(employee_id);
	  }
	}

