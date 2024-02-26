package com.example.HRM.entity.erecruitment;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidateInformation")
public class Candidate {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long candidateId;
	  
	  private String userName;
	  
	  private String emailId;
	  
	  private String mobileNumber;
	  
	  private String gender;
	  
	  private Date dateofBirth;
	  
	  private String education;
	  
	  private String branch;
	  
	  private String college;
	  
	  private String cgpa;
	  
	  private double salaryExpectations;
	  
	  private String Address;
	  
	  private String city;
	  
	  private String country;
	  
	  private String maritalStatus;
	  
	  private Date date;
	  
	  private String jobRole;
	  
	  private String skillDetails;
	  
	  private String yearOfPassing;
	  
	  private String workExperience;
	  
	  private Blob Resume;
	  
	  private String coverLetter;
	  
	  private String ResumeUrl;
	  
	  private boolean status;
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
	  }
	  
	  public String getUserName() {
	    return this.userName;
	  }
	  
	  public void setUserName(String userName) {
	    this.userName = userName;
	  }
	  
	  public String getEmailId() {
	    return this.emailId;
	  }
	  
	  public void setEmailId(String emailId) {
	    this.emailId = emailId;
	  }
	  
	  public String getMobileNumber() {
	    return this.mobileNumber;
	  }
	  
	  public void setMobileNumber(String mobileNumber) {
	    this.mobileNumber = mobileNumber;
	  }
	  
	  public String getGender() {
	    return this.gender;
	  }
	  
	  public void setGender(String gender) {
	    this.gender = gender;
	  }
	  
	  public Date getDateofBirth() {
	    return this.dateofBirth;
	  }
	  
	  public void setDateofBirth(Date dateofBirth) {
	    this.dateofBirth = dateofBirth;
	  }
	  
	  public String getEducation() {
	    return this.education;
	  }
	  
	  public void setEducation(String education) {
	    this.education = education;
	  }
	  
	  public String getBranch() {
	    return this.branch;
	  }
	  
	  public void setBranch(String branch) {
	    this.branch = branch;
	  }
	  
	  public String getCollege() {
	    return this.college;
	  }
	  
	  public void setCollege(String college) {
	    this.college = college;
	  }
	  
	  public String getCgpa() {
	    return this.cgpa;
	  }
	  
	  public void setCgpa(String cgpa) {
	    this.cgpa = cgpa;
	  }
	  
	  public double getSalaryExpectations() {
	    return this.salaryExpectations;
	  }
	  
	  public void setSalaryExpectations(double salaryExpectations) {
	    this.salaryExpectations = salaryExpectations;
	  }
	  
	  public String getAddress() {
	    return this.Address;
	  }
	  
	  public void setAddress(String address) {
	    this.Address = address;
	  }
	  
	  public String getCity() {
	    return this.city;
	  }
	  
	  public void setCity(String city) {
	    this.city = city;
	  }
	  
	  public String getCountry() {
	    return this.country;
	  }
	  
	  public void setCountry(String country) {
	    this.country = country;
	  }
	  
	  public String getMaritalStatus() {
	    return this.maritalStatus;
	  }
	  
	  public void setMaritalStatus(String maritalStatus) {
	    this.maritalStatus = maritalStatus;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public String getJobRole() {
	    return this.jobRole;
	  }
	  
	  public void setJobRole(String jobRole) {
	    this.jobRole = jobRole;
	  }
	  
	  public String getSkillDetails() {
	    return this.skillDetails;
	  }
	  
	  public void setSkillDetails(String skillDetails) {
	    this.skillDetails = skillDetails;
	  }
	  
	  public String getYearOfPassing() {
	    return this.yearOfPassing;
	  }
	  
	  public void setYearOfPassing(String yearOfPassing) {
	    this.yearOfPassing = yearOfPassing;
	  }
	  
	  public String getWorkExperience() {
	    return this.workExperience;
	  }
	  
	  public void setWorkExperience(String workExperience) {
	    this.workExperience = workExperience;
	  }
	  
	  public Blob getResume() {
	    return this.Resume;
	  }
	  
	  public void setResume(Blob resume) {
	    this.Resume = resume;
	  }
	  
	  public String getCoverLetter() {
	    return this.coverLetter;
	  }
	  
	  public void setCoverLetter(String coverLetter) {
	    this.coverLetter = coverLetter;
	  }
	  
	  public String getResumeUrl() {
	    return this.ResumeUrl;
	  }
	  
	  public void setResumeUrl(String resumeUrl) {
	    this.ResumeUrl = resumeUrl;
	  }
	

}
