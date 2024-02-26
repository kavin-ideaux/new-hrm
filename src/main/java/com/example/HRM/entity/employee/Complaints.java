package com.example.HRM.entity.employee;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaints")
public class Complaints {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long complaintsId;
	  
	  private long employeeId;
	  
	  private String complaintsTitle;
	  
	  private Date complaintsDate;
	  
	  private String complaintsAgainst;
	  
	  @Column(length = 4000)
	  private String description;
	  
	  private String url;
	  
	  private Blob attachments;
	  
	  private boolean status;
	  
	  public String getUrl() {
	    return this.url;
	  }
	  
	  public void setUrl(String url) {
	    this.url = url;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public long getComplaintsId() {
	    return this.complaintsId;
	  }
	  
	  public void setComplaintsId(long complaintsId) {
	    this.complaintsId = complaintsId;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public String getComplaintsTitle() {
	    return this.complaintsTitle;
	  }
	  
	  public void setComplaintsTitle(String complaintsTitle) {
	    this.complaintsTitle = complaintsTitle;
	  }
	  
	  public Date getComplaintsDate() {
	    return this.complaintsDate;
	  }
	  
	  public void setComplaintsDate(Date complaintsDate) {
	    this.complaintsDate = complaintsDate;
	  }
	  
	  public String getComplaintsAgainst() {
	    return this.complaintsAgainst;
	  }
	  
	  public void setComplaintsAgainst(String complaintsAgainst) {
	    this.complaintsAgainst = complaintsAgainst;
	  }
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  public Blob getAttachments() {
	    return this.attachments;
	  }
	  
	  public void setAttachments(Blob attachments) {
	    this.attachments = attachments;
	  }
	  
	  public Complaints() {
	    this.complaintsId = this.complaintsId;
	    this.employeeId = this.employeeId;
	    this.complaintsTitle = this.complaintsTitle;
	    this.complaintsDate = this.complaintsDate;
	    this.complaintsAgainst = this.complaintsAgainst;
	    this.description = this.description;
	    this.url = this.url;
	    this.attachments = this.attachments;
	    this.status = this.status;
	  }
	}


