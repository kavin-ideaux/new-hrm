package com.example.HRM.service.payroll;
import com.example.HRM.entity.payroll.SalaryType;
import com.example.HRM.entity.payroll.SalaryTypeList;
import com.example.HRM.repository.payroll.SalaryTypeListRepository;
import com.example.HRM.repository.payroll.SalaryTypeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryTypeService {

	 @Autowired
	  private SalaryTypeRepository salaryTypeRepository;
	  
	  @Autowired
	  private SalaryTypeListRepository salaryTypeListRepository;
	  
	  public List<SalaryType> listSalaryType() {
	    return this.salaryTypeRepository.findAll();
	  }
	  
	  public void SaveSalaryType(SalaryType client) {
	    this.salaryTypeRepository.save(client);
	  }
	  
	  public SalaryType findSalaryTypeById(Long id) {
	    return this.salaryTypeRepository.findById(id).get();
	  }
	  
	  public void deleteSalaryTypeById(Long id) {
	    this.salaryTypeRepository.deleteById(id);
	  }
	  
	  public Optional<SalaryTypeList> getSalaryByEmployeeId(Long employeeId) {
	    return this.salaryTypeListRepository.findByEmployeeId(employeeId);
	  }
	  
	  public Long getEmployeeIdForSalaryType(SalaryType salary) {
	    return this.salaryTypeRepository.findEmployeeIdBySalaryTypeList(salary);
	  }
	}

