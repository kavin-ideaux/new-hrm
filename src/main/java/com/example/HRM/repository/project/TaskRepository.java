package com.example.HRM.repository.project;

import com.example.HRM.entity.project.Task;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task, Long> {
	  Task findByTicketNumber(String paramString);
	  
	  Optional<Task> findTopByOrderByTicketNumberDesc();
	}
