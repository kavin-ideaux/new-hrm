package com.example.HRM.service.project;
import com.example.HRM.entity.project.Task;
import com.example.HRM.repository.project.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	 @Autowired
	  private TaskRepository taskRepository;
	  
	  public List<Task> TaskAssignedForEmployees() {
	    return this.taskRepository.findAll();
	  }
	  
	  public void saveTaskAssigned(Task task) {
	    this.taskRepository.save(task);
	  }
	  
	  public void deleteTask(Long id) {
	    this.taskRepository.deleteById(id);
	  }
	  
	  public Task updateTask(Long id) {
	    return this.taskRepository.findById(id).get();
	  }
	}

