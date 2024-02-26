package com.example.HRM.controller.payroll;
import com.example.HRM.entity.payroll.SalaryType;
import com.example.HRM.entity.payroll.SalaryTypeList;
import com.example.HRM.repository.payroll.SalaryTypeListRepository;
import com.example.HRM.repository.payroll.SalaryTypeRepository;
import com.example.HRM.service.payroll.SalaryTypeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin
@RestController
public class SalaryTypeController {

	 @Autowired
	  private SalaryTypeService salaryTypeService;
	  
	  @Autowired
	  private SalaryTypeRepository salaryTypeRepository;
	  
	  @Autowired
	  private SalaryTypeListRepository salaryTypeListRepository;
	  
	  @GetMapping({"/salary"})
		public ResponseEntity<Object> getEmployeeSalary(@RequestParam(required = true) String salary) {
			if ("Salary".equals(salary))
				return ResponseEntity.ok(this.salaryTypeService.listSalaryType());
			String errorMessage = "Invalid value for 'salary'. Expected 'Salary'.";
			return ResponseEntity.badRequest().body(errorMessage);
		}

		@PostMapping({ "/salary/save" })
		public ResponseEntity<String> saveEmployeeSalary(@RequestBody SalaryType salary) {
			List<SalaryTypeList> salaryTypeList = salary.getSalaryTypeList();
			for (SalaryTypeList salaryLoop : salaryTypeList) {
				Optional<SalaryTypeList> salaryByEmployeeId = this.salaryTypeService
						.getSalaryByEmployeeId(Long.valueOf(salaryLoop.getEmployeeId()));
				if (salaryByEmployeeId.isPresent()) {
					String errorMessage = "A salary for this employee already exists.";
					return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
				}
			}
			this.salaryTypeService.SaveSalaryType(salary);
			long id = salary.getSalaryTypeId();
			return ResponseEntity.ok("Salary added successfully. Salary ID: " + id);
		}

