package com.example.HRM.entity.employee;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long employeeId;
	  
	  private String userName;
	  
	  private String gender;
	  
	  private String country;
	  
	  @Column(unique = true)
	  private String email;
	  
	  private String password;
	  
	  private String state;
	  
	  private String city;
	  
	  private String address;
	  
	  private String confirmPassword;
	  
	  private long shiftId;
	  
	  private long shiftTypeId;
	  
	  private String attendanceType;
	  
	  private String url;
	  
	  private Date dob;
	  
	  private Blob profile;
	  
	  private LocalDate date = LocalDate.now();
	  
	  @Column(length = 5000)
	  private String description;
	  
	  private long designationId;
	  
	  private Long departmentId;
	  
	  private long roleId;
	  
	  private String roleType;
	  
	  private long phoneNumber;
	  
	  private String userId;
	  
	  private boolean status;
	  
	  public long getShiftTypeId() {
	    return this.shiftTypeId;
	  }
	  
	  public void setShiftTypeId(long shiftTypeId) {
	    this.shiftTypeId = shiftTypeId;
	  }
	  
	  public long getShiftId() {
	    return this.shiftId;
	  }
	  
	  public void setShiftId(long shiftId) {
	    this.shiftId = shiftId;
	  }
	  
	  public String getAttendanceType() {
	    return this.attendanceType;
	  }
	  
	  public void setAttendanceType(String attendanceType) {
	    this.attendanceType = attendanceType;
	  }
	  
	  public String getConfirmPassword() {
	    return this.confirmPassword;
	  }
	  
	  public void setConfirmPassword(String confirmPassword) {
	    this.confirmPassword = confirmPassword;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public String getUserName() {
	    return this.userName;
	  }
	  
	  public void setUserName(String userName) {
	    this.userName = userName;
	  }
	  
	  public String getGender() {
	    return this.gender;
	  }
	  
	  public void setGender(String gender) {
	    this.gender = gender;
	  }
	  
	  public String getCountry() {
	    return this.country;
	  }
	  
	  public void setCountry(String country) {
	    this.country = country;
	  }
	  
	  public String getEmail() {
	    return this.email;
	  }
	  
	  public void setEmail(String email) {
	    this.email = email;
	  }
	  
	  public String getPassword() {
	    return this.password;
	  }
	  
	  public void setPassword(String password) {
	    this.password = password;
	  }
	  
	  public String getState() {
	    return this.state;
	  }
	  
	  public void setState(String state) {
	    this.state = state;
	  }
	  
	  public String getCity() {
	    return this.city;
	  }
	  
	  public void setCity(String city) {
	    this.city = city;
	  }
	  
	  public String getAddress() {
	    return this.address;
	  }
	  
	  public void setAddress(String address) {
	    this.address = address;
	  }
	  
	  public String getUrl() {
	    return this.url;
	  }
	  
	  public void setUrl(String url) {
	    this.url = url;
	  }
	  
	  public Date getDob() {
	    return this.dob;
	  }
	  
	  public void setDob(Date dob) {
	    this.dob = dob;
	  }
	  
	  public Blob getProfile() {
	    return this.profile;
	  }
	  
	  public void setProfile(Blob profile) {
	    this.profile = profile;
	  }
	  
	  public LocalDate getDate() {
	    return this.date;
	  }
	  
	  public void setDate(LocalDate date) {
	    this.date = date;
	  }
	  
	  public String getUserId() {
	    return this.userId;
	  }
	  
	  public void setUserId(String userId) {
	    this.userId = userId;
	  }
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  public long getDesignationId() {
	    return this.designationId;
	  }
	  
	  public void setDesignationId(long designationId) {
	    this.designationId = designationId;
	  }
	  
	  public Long getDepartmentId() {
	    return this.departmentId;
	  }
	  
	  public void setDepartmentId(Long departmentId) {
	    this.departmentId = departmentId;
	  }
	  
	  public long getRoleId() {
	    return this.roleId;
	  }
	  
	  public void setRoleId(long roleId) {
	    this.roleId = roleId;
	  }
	  
	  public String getRoleType() {
	    return this.roleType;
	  }
	  
	  public void setRoleType(String roleType) {
	    this.roleType = roleType;
	  }
	  
	  public long getPhoneNumber() {
	    return this.phoneNumber;
	  }
	  
	  public void setPhoneNumber(long phoneNumber) {
	    this.phoneNumber = phoneNumber;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	}

