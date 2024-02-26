package com.example.HRM.entity.employee;
import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qualification")
public class Qualification {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long qualificationId;
	  
	  private Blob resume;
	  
	  private long aadharNO;
	  
	  private long employeeId;
	  
	  private Blob ten;
	  
	  private Blob aadhar;
	  
	  private String highestQualification;
	  
	  private Blob degree;
	  
	  private Blob bankBook;
	  
	  private Blob twelve;
	  
	  private Blob panno;
	  
	  private String resumeurl;
	  
	  private String pannourl;
	  
	  private String tenurl;
	  
	  private String aadharurl;
	  
	  private String degreeurl;
	  
	  private String bankBookurl;
	  
	  private String twelveurl;
	  
	  private boolean status;
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public long getAadharNO() {
	    return this.aadharNO;
	  }
	  
	  public void setAadharNO(long aadharNO) {
	    this.aadharNO = aadharNO;
	  }
	  
	  public String getResumeurl() {
	    return this.resumeurl;
	  }
	  
	  public void setResumeurl(String resumeurl) {
	    this.resumeurl = resumeurl;
	  }
	  
	  public String getPannourl() {
	    return this.pannourl;
	  }
	  
	  public void setPannourl(String pannourl) {
	    this.pannourl = pannourl;
	  }
	  
	  public String getTenurl() {
	    return this.tenurl;
	  }
	  
	  public void setTenurl(String tenurl) {
	    this.tenurl = tenurl;
	  }
	  
	  public String getAadharurl() {
	    return this.aadharurl;
	  }
	  
	  public void setAadharurl(String aadharurl) {
	    this.aadharurl = aadharurl;
	  }
	  
	  public String getDegreeurl() {
	    return this.degreeurl;
	  }
	  
	  public void setDegreeurl(String degreeurl) {
	    this.degreeurl = degreeurl;
	  }
	  
	  public String getBankBookurl() {
	    return this.bankBookurl;
	  }
	  
	  public void setBankBookurl(String bankBookurl) {
	    this.bankBookurl = bankBookurl;
	  }
	  
	  public String getTwelveurl() {
	    return this.twelveurl;
	  }
	  
	  public void setTwelveurl(String twelveurl) {
	    this.twelveurl = twelveurl;
	  }
	  
	  public long getQualificationId() {
	    return this.qualificationId;
	  }
	  
	  public void setQualificationId(long qualificationId) {
	    this.qualificationId = qualificationId;
	  }
	  
	  public Blob getResume() {
	    return this.resume;
	  }
	  
	  public void setResume(Blob resume) {
	    this.resume = resume;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public Blob getTen() {
	    return this.ten;
	  }
	  
	  public void setTen(Blob ten) {
	    this.ten = ten;
	  }
	  
	  public Blob getAadhar() {
	    return this.aadhar;
	  }
	  
	  public void setAadhar(Blob aadhar) {
	    this.aadhar = aadhar;
	  }
	  
	  public String getHighestQualification() {
	    return this.highestQualification;
	  }
	  
	  public void setHighestQualification(String highestQualification) {
	    this.highestQualification = highestQualification;
	  }
	  
	  public Blob getDegree() {
	    return this.degree;
	  }
	  
	  public void setDegree(Blob degree) {
	    this.degree = degree;
	  }
	  
	  public Blob getBankBook() {
	    return this.bankBook;
	  }
	  
	  public void setBankBook(Blob bankBook) {
	    this.bankBook = bankBook;
	  }
	  
	  public Blob getTwelve() {
	    return this.twelve;
	  }
	  
	  public void setTwelve(Blob twelve) {
	    this.twelve = twelve;
	  }
	  
	  public Blob getPanno() {
	    return this.panno;
	  }
	  
	  public void setPanno(Blob panno) {
	    this.panno = panno;
	  }
	

}