		@PutMapping({ "/salary/edit/{id}" })
		public ResponseEntity<SalaryType> updateEmployeeSalary(@PathVariable("id") Long salaryTypeId,
				@RequestBody SalaryType salaryType) {
			try {
				SalaryType existingSalaryType = this.salaryTypeService.findSalaryTypeById(salaryTypeId);
				if (existingSalaryType == null)
					return ResponseEntity.notFound().build();
				existingSalaryType.setSalaryTypeList(salaryType.getSalaryTypeList());
				this.salaryTypeService.SaveSalaryType(existingSalaryType);
				return ResponseEntity.ok(existingSalaryType);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}

		@PutMapping({ "/department/employee/salary/{id}" })
		public ResponseEntity<?> updateEmployeeSalaryById(@PathVariable("id") Long employeeId,
				@RequestBody SalaryTypeList salaryType) {
			try {
				Optional<SalaryTypeList> existingSalaryTypeOptional = this.salaryTypeService
						.getSalaryByEmployeeId(employeeId);
				if (!existingSalaryTypeOptional.isPresent())
					return ResponseEntity.notFound().build();
				SalaryTypeList existingSalaryType = existingSalaryTypeOptional.get();
				existingSalaryType.setSalaryAmount(salaryType.getSalaryAmount());
				this.salaryTypeListRepository.save(existingSalaryType);
				return ResponseEntity.ok(existingSalaryType);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}

		@DeleteMapping({ "/salary/delete/{id}" })
		public ResponseEntity<String> deleteEmployeeSalary(@PathVariable("id") Long salaryTypeId) {
			this.salaryTypeService.deleteSalaryTypeById(salaryTypeId);
			return ResponseEntity.ok("Employee Salary was deleted successfully");
		}

		@GetMapping({ "/department/employee/salary" })
		public ResponseEntity<Object> getAllRoleByEmployee(@RequestParam(required = true) String department) {
			if ("Salary".equals(department)) {
				List<Map<String, Object>> departmentList = new ArrayList<>();
				List<Map<String, Object>> departmentRole = this.salaryTypeRepository.getAllDetailsForSalary();
				Map<String, Map<String, List<Map<String, Object>>>> departmentGroupMap = (Map<String, Map<String, List<Map<String, Object>>>>) StreamSupport
						.stream(departmentRole.spliterator(), false)
						.collect(Collectors.groupingBy(action -> action.get("department_id").toString(),
								Collectors.groupingBy(action -> action.get("salary_type_id").toString())));
				for (Map.Entry<String, Map<String, List<Map<String, Object>>>> departmentLoop : departmentGroupMap
						.entrySet()) {
					Map<String, Object> departmentMap = new HashMap<>();
					departmentMap.put("departmentId", departmentLoop.getKey());
					String departmentName = departmentRole.stream()
							.filter(d -> d.get("department_id").toString().equals(departmentLoop.getKey()))
							.map(d -> d.get("department_name").toString()).findFirst().orElse(null);
					departmentMap.put("departmentName", departmentName);
					List<Map<String, Object>> salaryType = new ArrayList<>();
					for (Map.Entry<String, List<Map<String, Object>>> salaryLoop : (Iterable<Map.Entry<String, List<Map<String, Object>>>>) ((Map) departmentLoop
							.getValue()).entrySet()) {
						Map<String, Object> salaryTypeMap = new HashMap<>();
						salaryTypeMap.put("enteredBy", salaryLoop.getValue().get(0).get("entered_by"));
						List<Map<String, Object>> salaryTypeList = new ArrayList<>();
						for (Map<String, Object> salaryTypeLoop : salaryLoop.getValue()) {
							Map<String, Object> salaryTypeMapList = new HashMap<>();
							salaryTypeMapList.put("salaryTypeListId", salaryTypeLoop.get("salary_type_list_id"));
							salaryTypeMapList.put("employeeId", salaryTypeLoop.get("employee_id"));
							salaryTypeMapList.put("employeeName", salaryTypeLoop.get("user_name"));
							salaryTypeMapList.put("salaryAmount", salaryTypeLoop.get("salary_amount"));
							salaryTypeList.add(salaryTypeMapList);
						}
						salaryTypeMap.put("salaryTypeList", salaryTypeList);
						salaryType.add(salaryTypeMap);
					}
					departmentMap.put("salaryType", salaryType);
					departmentList.add(departmentMap);
				}
				return ResponseEntity.ok(departmentList);
			}
			String errorMessage = "Invalid value for 'department'. Expected 'Department'.";
			return ResponseEntity.badRequest().body(errorMessage);
		}

		@GetMapping({ "/department/emp/salary" })
		public ResponseEntity<Object> getAllRoleByEmployee1(@RequestParam(required = true) String department) {
			if ("Salary".equals(department)) {
				List<Map<String, Object>> departmentList = new ArrayList<>();
				List<Map<String, Object>> departmentRole = this.salaryTypeRepository.getAllDetailsForSalary();
				Map<String, List<Map<String, Object>>> departmentGroupMap = (Map<String, List<Map<String, Object>>>) departmentRole
						.stream().collect(Collectors.groupingBy(action -> action.get("department_id").toString()));
				for (Map.Entry<String, List<Map<String, Object>>> departmentLoop : departmentGroupMap.entrySet()) {
					Map<String, Object> departmentMap = new HashMap<>();
					departmentMap.put("departmentId", departmentLoop.getKey());
					departmentMap.put("departmentName", departmentLoop.getValue().get(0).get("department_name"));
	        List<Map<String, Object>> salaryTypeList = new ArrayList<>();
	        for (Map<String, Object> salaryTypeLoop : departmentLoop.getValue()) {
	          Map<String, Object> salaryTypeMapList = new HashMap<>();
	      	int randomNumber = generateRandomNumber();
			String fileExtension = getFileExtensionForImage(salaryTypeLoop);
			String imageUrl = "profile/" + randomNumber + "/" + salaryTypeLoop.get("employee_id") + "."
					+ fileExtension;
	          salaryTypeMapList.put("salaryTypeListId", salaryTypeLoop.get("salary_type_list_id"));
	          salaryTypeMapList.put("employeeId", salaryTypeLoop.get("employee_id"));
	          salaryTypeMapList.put("employeeName", salaryTypeLoop.get("user_name"));
	          salaryTypeMapList.put("profile",imageUrl );
	          salaryTypeMapList.put("salaryAmount", salaryTypeLoop.get("salary_amount"));
	          salaryTypeMapList.put("departmentId", salaryTypeLoop.get("department_id"));
	          salaryTypeMapList.put("departmentName", salaryTypeLoop.get("department_name"));
	          salaryTypeList.add(salaryTypeMapList);
	        } 
	        departmentMap.put("salaryType", salaryTypeList);
	        departmentList.add(departmentMap);
	      } 
	      return ResponseEntity.ok(departmentList);
	    } 
	    String errorMessage = "Invalid value for 'department'. Expected 'Salary'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
		
		private String getFileExtensionForImage(Map<String, Object> salaryTypeLoop) {
			if (salaryTypeLoop == null || !salaryTypeLoop.containsKey("url") || salaryTypeLoop.get("url") == null) {
				return "jpg";
			}
			String url = (String) salaryTypeLoop.get("url");
			if (url.endsWith(".png")) {
				return "png";
			} else if (url.endsWith(".jpg")) {
				return "jpg";
			} else {
				return "jpg";
			}
		}
		private int generateRandomNumber() {
			Random random = new Random();
			return random.nextInt(1000000); // Change the upper limit if needed
		}
		
		
		private String getFileExtensionForImage1(Entry<String, List<Map<String, Object>>> payrollLoop) {
			if (payrollLoop == null || payrollLoop.getValue() == null || payrollLoop.getValue().isEmpty()) {
				return "jpg";
			}

			String url = (String) payrollLoop.getValue().get(0).get("url");

			if (url == null) {
				return "jpg";
			}

			if (url.endsWith(".png")) {
				return "png";
			} else if (url.endsWith(".jpg")) {
				return "jpg";
			} else {
				return "jpg";
			}
		}
		@GetMapping("/salary/type/view")
		public ResponseEntity<?> getAllPayrollListDetailsByMonthAndYear(@RequestParam(required = true) String payrollMonth) {
			if(("pay").equals(payrollMonth)) {
				List<Map<String, Object>> payrollList = new ArrayList<>();
				List<Map<String, Object>> payroll = salaryTypeRepository.getAllDetailsForSalaryByDetails();

				
				Map<String, List<Map<String, Object>>> payrollGroupMap = payroll.stream()
						.collect(Collectors.groupingBy(action -> action.get("salary_type_list_id").toString()));
				for (Entry<String, List<Map<String, Object>>> payrollLoop : payrollGroupMap.entrySet()) {
					Map<String, Object> payrollMap = new HashMap<>();
					int randomNumber = generateRandomNumber();
					String fileExtension = getFileExtensionForImage1(payrollLoop);
					String imageUrl = "profile/" + randomNumber + "/" + payrollLoop.getValue().get(0).get("employee_id") + "."
							+ fileExtension;
					payrollMap.put("profile",imageUrl );
					payrollMap.put("salarytypeId", payrollLoop.getValue().get(0).get("salary_type_id"));
					payrollMap.put("salaryDate", payrollLoop.getValue().get(0).get("salary_date"));
					payrollMap.put("salaryTypeListId", payrollLoop.getValue().get(0).get("salary_type_list_id"));
					payrollMap.put("employeeId", payrollLoop.getValue().get(0).get("employee_id"));
					payrollMap.put("salaryAmount", payrollLoop.getValue().get(0).get("salary_amount"));
					payrollMap.put("departmentId", payrollLoop.getValue().get(0).get("department_id"));
					payrollMap.put("salaryDate", payrollLoop.getValue().get(0).get("salary_date"));
					payrollMap.put("userName", payrollLoop.getValue().get(0).get("user_name"));
					payrollMap.put("departmentName", payrollLoop.getValue().get(0).get("department_name"));
					payrollMap.put("designationName", payrollLoop.getValue().get(0).get("designation_name"));
					payrollMap.put("designationId", payrollLoop.getValue().get(0).get("designation_id"));
					
					payrollList.add(payrollMap);
				}

				return ResponseEntity.ok(payrollList);

			}else {
				String errorMessage = "Invalid value for 'payrollMonth'. Expected 'payrollDetails'.";
				return ResponseEntity.badRequest().body(errorMessage);
			}
			
		}
		
		
		
	}

