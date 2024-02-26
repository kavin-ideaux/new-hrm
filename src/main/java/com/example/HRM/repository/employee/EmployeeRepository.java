package com.example.HRM.repository.employee;

import com.example.HRM.entity.employee.Employee;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	  @Query(value = " select  e.*,d.designation_name,dd.department_name,r.role_name,s.shift_type,s.in_time,s.out_time from employee as e join designation as d on d.designation_id=e.designation_id join department as dd on dd.department_id= e.department_id join role as r on r.role_id = e.role_id left join shift as s on s.shift_id = e.shift_id", nativeQuery = true)
	  List<Map<String, Object>> getAllEmployeesWithDetails();
	  
	  @Query(value = "select e.*,r.role_name,d.designation_name,dd.department_name,s.shift_type,s.in_time,s.out_time from employee as e join role as r on r.role_id=e.role_id join designation as d on d.designation_id=e.designation_id join department as dd on dd.department_id= e.department_id left join shift as s on s.shift_id = e.shift_id where e.employee_id = :employee_id", nativeQuery = true)
	    List<Map<String, Object>> getAllEmployeesWithDetailsWithId(@Param("employee_id") Long employeeId);
	  
	  @Query(value = " select e.*,d.designation_name,dd.department_name\tfrom employee as e\tjoin designation as d on d.designation_id=e.designation_id\tjoin department as dd on dd.department_id= e.department_id where e.status = true;", nativeQuery = true)
	  List<Map<String, Object>> AllEmployees();
	  
	  @Query(value = "SELECT e.*, d.designation_name, dd.department_name FROM employee AS e JOIN designation AS d ON d.designation_id = e.designation_id JOIN department AS dd ON dd.department_id = e.department_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	  List<Map<String, Object>> getAllRoleByEmployees3(@Param("employeeId") Long paramLong);
	  
	  @Query(value = "select e.*,r.role_name from employee as e join role as r on r.role_id = e.role_id", nativeQuery = true)
	  List<Map<String, Object>> getAllEmployeeWithRole();
	  
	  Employee findByEmail(String paramString);
	  
	  @Query(value = "select e.employee_id,e.user_name,d.department_id,d.department_name from employee as e join department as d on d.department_id = e.department_id", nativeQuery = true)
	  List<Map<String, Object>> getAllRoleByEmployees();
	  
	  @Query(value = "select e.*, d.department_name from employee as e join department as d on d.department_id = e.department_id where d.department_id = :departmentId", nativeQuery = true)
	  List<Map<String, Object>> getAllRoleByEmployees1(@Param("departmentId") Long paramLong);
	  
	  @Query(value = "SELECT e.*, d.department_name FROM employee e JOIN department d ON d.department_id = e.department_id WHERE d.department_id = :departmentId", nativeQuery = true)
	  List<Map<String, Object>> getAllEmployeesByDepartment(@Param("departmentId") Long paramLong);
	  
	  @Query(value = "               SELECT    ROUND((SUM(CASE WHEN e.gender = 'Male' THEN 1 ELSE 0 END) / COUNT(*)) * 100, 1) AS male_percentage,    ROUND((SUM(CASE WHEN e.gender = 'Female' THEN 1 ELSE 0 END) / COUNT(*)) * 100, 1) AS female_percentage FROM    employee AS e;", nativeQuery = true)
	  List<Map<String, Object>> ALLOver();
	  
	  @Query(value = "select year(e.date) as year, sum(count(e.employee_id)) over (order by year(e.date)) as total_employee_count, count(e.employee_id) as new_employee_count from employee e  where  e.status = true group by year(e.date) order by year(e.date)", nativeQuery = true)
	  List<Map<String, Object>> ALLCount();
	  
	  @Query(value = "  SELECT d.department_name,d.color ,COALESCE(COUNT(e.employee_id), 0) as employee_count\t\t\t FROM department as d\t\t\t LEFT JOIN employee as e\t\t\t ON e.department_id = d.department_id\t\t\t GROUP BY d.department_name,d.color;", nativeQuery = true)
	  List<Map<String, Object>> ALLDepatment();
	  
	  @Query(value = "  SELECT     e.user_name,    e.employee_id,    e.user_id,    DATE_ADD(e.date, INTERVAL YEAR(CURDATE()) - YEAR(e.date) YEAR) AS anniversary,    TIMESTAMPDIFF(YEAR, e.date, CURDATE()) AS years_since_joining,    CONCAT(e.user_name, ' - ', TIMESTAMPDIFF(YEAR, e.date, CURDATE()), ' year anniversary') AS anniversary_message FROM employee AS e", nativeQuery = true)
	  List<Map<String, Object>> AllNotifications1();
	  
	  @Query(value = "  SELECT     day AS event_date,    date AS date,    message FROM (    SELECT         h.day,        DATE_ADD(h.date, INTERVAL YEAR(CURDATE()) - YEAR(h.date) YEAR) AS date,        CASE            WHEN DATE_ADD(h.date, INTERVAL YEAR(CURDATE()) - YEAR(h.date) YEAR) = CURDATE()  THEN CONCAT(h.title, ' - Today is their holidays')            WHEN DATE_ADD(h.date, INTERVAL YEAR(CURDATE()) - YEAR(h.date) YEAR) = CURDATE() + INTERVAL 1 DAY THEN CONCAT(h.title, ' - Tomorrow is their holidays')            ELSE 'No special message'        END AS message    FROM holidays AS h    WHERE        DATE_ADD(h.date, INTERVAL YEAR(CURDATE()) - YEAR(h.date) YEAR) BETWEEN CURDATE() AND CURDATE() + INTERVAL 1 DAY    UNION    SELECT     DAYNAME(DATE_ADD(e.dob, INTERVAL YEAR(CURDATE()) - YEAR(e.dob) YEAR)) AS day,        DATE_ADD(e.dob, INTERVAL YEAR(CURDATE()) - YEAR(e.dob) YEAR) AS date,        CASE            WHEN DATE_ADD(e.dob, INTERVAL YEAR(CURDATE()) - YEAR(e.dob) YEAR) = CURDATE() THEN CONCAT(e.user_name, ' - Today is their birthday')            WHEN DATE_ADD(e.dob, INTERVAL YEAR(CURDATE()) - YEAR(e.dob) YEAR) = CURDATE() + INTERVAL 1 DAY THEN CONCAT(e.user_name, ' - Tomorrow is their birthday')            ELSE 'No special message'        END AS message    FROM employee AS e    WHERE        DATE_ADD(e.dob, INTERVAL YEAR(CURDATE()) - YEAR(e.dob) YEAR) BETWEEN CURDATE() AND CURDATE() + INTERVAL 1 DAY    UNION SELECT         DAYNAME(DATE_ADD(p.payroll_date, INTERVAL YEAR(CURDATE()) - YEAR(p.payroll_date) YEAR)) AS day,        DATE_ADD(p.payroll_date, INTERVAL YEAR(CURDATE()) - YEAR(p.payroll_date) YEAR) AS date,        CASE            WHEN DATE_ADD(p.payroll_date, INTERVAL YEAR(CURDATE()) - YEAR(p.payroll_date) YEAR) = CURDATE() THEN CONCAT(p.payroll_date, ' - Today is their salary date')            WHEN DATE_ADD(p.payroll_date, INTERVAL YEAR(CURDATE()) - YEAR(p.payroll_date) YEAR) = CURDATE() + INTERVAL 1 DAY THEN CONCAT(p.payroll_date, ' - Tomorrow is their salary date')            ELSE 'No special message'        END AS message    FROM payroll AS p    WHERE       DATE_ADD(p.payroll_date, INTERVAL YEAR(CURDATE()) - YEAR(p.payroll_date) YEAR) BETWEEN CURDATE() AND CURDATE() + INTERVAL 1 DAY          ) AS all_events;", nativeQuery = true)
	  List<Map<String, Object>> AllNotifications();
	  
	  long countByStatusTrue();
	  
	  @Query(value = "select e.employee_id,e.user_name,e.gender,e.phone_number,e.role_type,st.shift_name,dt.department_name, d.designation_name,e.shift_type_id from employee as e join shift_type as st on st.shift_type_id = e.shift_type_id join department as dt on dt.department_id = e.department_id join designation as d on d.designation_id = e.designation_id where st.shift_type_id =1", nativeQuery = true)
	  List<Map<String, Object>> getAllShiftTypeDetails();
	  
	  @Query(value = "select e.employee_id,e.user_name,e.gender,e.phone_number,e.role_type,st.shift_name, dt.department_name,d.designation_name,e.shift_type_id,s.shift_id,s.shift_type,s.in_time,s.out_time from employee as e join shift_type as st on st.shift_type_id = e.shift_type_id join department as dt on dt.department_id = e.department_id join designation as d on d.designation_id = e.designation_id join shift as s on s.shift_id = e.shift_id where st.shift_type_id =2 and s.shift_id = :shiftId", nativeQuery = true)
	  List<Map<String, Object>> getAllshiftAndShiftTypeDetails(Long shiftId);
	  
	  @Query(value = "select e.employee_id,e.user_name,e.role_type,r.role_id,r.role_name from employee as e join role as r on r.role_id = e.role_id where e.employee_id = :employee_id", nativeQuery = true)
	  List<Map<String, Object>> getAllRoleDetails(@Param("employee_id") Long paramLong);
	}

