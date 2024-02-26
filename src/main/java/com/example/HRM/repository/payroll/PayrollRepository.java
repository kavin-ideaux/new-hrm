package com.example.HRM.repository.payroll;
import com.example.HRM.entity.payroll.Payroll;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
	  @Query(value = "select s.*,sl.salary_type_list_id,sl.employee_id,sl.salary_amount,sl.salary_date as date1,e.user_name,e.user_id,p.payroll_type_id,p.payment_date,\te.department_id,e.designation_id,d.designation_name,dt.department_name,p.allowance,p.deductions,p.net_pay,p.reason,month(p.payment_date) as month,year(p.payment_date) as year from salary_type as s\tjoin salary_type_list as sl on sl.salary_type_id = s.salary_type_id\tjoin employee as e on e.employee_id = sl.employee_id\tjoin designation as d on d.designation_id = e.designation_id\tjoin department as dt on dt.department_id = e.department_id join payroll_type as p on p.employee_id = e.employee_id where month(p.payment_date) =:month and year(p.payment_date) =:year", nativeQuery = true)
	  List<Map<String, Object>> getAllPayrollDetailsForEmployee(int month, int year);
	  
	  @Query(value = "select s.*,sl.salary_type_list_id,sl.employee_id,sl.salary_amount,sl.salary_date as date1,e.user_name,e.user_id,p.payroll_type_id,p.payment_date,\te.department_id,e.designation_id,d.designation_name,dt.department_name,p.allowance,p.deductions,p.net_pay,p.reason,month(p.payment_date) as month,year(p.payment_date) as year from salary_type as s\tjoin salary_type_list as sl on sl.salary_type_id = s.salary_type_id\tjoin employee as e on e.employee_id = sl.employee_id\tjoin designation as d on d.designation_id = e.designation_id\tjoin department as dt on dt.department_id = e.department_id join payroll_type as p on p.employee_id = e.employee_id where month(p.payment_date) = month(current_date())", nativeQuery = true)
	  List<Map<String, Object>> getAllPayrollDetailsForEmployeeByMonth();
	  
	  @Query(value = " select s.*, sl.salary_type_list_id, sl.employee_id, CURRENT_DATE() AS today_date,    e.email, b.account_number, sl.salary_amount, sl.salary_date AS date1,    e.user_name, e.user_id, p.payroll_type_id, p.payment_date,pt.payroll_id,pt.company_id,    e.department_id, e.designation_id, b.bank_name, d.designation_name, dt.department_name,    p.allowance, p.deductions, p.net_pay, p.reason,c.email as company_email,c.url,c.account_no,c.company_name,c.phone_number1,    MONTH(p.payment_date) AS month, YEAR(p.payment_date) AS year  from salary_type as s join salary_type_list as sl on sl.salary_type_id = s.salary_type_id join employee as e on e.employee_id = sl.employee_id join designation as d on d.designation_id = e.designation_id join department as dt on dt.department_id = e.department_id join payroll_type as p on p.employee_id = e.employee_id join payroll as pt on pt.payroll_id = p.payroll_id join company as c on c.company_id = pt.company_id join  bank AS b ON b.employee_id = e.employee_id where month(p.payment_date) = month(current_date()) and e.employee_id =:employee_id", nativeQuery = true)
	  List<Map<String, Object>> getAllPayrollDetailsForEmployeeByMonthByID(Long employee_id);
	}
