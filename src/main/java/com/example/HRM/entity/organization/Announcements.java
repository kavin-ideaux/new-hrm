package com.example.HRM.entity.organization;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "announcements")
public class Announcements {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long announcementId;
	  
	  private String title;
	  
	  private Date fromDate;
	  
	  private Date toDate;
	  
	  private Blob attachment;
	  
	  private Long employeeId;
	  
	  private String url;
	  
	  private String informedBy;
	  
	  private boolean published;
	  
	  private boolean status;
	  
	  public String getUrl() {
	    return this.url;
	  }
	  
	  public void setUrl(String url) {
	    this.url = url;
	  }
	  
	  public Blob getAttachment() {
	    return this.attachment;
	  }
	  
	  public void setAttachment(Blob attachment) {
	    this.attachment = attachment;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public boolean isPublished() {
	    return this.published;
	  }
	  
	  public void setPublished(boolean published) {
	    this.published = published;
	  }
	  
	  public long getAnnouncementId() {
	    return this.announcementId;
	  }
	  
	  public void setAnnouncementId(long announcementId) {
	    this.announcementId = announcementId;
	  }
	  
	  public String getTitle() {
	    return this.title;
	  }
	  
	  public void setTitle(String title) {
	    this.title = title;
	  }
	  
	  public Date getFromDate() {
	    return this.fromDate;
	  }
	  
	  public void setFromDate(Date fromDate) {
	    this.fromDate = fromDate;
	  }
	  
	  public Date getToDate() {
	    return this.toDate;
	  }
	  
	  public void setToDate(Date toDate) {
	    this.toDate = toDate;
	  }
	  
	  public String getInformedBy() {
	    return this.informedBy;
	  }
	  
	  public void setInformedBy(String informedBy) {
	    this.informedBy = informedBy;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
