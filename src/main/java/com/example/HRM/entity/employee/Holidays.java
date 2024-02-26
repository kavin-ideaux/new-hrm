package com.example.HRM.entity.employee;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holidays")
public class Holidays {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long holidaysId;
	  
	  private String title;
	  
	  private Date date;
	  
	  private String day;
	  
	  private boolean status;
	  
	  public Long getHolidaysId() {
	    return this.holidaysId;
	  }
	  
	  public void setHolidaysId(Long holidaysId) {
	    this.holidaysId = holidaysId;
	  }
	  
	  public String getTitle() {
	    return this.title;
	  }
	  
	  public void setTitle(String title) {
	    this.title = title;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public String getDay() {
	    return this.day;
	  }
	  
	  public void setDay(String day) {
	    this.day = day;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
