package com.example.HRM.repository.employee;
import com.example.HRM.entity.employee.EmployeeExit;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface EmployeeExitRepository extends JpaRepository<EmployeeExit, Long> {
	  @Query(value = "select e.user_name ,c.* from employeeexit as c join employee as e on e.employee_id=c.employee_id", nativeQuery = true)
	  List<Map<String, Object>> getAllProjectWork();
	}

