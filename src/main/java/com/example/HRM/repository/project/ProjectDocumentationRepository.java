package com.example.HRM.repository.project;
import com.example.HRM.entity.project.ProjectDocumentation;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ProjectDocumentationRepository extends JpaRepository<ProjectDocumentation, Long> {
	  @Query(value = "select pd.project_documentation_id,pd.date,pd.employee_id,pd.project_id from project_documentation as pd", nativeQuery = true)
	  List<Map<String, Object>> getAllProjectDocumentation();
	}
