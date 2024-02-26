package com.example.HRM.entity.employee;
import com.example.HRM.entity.employee.CompanyPropertyList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employeeexit")
public class EmployeeExit {

	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long employeeExitId;
	  
	  private Long employeeId;
	  
	  private Date date;
	  
	  @Column(length = 4000)
	  private String description;
	  
	  private boolean status;
	  
	  private String exitReason;
	  
	  private String companyProperty;
	  
	  private Date endDate;
	  
	  private String exitType;
	  
	  private LocalDate inDate = LocalDate.now();
	  
	  @OneToMany(cascade = {CascadeType.ALL})
	  @JoinColumn(name = "fk_employeeExitId", referencedColumnName = "employeeExitId")
	  private List<CompanyPropertyList> CompanyProperty;
	  
	  public Long getEmployeeExitId() {
	    return this.employeeExitId;
	  }
	  
	  public void setEmployeeExitId(Long employeeExitId) {
	    this.employeeExitId = employeeExitId;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public String getExitReason() {
	    return this.exitReason;
	  }
	  
	  public void setExitReason(String exitReason) {
	    this.exitReason = exitReason;
	  }
	  
	  public String getCompanyProperty() {
	    return this.companyProperty;
	  }
	  
	  public void setCompanyProperty(String companyProperty) {
	    this.companyProperty = companyProperty;
	  }
	  
	  public Date getEndDate() {
	    return this.endDate;
	  }
	  
	  public void setEndDate(Date endDate) {
	    this.endDate = endDate;
	  }
	  
	  public String getExitType() {
	    return this.exitType;
	  }
	  
	  public void setExitType(String exitType) {
	    this.exitType = exitType;
	  }
	  
	  public LocalDate getInDate() {
	    return this.inDate;
	  }
	  
	  public void setInDate(LocalDate inDate) {
	    this.inDate = inDate;
	  }
	  
	  public void setCompanyProperty(List<CompanyPropertyList> companyProperty) {
	    this.CompanyProperty = companyProperty;
	  }
	}

