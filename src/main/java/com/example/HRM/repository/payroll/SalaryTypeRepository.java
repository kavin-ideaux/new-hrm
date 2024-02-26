package com.example.HRM.repository.payroll;
import com.example.HRM.entity.payroll.SalaryType;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface SalaryTypeRepository extends JpaRepository<SalaryType, Long> {
	  @Query(value = "select s.*,sl.salary_type_list_id,sl.employee_id,sl.salary_amount,sl.salary_date as date1,e.user_name, e.department_id,e.designation_id,d.designation_name,dt.department_name from salary_type as s left join salary_type_list as sl on sl.salary_type_id = s.salary_type_id left join employee as e on e.employee_id = sl.employee_id join designation as d on d.designation_id = e.designation_id join department as dt on dt.department_id = e.department_id", nativeQuery = true)
	  List<Map<String, Object>> getAllDetailsForSalary();
	  
	  Long findEmployeeIdBySalaryTypeList(SalaryType paramSalaryType);

		@Query(value = "select s.*,e.user_name,d.department_id,dt.designation_id,d.department_name,dt.designation_name from salary_type_list as s"
			+ " join employee as e on e.employee_id = s.employee_id"
			+ " join department as d on d.department_id = e.department_id"
			+ " join designation as dt on dt.designation_id = e.designation_id", nativeQuery = true)
	List<Map<String, Object>> getAllDetailsForSalaryByDetails();
	}