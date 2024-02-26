package com.example.HRM.repository.employee;

import com.example.HRM.entity.employee.Qualification;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
	@Query(value = "select q.qualification_id, q.employee_id, q.highest_qualification,\t\t\t  q.resumeurl, q.tenurl, q.aadharurl,\t\t\t\t\t q.degreeurl, q.pannourl, q.twelveurl,q.aadharno,q.status,\t\t\t\t e.user_name, e.user_id\t\t\t\t\t\t from qualification as q\t\t\t\tjoin employee as e on e.employee_id = q.employee_id", nativeQuery = true)
	List<Map<String, Object>> getAllQualificationsByImage();

	Optional<Qualification> findByEmployeeId(long paramLong);

	@Query(value = "SELECT q.qualification_id, q.employee_id, q.highest_qualification,        q.resumeurl, q.tenurl, q.aadharurl,        q.degreeurl, q.pannourl, q.twelveurl, q.aadharno, q.status,        e.user_name, e.user_id  FROM qualification AS q  JOIN employee AS e ON e.employee_id = q.employee_id  WHERE e.employee_id = :employeeId", nativeQuery = true)
	List<Map<String, Object>> getQualifications(@Param("employeeId") Long paramLong);
}
