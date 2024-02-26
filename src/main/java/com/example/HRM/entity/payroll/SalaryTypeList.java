package com.example.HRM.entity.payroll;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salary_type_list")
public class SalaryTypeList {

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long salaryTypeListId;
	  
	  private long employeeId;
	  
	  private double salaryAmount;
	  
	  private LocalDate salaryDate = LocalDate.now();
	  
	  private Date updatingDate;
	  
	  public Date getUpdatingDate() {
	    return this.updatingDate;
	  }
	  
	  public void setUpdatingDate(Date updatingDate) {
	    this.updatingDate = updatingDate;
	  }
	  
	  public LocalDate getSalaryDate() {
	    return this.salaryDate;
	  }
	  
	  public void setSalaryDate(LocalDate salaryDate) {
	    this.salaryDate = salaryDate;
	  }
	  
	  public long getSalaryTypeListId() {
	    return this.salaryTypeListId;
	  }
	  
	  public void setSalaryTypeListId(long salaryTypeListId) {
	    this.salaryTypeListId = salaryTypeListId;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public double getSalaryAmount() {
	    return this.salaryAmount;
	  }
	  
	  public void setSalaryAmount(double salaryAmount) {
	    this.salaryAmount = salaryAmount;
	  }
	}

