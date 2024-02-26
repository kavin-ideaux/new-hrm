package com.example.HRM.controller.payroll;

import com.example.HRM.entity.payroll.Payroll;
import com.example.HRM.entity.payroll.PayrollTypeList;
import com.example.HRM.repository.payroll.PayrollRepository;
import com.example.HRM.repository.payroll.PayrollTypeRepository;
import com.example.HRM.service.payroll.PayrollService;
import com.example.HRM.service.payroll.PayrollTypeService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PayrollController {
	@Autowired
	private PayrollService payrollService;

	@Autowired
	private PayrollRepository payrollRepository;

	@Autowired
	private PayrollTypeService payrollTypeService;

	@Autowired
	private PayrollTypeRepository payrollTypeRepository;

	@GetMapping({ "/payroll" })
	public ResponseEntity<Object> getPayroll(@RequestParam(required = true) String payroll) {
		if ("Payroll".equals(payroll))
			return ResponseEntity.ok(this.payrollService.listPayroll());
		String errorMessage = "Invalid value for 'payroll'. Expected 'Payroll'.";
		return ResponseEntity.badRequest().body(errorMessage);
	}

	@PostMapping({ "/payroll/save" })
	private ResponseEntity<String> savePayroll(@RequestBody Payroll payroll) {
		List<PayrollTypeList> payrollTypeList = payroll.getPayrollTypeList();
		for (PayrollTypeList payrollTypeListLoop : payrollTypeList) {
			Optional<PayrollTypeList> empAndDateById = this.payrollTypeService.findByEmployeeIdAndDate(
					Long.valueOf(payrollTypeListLoop.getEmployeeId()), payrollTypeListLoop.getPaymentDate());
			if (empAndDateById.isPresent()) {
				String errorMessage = "A  employee and date for payroll already exists.";
				return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
			}
		}
		for (PayrollTypeList payrollTypeListLoop : payrollTypeList) {
			Optional<PayrollTypeList> empAndDateById = this.payrollTypeService.findByEmployeeIdAndDate(
					Long.valueOf(payrollTypeListLoop.getEmployeeId()), payrollTypeListLoop.getPaymentDate());
			if (empAndDateById.isPresent()) {
				String errorMessage = "A  employee and date for payroll already exists.";
				return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
			}
		}
		payroll.setCompanyId(1L);
		this.payrollService.SavePayroll(payroll);
		long id = payroll.getPayrollId();
		return ResponseEntity.ok("Payroll saved successfully with id :" + id);
	}

	@PutMapping({ "/payroll/edit/{id}" })
	public ResponseEntity<Payroll> updatePayroll(@PathVariable("id") Long payrollId, @RequestBody Payroll payroll) {
		try {
			Payroll existingSalaryType = this.payrollService.findPayrollById(payrollId);
			if (existingSalaryType == null)
				return ResponseEntity.notFound().build();
			existingSalaryType.setPayrollDate(payroll.getPayrollDate());
			existingSalaryType.setPayrollTypeList(payroll.getPayrollTypeList());
			this.payrollService.SavePayroll(existingSalaryType);
			return ResponseEntity.ok(existingSalaryType);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping({ "/payroll/type/edit/{id}/{date}" })
	public ResponseEntity<PayrollTypeList> updatePayrollList(@PathVariable("id") Long employeeId,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@RequestBody PayrollTypeList payrollType) {
		try {
			PayrollTypeList existingPayrollType = this.payrollTypeService.findByEmployeeIdAndPaymentDate(employeeId,
					date);
			if (existingPayrollType == null)
				return ResponseEntity.notFound().build();
			existingPayrollType.setDeductions(payrollType.getDeductions());
			existingPayrollType.setAllowance(payrollType.getAllowance());
			existingPayrollType.setReason(payrollType.getReason());
			this.payrollTypeService.SavePayrollType(existingPayrollType);
			return ResponseEntity.ok(existingPayrollType);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping({ "/payroll/delete/{id}" })
	public ResponseEntity<String> deletePayroll(@PathVariable("id") Long payrollId) {
		this.payrollService.deletePayrollById(payrollId);
		return ResponseEntity.ok("Payroll was deleted successfully");
	}

	@GetMapping({ "/department/emp/payroll" })
	public ResponseEntity<Object> getAllRoleByEmployee(@RequestParam(required = true) String department,
			@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year) {
		if ("Payroll".equals(department)) {
			List<Map<String, Object>> departmentList = new ArrayList<>();
			List<Map<String, Object>> departmentRole = this.payrollRepository
					.getAllPayrollDetailsForEmployee(month.intValue(), year.intValue());
			Map<String, List<Map<String, Object>>> departmentGroupMap = (Map<String, List<Map<String, Object>>>) departmentRole
					.stream().collect(Collectors.groupingBy(action -> action.get("payroll_type_id").toString()));
			for (Map.Entry<String, List<Map<String, Object>>> departmentLoop : departmentGroupMap.entrySet()) {
				Map<String, Object> departmentMap = new HashMap<>();
				departmentMap.put("departmentId", departmentLoop.getKey());
				departmentMap.put("departmentName",
						departmentLoop.getValue().get(0).get("department_name"));
				departmentMap.put("salaryTypeListId",
						departmentLoop.getValue().get(0).get("salary_type_list_id"));
				departmentMap.put("employeeId",
						departmentLoop.getValue().get(0).get("employee_id"));
				departmentMap.put("employeeName",
						departmentLoop.getValue().get(0).get("user_name"));
				departmentMap.put("salaryAmount",
						departmentLoop.getValue().get(0).get("salary_amount"));
				departmentMap.put("allowance", departmentLoop.getValue().get(0).get("allowance"));
				departmentMap.put("deductions",
						departmentLoop.getValue().get(0).get("deductions"));
				departmentMap.put("netPay", departmentLoop.getValue().get(0).get("net_pay"));
				departmentList.add(departmentMap);
			}
			return ResponseEntity.ok(departmentList);
		}
		if ("CurrentMonth".equals(department)) {
			List<Map<String, Object>> departmentList = new ArrayList<>();
			List<Map<String, Object>> departmentRole = this.payrollRepository.getAllPayrollDetailsForEmployeeByMonth();
			Map<String, List<Map<String, Object>>> departmentGroupMap = (Map<String, List<Map<String, Object>>>) departmentRole
					.stream().collect(Collectors.groupingBy(action -> action.get("payroll_type_id").toString()));
			for (Map.Entry<String, List<Map<String, Object>>> departmentLoop : departmentGroupMap.entrySet()) {
				Map<String, Object> departmentMap = new HashMap<>();
				departmentMap.put("departmentId", departmentLoop.getKey());
				departmentMap.put("departmentName",
						departmentLoop.getValue().get(0).get("department_name"));
				departmentMap.put("paymentDate",
						departmentLoop.getValue().get(0).get("payment_date"));
				departmentMap.put("salaryTypeListId",
						departmentLoop.getValue().get(0).get("salary_type_list_id"));
				departmentMap.put("employeeId",
						departmentLoop.getValue().get(0).get("employee_id"));
				departmentMap.put("employeeName",
						departmentLoop.getValue().get(0).get("user_name"));
				departmentMap.put("userId", departmentLoop.getValue().get(0).get("user_id"));
				departmentMap.put("salaryAmount",
						departmentLoop.getValue().get(0).get("salary_amount"));
				departmentMap.put("allowance", departmentLoop.getValue().get(0).get("allowance"));
				departmentMap.put("deductions",
						departmentLoop.getValue().get(0).get("deductions"));
				departmentMap.put("netPay", departmentLoop.getValue().get(0).get("net_pay"));
				departmentList.add(departmentMap);
			}
			return ResponseEntity.ok(departmentList);
		}
		String errorMessage = "Invalid value for 'department'. Expected 'CurrentMonth'.";
		return ResponseEntity.badRequest().body(errorMessage);
	}

	@GetMapping({ "/department/emp/payroll/{id}" })
	public ResponseEntity<Object> getAllRoleByEmployee(@RequestParam(required = true) String department,
			@PathVariable("id") Long employee_id) {
		if ("CurrentMonth".equals(department)) {
			List<Map<String, Object>> departmentList = new ArrayList<>();
			List<Map<String, Object>> departmentRole = this.payrollRepository
					.getAllPayrollDetailsForEmployeeByMonthByID(employee_id);
			Map<String, List<Map<String, Object>>> departmentGroupMap = (Map<String, List<Map<String, Object>>>) departmentRole
					.stream().collect(Collectors.groupingBy(action -> action.get("payroll_type_id").toString()));
			for (Map.Entry<String, List<Map<String, Object>>> departmentLoop : departmentGroupMap.entrySet()) {
				Map<String, Object> departmentMap = new HashMap<>();
				int randomNumber = generateRandomNumber();
				String fileExtension = getFileExtensionForImage(departmentLoop.getValue().get(0));
				if (departmentLoop.getValue().isEmpty())
					return ResponseEntity.badRequest().body("No data found for the given department.");
				String imageUrl = "/company/" + randomNumber + "/"
						+ departmentLoop.getValue().get(0).get("company_id") + "."
						+ fileExtension;
				departmentMap.put("imageUrl", imageUrl);
				departmentMap.put("departmentId", departmentLoop.getKey());
				departmentMap.put("departmentName",
						departmentLoop.getValue().get(0).get("department_name"));
				departmentMap.put("paymentDate",
						departmentLoop.getValue().get(0).get("payment_date"));
				departmentMap.put("salaryTypeListId",
						departmentLoop.getValue().get(0).get("salary_type_list_id"));
				departmentMap.put("employeeId",
						departmentLoop.getValue().get(0).get("employee_id"));
				departmentMap.put("employeeName",
						departmentLoop.getValue().get(0).get("user_name"));
				departmentMap.put("userId", departmentLoop.getValue().get(0).get("user_id"));
				departmentMap.put("salaryAmount",
						departmentLoop.getValue().get(0).get("salary_amount"));
				departmentMap.put("allowance", departmentLoop.getValue().get(0).get("allowance"));
				departmentMap.put("deductions",
						departmentLoop.getValue().get(0).get("deductions"));
				departmentMap.put("bankName", departmentLoop.getValue().get(0).get("bank_name"));
				departmentMap.put("netPay", departmentLoop.getValue().get(0).get("net_pay"));
				departmentMap.put("email", departmentLoop.getValue().get(0).get("email"));
				departmentMap.put("accountNumber",
						departmentLoop.getValue().get(0).get("account_number"));
				departmentMap.put("todayDate",
						departmentLoop.getValue().get(0).get("today_date"));
				departmentMap.put("email", departmentLoop.getValue().get(0).get("company_email"));
				departmentMap.put("accountNumber",
						departmentLoop.getValue().get(0).get("account_number"));
				departmentMap.put("phoneNumber1",
						departmentLoop.getValue().get(0).get("phone_number1"));
				departmentMap.put("companyName",
						departmentLoop.getValue().get(0).get("company_name"));
				departmentList.add(departmentMap);
			}
			return ResponseEntity.ok(departmentList);
		}
		String errorMessage = "Invalid value for 'department'. Expected 'CurrentMonth'.";
		return ResponseEntity.badRequest().body(errorMessage);
	}

	private int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(1000000);
	}

	private String getFileExtensionForImage(Map<String, Object> map) {
		if (map == null || !map.containsKey("url") || map.get("url") == null)
			return "jpg";
		String url = map.get("url").toString();
		if (url.endsWith(".png"))
			return "png";
		if (url.endsWith(".jpg"))
			return "jpg";
		return "jpg";
	}

	@GetMapping({ "/demooo" })
	public List<PayrollTypeList> getAllPayrollTypeList() {
		return this.payrollTypeRepository.findAll();
	}

	@GetMapping({ "/monthly/payroll/view" })
	public ResponseEntity<?> getAllPayrollListDetailsByMonthAndYear(
			@RequestParam(required = true) String payrollMonth) {
		if ("payrollDetails".equals(payrollMonth)) {
			List<Map<String, Object>> payrollList = new ArrayList<>();
			List<Map<String, Object>> payroll = this.payrollTypeRepository.getAllPayrollListDetailsByMonthAndYear();
			Map<String, List<Map<String, Object>>> payrollGroupMap = (Map<String, List<Map<String, Object>>>) payroll
					.stream().collect(Collectors.groupingBy(action -> action.get("payroll_type_id").toString()));
			for (Map.Entry<String, List<Map<String, Object>>> payrollLoop : payrollGroupMap.entrySet()) {
				Map<String, Object> payrollMap = new HashMap<>();
				payrollMap.put("payrollTypeId",payrollLoop.getValue().get(0).get("payroll_type_id"));
				payrollMap.put("allowance",payrollLoop.getValue().get(0).get("allowance"));
				payrollMap.put("deductions", payrollLoop.getValue().get(0).get("deductions"));
				payrollMap.put("netPay", payrollLoop.getValue().get(0).get("net_pay"));
				payrollMap.put("employeeId", payrollLoop.getValue().get(0).get("employee_id"));
				payrollMap.put("paymentDate", payrollLoop.getValue().get(0).get("payment_date"));
				payrollMap.put("reason", payrollLoop.getValue().get(0).get("reason"));
				payrollMap.put("payrollId", payrollLoop.getValue().get(0).get("payroll_id"));
				payrollMap.put("userName", payrollLoop.getValue().get(0).get("user_name"));
				payrollMap.put("departmentName",
						payrollLoop.getValue().get(0).get("department_name"));
				payrollMap.put("designationName",
						payrollLoop.getValue().get(0).get("designation_name"));
				payrollMap.put("month", payrollLoop.getValue().get(0).get("month"));
				payrollMap.put("year", payrollLoop.getValue().get(0).get("year"));
				payrollList.add(payrollMap);
			}
			return ResponseEntity.ok(payrollList);
		}
		String errorMessage = "Invalid value for 'payrollMonth'. Expected 'payrollDetails'.";
		return ResponseEntity.badRequest().body(errorMessage);
	}
}
