package com.example.HRM.entity.employee;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assetsList")
public class AssetsList {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long assetsListId;
	  
	  private Long brantId;
	  
	  private Long accessoriesId;
	  
	  private int count;
	  
	  private long serialNumber;
	  
	  public int getCount() {
	    return this.count;
	  }
	  
	  public void setCount(int count) {
	    this.count = count;
	  }
	  
	  public Long getAssetsListId() {
	    return this.assetsListId;
	  }
	  
	  public void setAssetsListId(Long assetsListId) {
	    this.assetsListId = assetsListId;
	  }
	  
	  public Long getBrantId() {
	    return this.brantId;
	  }
	  
	  public void setBrantId(Long brantId) {
	    this.brantId = brantId;
	  }
	  
	  public Long getAccessoriesId() {
	    return this.accessoriesId;
	  }
	  
	  public void setAccessoriesId(Long accessoriesId) {
	    this.accessoriesId = accessoriesId;
	  }
	  
	  public long getSerialNumber() {
	    return this.serialNumber;
	  }
	  
	  public void setSerialNumber(long serialNumber) {
	    this.serialNumber = serialNumber;
	  }
	}


