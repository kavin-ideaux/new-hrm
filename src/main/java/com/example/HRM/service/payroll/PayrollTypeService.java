package com.example.HRM.service.payroll;
import com.example.HRM.entity.payroll.PayrollTypeList;
import com.example.HRM.repository.payroll.PayrollTypeRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollTypeService {

	 @Autowired
	  private PayrollTypeRepository payrollTypeRepository;
	  
	  public List<PayrollTypeList> listPayrollType() {
	    return this.payrollTypeRepository.findAll();
	  }
	  
	  public void SavePayrollType(PayrollTypeList client) {
	    this.payrollTypeRepository.save(client);
	  }
	  
	  public PayrollTypeList findPayrollTypeById(Long id) {
	    return this.payrollTypeRepository.findById(id).get();
	  }
	  
	  public PayrollTypeList findByEmployeeIdAndPaymentDate(Long id, LocalDate date) {
	    return this.payrollTypeRepository.findByEmployeeIdAndPaymentDate(id, date).get();
	  }
	  
	  public void deletePayrollTypeById(Long id) {
	    this.payrollTypeRepository.deleteById(id);
	  }
	  
	  public Optional<PayrollTypeList> findByEmployeeIdAndDate(Long employeeId, LocalDate date) {
	    return this.payrollTypeRepository.findByEmployeeIdAndPaymentDate(employeeId, date);
	  }
	}

