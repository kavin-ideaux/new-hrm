package com.example.HRM.entity.payroll;
import com.example.HRM.entity.payroll.SalaryTypeList;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "salary_type")
public class SalaryType {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long salaryTypeId;
	  
	  private String enteredBy;
	  
	  private Date salaryDate;
	  
	  @OneToMany(cascade = {CascadeType.ALL})
	  @JoinColumn(name = "salaryTypeId", referencedColumnName = "salaryTypeId")
	  private List<SalaryTypeList> salaryTypeList;
	  
	  public String getEnteredBy() {
	    return this.enteredBy;
	  }
	  
	  public void setEnteredBy(String enteredBy) {
	    this.enteredBy = enteredBy;
	  }
	  
	  public Date getSalaryDate() {
	    return this.salaryDate;
	  }
	  
	  public void setSalaryDate(Date salaryDate) {
	    this.salaryDate = salaryDate;
	  }
	  
	  public long getSalaryTypeId() {
	    return this.salaryTypeId;
	  }
	  
	  public void setSalaryTypeId(long salaryTypeId) {
	    this.salaryTypeId = salaryTypeId;
	  }
	  
	  public List<SalaryTypeList> getSalaryTypeList() {
	    return this.salaryTypeList;
	  }
	  
	  public void setSalaryTypeList(List<SalaryTypeList> salaryTypeList) {
	    this.salaryTypeList = salaryTypeList;
	  }
	}


