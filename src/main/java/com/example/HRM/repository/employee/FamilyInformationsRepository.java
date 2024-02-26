package com.example.HRM.repository.employee;
import com.example.HRM.entity.employee.FamilyInformations;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface FamilyInformationsRepository extends JpaRepository<FamilyInformations, Long> {
	  @Query(value = " select e.user_name,e.user_id,f.* from family as f  join employee as e on e.employee_id= f.employee_id where e.employee_id=employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllFamilyInformations(@Param("employeeId") Long paramLong);
	  
	  Optional<FamilyInformations> findByEmployeeId(long paramLong);
	}

