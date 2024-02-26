package com.example.HRM.repository.employee;
import com.example.HRM.entity.employee.EmergencyContacts;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmergencyContactsRepository extends JpaRepository<EmergencyContacts, Long> {
	  Optional<EmergencyContacts> findByEmployeeId(long paramLong);
	  
	  @Query(value = "SELECT e.user_name, e.user_id, c.* FROM contacts AS c JOIN employee AS e ON e.employee_id = c.employee_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllEmergencyContactsByEmployeeId(@Param("employeeId") Long paramLong);
	}