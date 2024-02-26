package com.example.HRM.entity.employee;
import com.example.HRM.entity.employee.AssetsList;
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
@Table(name = "assets")
public class Assets {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long assetsId;
	  
	  private Long employeeId;
	  
	  private Date assetsDate;
	  
	  @OneToMany(cascade = {CascadeType.ALL})
	  @JoinColumn(name = "fk_assetsId", referencedColumnName = "assetsId")
	  private List<AssetsList> assets;
	  
	  public long getAssetsId() {
	    return this.assetsId;
	  }
	  
	  public void setAssetsId(long assetsId) {
	    this.assetsId = assetsId;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public Date getAssetsDate() {
	    return this.assetsDate;
	  }
	  
	  public void setAssetsDate(Date assetsDate) {
	    this.assetsDate = assetsDate;
	  }
	  
	  public List<AssetsList> getAssets() {
	    return this.assets;
	  }
	  
	  public void setAssets(List<AssetsList> assets) {
	    this.assets = assets;
	  }
	}


