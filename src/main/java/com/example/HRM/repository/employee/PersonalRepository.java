package com.example.HRM.repository.employee;

import com.example.HRM.entity.employee.Personal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PersonalRepository extends JpaRepository<Personal, Long> {
	  @Query(value = "SELECT e.user_name, e.user_id, p.* FROM personal AS p JOIN employee AS e ON e.employee_id = p.employee_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllPersonal(@Param("employeeId") Long paramLong);
	  
	  Optional<Personal> findByEmployeeId(long paramLong);
	}

