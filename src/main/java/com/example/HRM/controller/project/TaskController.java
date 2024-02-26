package com.example.HRM.controller.project;

import com.example.HRM.entity.project.Task;
import com.example.HRM.repository.project.TaskRepository;
import com.example.HRM.service.project.TaskService;
import java.util.HashMap;
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
public class TaskController {
	@Autowired
	  private TaskService taskService;
	  
	  @Autowired
	  private TaskRepository taskRepository;
	  
	  @GetMapping({"/task/view"})
	  public ResponseEntity<Object> getUserDetails(@RequestParam(required = true) String task) {
	    if ("givenTask".equals(task))
	      return ResponseEntity.ok(this.taskService.TaskAssignedForEmployees()); 
	    String errorMessage = "Invalid value for 'task'. Expected 'givenTask'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/task/save"})
	  public ResponseEntity<String> saveTask(@RequestBody Task task) {
	    try {
	      if (task.getTaskProcess().equals("todo")) {
	        task.setCompleted(false);
	        task.setInProgress(false);
	        task.setMoved(false);
	        task.setTodo(true);
	      } else if (task.getTaskProcess().equals("completed")) {
	        task.setCompleted(true);
	        task.setInProgress(false);
	        task.setMoved(false);
	        task.setTodo(false);
	      } else if (task.getTaskProcess().equals("inProgress")) {
	        task.setCompleted(false);
	        task.setInProgress(true);
	        task.setMoved(false);
	        task.setTodo(false);
	      } else if (task.getTaskProcess().equals("moved")) {
	        task.setCompleted(false);
	        task.setInProgress(false);
	        task.setMoved(true);
	        task.setTodo(false);
	      } else {
	        task.setCompleted(false);
	        task.setInProgress(false);
	        task.setMoved(false);
	        task.setTodo(false);
	      } 
	      this.taskService.saveTaskAssigned(task);
	      long taskId = task.getTaskId();
	      return ResponseEntity.ok("Task saved successfully. Task ID: " + taskId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Task: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/task/edit/{id}"})
	  public ResponseEntity<Task> updateTask(@PathVariable("id") Long taskId, @RequestBody Task updatedTask) {
	    try {
	      Task existingTask = this.taskService.updateTask(taskId);
	      if (existingTask == null)
	        return ResponseEntity.notFound().build(); 
	      existingTask.setDescription(updatedTask.getDescription());
	      existingTask.setTaskName(updatedTask.getTaskName());
	      existingTask.setEmployeeId(updatedTask.getEmployeeId());
	      existingTask.setEndDate(updatedTask.getEndDate());
	      existingTask.setStartDate(updatedTask.getStartDate());
	      existingTask.setProjectId(updatedTask.getProjectId());
	      existingTask.setTicketNumber(updatedTask.getTicketNumber());
	      existingTask.setTaskProcess(updatedTask.getTaskProcess());
	      String updatedTaskProcess = updatedTask.getTaskProcess();
	      switch (updatedTaskProcess) {
	        case "todo":
	          existingTask.setCompleted(false);
	          existingTask.setInProgress(false);
	          existingTask.setMoved(false);
	          existingTask.setTodo(true);
	          break;
	        case "completed":
	          existingTask.setCompleted(true);
	          existingTask.setInProgress(false);
	          existingTask.setMoved(false);
	          existingTask.setTodo(false);
	          break;
	        case "inProgress":
	          existingTask.setCompleted(false);
	          existingTask.setInProgress(true);
	          existingTask.setMoved(false);
	          existingTask.setTodo(false);
	          break;
	        case "moved":
	          existingTask.setCompleted(false);
	          existingTask.setInProgress(false);
	          existingTask.setMoved(true);
	          existingTask.setTodo(false);
	          break;
	        default:
	          existingTask.setCompleted(false);
	          existingTask.setInProgress(false);
	          existingTask.setMoved(false);
	          existingTask.setTodo(false);
	          break;
	      } 
	      if (updatedTask.getTaskProcess().equals("completed"));
	      this.taskService.saveTaskAssigned(existingTask);
	      return ResponseEntity.ok(existingTask);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/task/delete/{id}"})
	  public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long taskId) {
	    this.taskService.deleteTask(taskId);
	    return ResponseEntity.ok("task was deleted successfully");
	  }
	  
	  @GetMapping({"/generate/ticket"})
	  public Map<String, Object> generateProjectNumber() {
	    Optional<Task> lastTicket = this.taskRepository.findTopByOrderByTicketNumberDesc();
	    int nextTicketNumber = 1;
	    if (lastTicket.isPresent()) {
	      String lastTicketNumber = ((Task)lastTicket.get()).getTicketNumber();
	      try {
	        nextTicketNumber = Integer.parseInt(lastTicketNumber.substring(1)) + 1;
	      } catch (NumberFormatException numberFormatException) {}
	    } 
	    String ticketNumber = String.format("T%03d", new Object[] { Integer.valueOf(nextTicketNumber) });
	    Task taskNumberEntity = new Task();
	    taskNumberEntity.setTicketNumber(ticketNumber);
	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("ticketNumber", ticketNumber);
	    return responseData;
	  }
	}
