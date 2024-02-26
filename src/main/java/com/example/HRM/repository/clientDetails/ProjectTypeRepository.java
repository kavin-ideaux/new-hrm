package com.example.HRM.repository.clientDetails;
import com.example.HRM.entity.clientDetails.ProjectType;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
	  @Query(value = " select c.project_id as projectId, c.client_id as clientId,c.duration ,c.date,c.project_name as projectName,  c.services,p.project_type_id as projectTypeId,p.project_type as projectType from client_requirements as c  join project_type as p on p.project_id=c.project_id", nativeQuery = true)
	  List<Map<String, Object>> findProjectTypeDetails();
	}
