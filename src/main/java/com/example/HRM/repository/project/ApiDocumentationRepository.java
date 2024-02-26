package com.example.HRM.repository.project;
import com.example.HRM.entity.project.ApiDocumentation;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ApiDocumentationRepository extends JpaRepository<ApiDocumentation, Long> {
	  @Query(value = "select ad.api_documentation_id,ad.date,ad.employee_id,ad.project_id from api_documentation as ad", nativeQuery = true)
	  List<Map<String, Object>> getAllApiDocumentation();
	}
