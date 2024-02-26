package com.example.HRM.entity.employee;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promotions")
public class Promotions {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long promotionsId;
	  
	  private long employeeId;
	  
	  @Column(length = 4000)
	  private String description;
	  
	  private LocalDate date = LocalDate.now();
	  
	  private long roleId;
	  
	  private String promotionsBy;
	  
	  private boolean status;
	  
	  private String roleType;
	  
	  public String getRoleType() {
	    return this.roleType;
	  }
	  
	  public void setRoleType(String roleType) {
	    this.roleType = roleType;
	  }
	  
	  public long getPromotionsId() {
	    return this.promotionsId;
	  }
	  
	  public void setPromotionsId(long promotionsId) {
	    this.promotionsId = promotionsId;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  public LocalDate getDate() {
	    return this.date;
	  }
	  
	  public void setDate(LocalDate date) {
	    this.date = date;
	  }
	  
	  public long getRoleId() {
	    return this.roleId;
	  }
	  
	  public void setRoleId(long roleId) {
	    this.roleId = roleId;
	  }
	  
	  public String getPromotionsBy() {
	    return this.promotionsBy;
	  }
	  
	  public void setPromotionsBy(String promotionsBy) {
	    this.promotionsBy = promotionsBy;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
