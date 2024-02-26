package com.example.HRM.service.eRecruitment;
import com.example.HRM.entity.erecruitment.TaskAssigned;
import com.example.HRM.repository.erecruitment.TaskAssignedRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAssignedService {

	@Autowired
	  private TaskAssignedRepository taskAssignedRepository;
	  
	  public List<TaskAssigned> listAll() {
	    return this.taskAssignedRepository.findAll();
	  }
	  
	  public TaskAssigned SaveTaskAssigned(TaskAssigned taskAssigned) {
	    return (TaskAssigned)this.taskAssignedRepository.save(taskAssigned);
	  }
	  
	  public TaskAssigned findById(Long taskAssignedId) {
	    return this.taskAssignedRepository.findById(taskAssignedId).get();
	  }
	  
	  public void deleteTaskAssignedId(Long id) {
	    this.taskAssignedRepository.deleteById(id);
	  }
	}
