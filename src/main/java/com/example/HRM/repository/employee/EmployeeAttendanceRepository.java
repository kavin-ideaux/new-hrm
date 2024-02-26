package com.example.HRM.repository.employee;
import com.example.HRM.entity.employee.EmployeeAttendance;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
	  Optional<EmployeeAttendance> findByEmployeeIdAndInDate(Long paramLong, LocalDate paramLocalDate);
	  
	  @Query(value = "  SELECT     e.user_name,e.user_id,     e.employee_id,     a.employee_att_id,     a.in_time,     a.out_time,     a.attendance,     a.in_date,     a.punch_in,     a.punch_out,     TIMEDIFF(        STR_TO_DATE(a.out_time, '%h:%i %p'),        STR_TO_DATE(a.in_time, '%h:%i %p')    ) as working_hour,    CASE        WHEN a.employee_att_id IS NULL THEN 'Absent'        ELSE 'Present'    END AS current_attendance FROM employee e  LEFT JOIN employee_att a ON e.employee_id = a.employee_id;", nativeQuery = true)
	  List<Map<String, Object>> GoodAllWorks();
	  
	  @Query(value = " SELECT \t\t\t    e.user_name,e.user_id, \t\t\t   e.employee_id, \t\t\t    a.employee_att_id, \t\t\t    a.in_time, \t\t\t    a.out_time, \t\t\t    a.attendance, \t\t\t    a.in_date, \t\t\t    a.punch_in, \t\t    a.punch_out, \t\t\t    TIMEDIFF(\t\t        STR_TO_DATE(a.out_time, '%h:%i %p'),\t\t\t       STR_TO_DATE(a.in_time, '%h:%i %p')\t\t\t    ) as working_hour,\t\t    CASE\t\t\t        WHEN a.employee_att_id IS NULL THEN 'Absent'\t\t\t       ELSE 'Present'\t\t\t   END AS current_attendance\t\t\t FROM employee e \t\t\t LEFT JOIN employee_att a ON e.employee_id = a.employee_id             where e.employee_id= :employeeId", nativeQuery = true)
	  List<Map<String, Object>> AttendanceforEmployeeId1(@Param("employeeId") long paramLong);
	  
	  @Query(value = " select    e.user_name,    e.user_id,    e.employee_id,    max(a.in_date) as in_date,    timediff(        current_time(),        str_to_date(max(a.in_time), '%h:%i %p')    ) as to_day,    sum(case when date_format(a.in_date, '%y-%m') = date_format(curdate(), '%y-%m') then a.working_hour else 0 end) as month_working_hour,    sum(case when yearweek(a.in_date, 1) = yearweek(curdate(), 1) then a.working_hour else 0 end) as week_working_hour from     employee e left join     employee_att a on e.employee_id = a.employee_id where     e.employee_id = :employeeId group by    e.user_name,    e.user_id,    e.employee_id", nativeQuery = true)
	  Map<String, Object> AttendanceforEmployeeId(@Param("employeeId") long paramLong);
	  
	  @Query(value = "SELECT e.user_name,e.user_id, e.employee_id, a.employee_att_id, a.in_time, a.out_time, a.attendance, a.in_date, a.punch_in, a.punch_out,   TIMEDIFF(\t\t\t       STR_TO_DATE(a.out_time, '%h:%i %p'),\t\t        STR_TO_DATE(a.in_time, '%h:%i %p')\t\t\t   ) as working_hour, CASE    WHEN a.employee_att_id IS NULL THEN 'Absent'    ELSE 'Present' END AS current_attendance FROM employee e LEFT JOIN employee_att a ON e.employee_id = a.employee_id WHERE e.employee_id = :employee_id AND MONTH(a.in_date) = MONTH(CURRENT_DATE())", nativeQuery = true)
	  List<Map<String, Object>> Allfilter(@Param("employee_id") long paramLong);
	  
	  @Query(value = "SELECT e.user_name,e.user_id e.employee_id, a.employee_att_id, a.in_time, a.out_time, a.attendance, a.in_date, a.punch_in, a.punch_out, a.working_hour,CASE    WHEN a.employee_att_id IS NULL THEN 'Absent'    ELSE 'Present' END AS current_attendance FROM employee e LEFT JOIN employee_att a ON e.employee_id = a.employee_id  WHERE e.employee_id = :employee_id AND a.in_date = CURDATE();", nativeQuery = true)
	  List<Map<String, Object>> Allfilter2(@Param("employee_id") long paramLong);
	  
	  @Query(value = "  SELECT e.user_name,e.user_id e.employee_id, a.employee_att_id, a.in_time, a.out_time, a.attendance, a.in_date, a.punch_in, a.punch_out, a.working_hour, CASE    WHEN a.employee_att_id IS NULL THEN 'Absent'    ELSE 'Present' END AS current_attendance FROM employee e LEFT JOIN employee_att a ON e.employee_id = a.employee_id AND a.in_date = CURDATE();", nativeQuery = true)
	  List<Map<String, Object>> Allfilter3();
	  
	  @Query(value = "SELECT     COUNT(CASE WHEN a.employee_att_id IS NOT NULL THEN 1 END) AS present_count,    COUNT(CASE WHEN a.employee_att_id IS NULL THEN 1 END) AS absent_count  FROM employee e LEFT JOIN employee_att a ON e.employee_id = a.employee_id AND a.in_date = CURDATE();", nativeQuery = true)
	  List<Map<String, Object>> getAllpresent();
	  
	  @Query(value = "SELECT     present_count,    absent_count,    present_count / (present_count + absent_count) * 100 AS present_percentage,    absent_count / (present_count + absent_count) * 100 AS absent_percentage FROM (    SELECT         COUNT(CASE WHEN a.employee_att_id IS NOT NULL THEN 1 END) AS present_count,        COUNT(CASE WHEN a.employee_att_id IS NULL THEN 1 END) AS absent_count    FROM employee e    LEFT JOIN employee_att a ON e.employee_id = a.employee_id    WHERE MONTH(a.in_date) = MONTH(CURDATE())     AND YEAR(a.in_date) = YEAR(CURDATE())) AS sub;", nativeQuery = true)
	  List<Map<String, Object>> getAllpresent1();
	}
