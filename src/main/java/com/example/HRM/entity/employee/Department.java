package com.example.HRM.entity.employee;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long departmentId;
	  
	  private String departmentName;
	  
	  private String color;
	  
	  public String getColor() {
	    return this.color;
	  }
	  
	  public void setColor(String color) {
	    this.color = color;
	  }
	  
	  public Long getDepartmentId() {
	    return this.departmentId;
	  }
	  
	  public void setDepartmentId(Long departmentId) {
	    this.departmentId = departmentId;
	  }
	  
	  public String getDepartmentName() {
	    return this.departmentName;
	  }
	  
	  public void setDepartmentName(String departmentName) {
	    this.departmentName = departmentName;
	  }
	}
