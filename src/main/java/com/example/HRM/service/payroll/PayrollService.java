package com.example.HRM.service.payroll;
import com.example.HRM.entity.payroll.Payroll;
import com.example.HRM.repository.payroll.PayrollRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {
	 @Autowired
	  private PayrollRepository payrollRepository;
	  
	  public List<Payroll> listPayroll() {
	    return this.payrollRepository.findAll();
	  }
	  
	  public void SavePayroll(Payroll client) {
	    this.payrollRepository.save(client);
	  }
	  
	  public Payroll findPayrollById(Long id) {
	    return this.payrollRepository.findById(id).get();
	  }
	  
	  public void deletePayrollById(Long id) {
	    this.payrollRepository.deleteById(id);
	  }
	

}
