package com.example.HRM.entity.employee;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companyPropertyList")
public class CompanyPropertyList {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long companyPropertylistId;
	  
	  private long brandId;
	  
	  private long accessoriesId;
	  
	  private int count;
	  
	  public long getCompanyPropertylistId() {
	    return this.companyPropertylistId;
	  }
	  
	  public void setCompanyPropertylistId(long companyPropertylistId) {
	    this.companyPropertylistId = companyPropertylistId;
	  }
	  
	  public long getBrandId() {
	    return this.brandId;
	  }
	  
	  public void setBrandId(long brandId) {
	    this.brandId = brandId;
	  }
	  
	  public long getAccessoriesId() {
	    return this.accessoriesId;
	  }
	  
	  public void setAccessoriesId(long accessoriesId) {
	    this.accessoriesId = accessoriesId;
	  }
	  
	  public int getCount() {
	    return this.count;
	  }
	  
	  public void setCount(int count) {
	    this.count = count;
	  }
	}


