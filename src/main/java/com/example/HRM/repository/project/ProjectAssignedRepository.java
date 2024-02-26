package com.example.HRM.repository.project;
import com.example.HRM.entity.project.ProjectAssigned;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ProjectAssignedRepository extends JpaRepository<ProjectAssigned, Long> {
	  ProjectAssigned findByProjectNumber(String paramString);
	  
	  Optional<ProjectAssigned> findTopByOrderByProjectNumberDesc();
	  
	  @Query(value = "select case when t.completed = 1 and t.completed_date <= t.end_date then 'task completed on time' when t.completed = 1 and t.completed_date > t.end_date then 'task not completed on time' else 'other' end as completion_status, count(*) as task_count from task as t where t.employee_id = :employee_id group by completion_status", nativeQuery = true)
	  List<Map<String, Object>> getAllProjectCompletionAndInCompletionCount(Long employee_id);
	}