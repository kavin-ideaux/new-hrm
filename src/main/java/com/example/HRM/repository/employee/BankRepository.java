package com.example.HRM.repository.employee;

import com.example.HRM.entity.employee.Bank;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface BankRepository extends JpaRepository<Bank, Long> {
	  @Query(value = "SELECT e.*, d.designation_name, dd.department_name FROM employee AS e JOIN designation AS d ON d.designation_id = e.designation_id JOIN department AS dd ON dd.department_id = e.department_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllRoleByEmployees3(@Param("employeeId") Long paramLong);
	  
	  @Query(value = "SELECT e.user_name, e.user_id, b.* FROM bank AS b JOIN employee AS e ON e.employee_id = b.employee_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllBank(@Param("employeeId") Long paramLong);
	  
	  @Query(value = "SELECT e.user_name, e.user_id, p.* FROM personal AS p JOIN employee AS e ON e.employee_id = p.employee_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllPersonal(@Param("employeeId") Long paramLong);
	  
	  @Query(value = "SELECT e.user_name, e.user_id, c.* FROM contacts AS c JOIN employee AS e ON e.employee_id = c.employee_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllEmergencyContacts(@Param("employeeId") Long paramLong);
	  
	  @Query(value = "SELECT q.qualification_id, q.employee_id, q.highest_qualification,        q.resumeurl, q.tenurl, q.aadharurl,        q.degreeurl, q.pannourl, q.twelveurl, q.aadharno, q.status,        e.user_name, e.user_id FROM qualification AS q JOIN employee AS e ON e.employee_id = q.employee_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getQualifications(@Param("employeeId") Long paramLong);
	  
	  @Query(value = " select e.user_name,e.user_id,f.* from family as f  join employee as e on e.employee_id= f.employee_id where e.employee_id=:employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllFamilyInformations(@Param("employeeId") Long paramLong);
	  
	  Optional<Bank> findByEmployeeId(long paramLong);
	}

