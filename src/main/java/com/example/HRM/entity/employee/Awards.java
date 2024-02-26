package com.example.HRM.entity.employee;
import java.sql.Date;
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
@Table(name = "awards")
public class Awards {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long awardsId;
	  
	  @Column(length = 4000)
	  private String description;
	  
	  private String awardsType;
	  
	  private Date date;
	  
	  private long employeeId;
	  
	  @OneToMany(cascade = {CascadeType.ALL})
	  @JoinColumn(name = "fkawardsId", referencedColumnName = "awardsId")
	  private List<AwardsPhoto> awardsPhotos;
	  
	  private boolean status;
	  
	  public String getAwardsType() {
	    return this.awardsType;
	  }
	  
	  public void setAwardsType(String awardsType) {
	    this.awardsType = awardsType;
	  }
	  
	  public long getAwardsId() {
	    return this.awardsId;
	  }
	  
	  public void setAwardsId(long awardsId) {
	    this.awardsId = awardsId;
	  }
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public List<AwardsPhoto> getAwardsPhotos() {
	    return this.awardsPhotos;
	  }
	  
	  public void setAwardsPhotos(List<AwardsPhoto> awardsPhotos) {
	    this.awardsPhotos = awardsPhotos;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	}


