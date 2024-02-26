package com.example.HRM.entity.employee;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assest")
public class Assest {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long assestId;
	  
	  private long employeeId;
	  
	  private String productName;
	  
	  private long serialNumber;
	  
	  private long modelNumber;
	  
	  private String brand;
	  
	  private String keyboardBrand;
	  
	  private String mouseBrand;
	  
	  private boolean status;
	  
	  private LocalDate date = LocalDate.now();
	  
	  public LocalDate getDate() {
	    return this.date;
	  }
	  
	  public void setDate(LocalDate date) {
	    this.date = date;
	  }
	  
	  public long getAssestId() {
	    return this.assestId;
	  }
	  
	  public void setAssestId(long assestId) {
	    this.assestId = assestId;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public String getProductName() {
	    return this.productName;
	  }
	  
	  public void setProductName(String productName) {
	    this.productName = productName;
	  }
	  
	  public long getSerialNumber() {
	    return this.serialNumber;
	  }
	  
	  public void setSerialNumber(long serialNumber) {
	    this.serialNumber = serialNumber;
	  }
	  
	  public long getModelNumber() {
	    return this.modelNumber;
	  }
	  
	  public void setModelNumber(long modelNumber) {
	    this.modelNumber = modelNumber;
	  }
	  
	  public String getBrand() {
	    return this.brand;
	  }
	  
	  public void setBrand(String brand) {
	    this.brand = brand;
	  }
	  
	  public String getKeyboardBrand() {
	    return this.keyboardBrand;
	  }
	  
	  public void setKeyboardBrand(String keyboardBrand) {
	    this.keyboardBrand = keyboardBrand;
	  }
	  
	  public String getMouseBrand() {
	    return this.mouseBrand;
	  }
	  
	  public void setMouseBrand(String mouseBrand) {
	    this.mouseBrand = mouseBrand;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	}


