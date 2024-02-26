package com.example.HRM.repository.employee;
import com.example.HRM.entity.employee.Resignations;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface ResignationsRepository extends JpaRepository<Resignations, Long> {
	  @Query(value = " select e.user_name ,e.user_id ,d.* from resignations as d join employee as e on e.employee_id=d.employee_id", nativeQuery = true)
	  List<Map<String, Object>> AllGoat();
	  
	  @Query(value = "select c.employee_id,c.status from resignations as c where c.employee_id = :employee_id", nativeQuery = true)
	  Optional<Resignations> getAllEmployeeIdByQualification(Long employee_id);
	  
	  @Query(value = "select c.employee_id,c.status from resignations as c where c.employee_id = :employee_id", nativeQuery = true)
	  List<Map<String, Object>> getAllEmployeeIdByQualification1(Long employee_id);
	  
	  @Query(value = " select e.user_name ,e.user-id ,d.* from resignations as d join employee as e on e.employee_id=d.employee_id where d.employee_id=:employee_id", nativeQuery = true)
	  List<Map<String, Object>> AllTimeGoat(@Param("employee_id") long paramLong);
	  
	  @Query(value = "select e.user_name ,e.user-id ,d.*\t\t\t from resignations as d\t\t\t  join employee as e on e.employee_id=d.employee_id              where d.resignations_date between :startDate and :endDate", nativeQuery = true)
	  List<Map<String, Object>> getAllReceiptBetweenDate(@Param("startDate") LocalDate paramLocalDate1, @Param("endDate") LocalDate paramLocalDate2);
	  
	  @Query(value = "select e.user_name ,e.user-id, d.resignations_id, d.notice_date, d.reason, d.resignations_date, d.status,       datediff( d.resignations_date,d.notice_date) as duration_date from resignations as d  join employee as e on e.employee_id = d.employee_id;", nativeQuery = true)
	  List<Map<String, Object>> getAllDurationDate();
	}

