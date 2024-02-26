package com.example.HRM.entity.payroll;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payroll_type")
public class PayrollTypeList {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long payrollTypeId;
	  
	  private long employeeId;
	  
	  private double deductions;
	  
	  private double payrollAmount;
	  
	  private double allowance;
	  
	  private String reason;
	  
	  private double netPay;
	  
	  private LocalDate paymentDate = LocalDate.now();
	  
	  public double getPayrollAmount() {
	    return this.payrollAmount;
	  }
	  
	  public void setPayrollAmount(double payrollAmount) {
	    this.payrollAmount = payrollAmount;
	  }
	  
	  public LocalDate getPaymentDate() {
	    return this.paymentDate;
	  }
	  
	  public void setPaymentDate(LocalDate paymentDate) {
	    this.paymentDate = paymentDate;
	  }
	  
	  public double getNetPay() {
	    return this.netPay;
	  }
	  
	  public void setNetPay(double netPay) {
	    this.netPay = netPay;
	  }
	  
	  public long getPayrollTypeId() {
	    return this.payrollTypeId;
	  }
	  
	  public void setPayrollTypeId(long payrollTypeId) {
	    this.payrollTypeId = payrollTypeId;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public double getDeductions() {
	    return this.deductions;
	  }
	  
	  public void setDeductions(double deductions) {
	    this.deductions = deductions;
	  }
	  
	  public double getAllowance() {
	    return this.allowance;
	  }
	  
	  public void setAllowance(double allowance) {
	    this.allowance = allowance;
	  }
	  
	  public String getReason() {
	    return this.reason;
	  }
	  
	  public void setReason(String reason) {
	    this.reason = reason;
	  
	}

}
