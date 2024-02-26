package com.example.HRM.entity.clientDetails;
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
@Table(name = "quotation")
public class Quotation {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long quotationId;
	  
	  private Long clientId;
	  
	  private long companyId;
	  
	  private Date date;
	  
	  private Long projectTypeId;
	  
	  private boolean stats;
	  
	  private boolean quotationLevel;
	  
	  private String reason;
	  
	  private String quotationStatus;
	  
	  private double amount;
	  
	  @OneToMany(cascade = {CascadeType.ALL})
	  @JoinColumn(name = "quotationId", referencedColumnName = "quotationId")
	  private List<TermsList> quotationList;
	  
	  @OneToMany(cascade = {CascadeType.ALL})
	  @JoinColumn(name = "quotationId", referencedColumnName = "quotationId")
	  private List<QuotationList> termsList;
	  
	  public double getAmount() {
	    return this.amount;
	  }
	  
	  public void setAmount(double amount) {
	    this.amount = amount;
	  }
	  
	  public boolean isQuotationLevel() {
	    return this.quotationLevel;
	  }
	  
	  public void setQuotationLevel(boolean quotationLevel) {
	    this.quotationLevel = quotationLevel;
	  }
	  
	  public String getQuotationStatus() {
	    return this.quotationStatus;
	  }
	  
	  public void setQuotationStatus(String quotationStatus) {
	    this.quotationStatus = quotationStatus;
	  }
	  
	  public String getReason() {
	    return this.reason;
	  }
	  
	  public void setReason(String reason) {
	    this.reason = reason;
	  }
	  
	  public long getQuotationId() {
	    return this.quotationId;
	  }
	  
	  public void setQuotationId(long quotationId) {
	    this.quotationId = quotationId;
	  }
	  
	  public Long getClientId() {
	    return this.clientId;
	  }
	  
	  public void setClientId(Long clientId) {
	    this.clientId = clientId;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public Long getProjectTypeId() {
	    return this.projectTypeId;
	  }
	  
	  public void setProjectTypeId(Long projectTypeId) {
	    this.projectTypeId = projectTypeId;
	  }
	  
	  public boolean isStats() {
	    return this.stats;
	  }
	  
	  public void setStats(boolean stats) {
	    this.stats = stats;
	  }
	  
	  public long getCompanyId() {
	    return this.companyId;
	  }
	  
	  public void setCompanyId(long companyId) {
	    this.companyId = companyId;
	  }
	  
	  public List<TermsList> getQuotationList() {
	    return this.quotationList;
	  }
	  
	  public void setQuotationList(List<TermsList> quotationList) {
	    this.quotationList = quotationList;
	  }
	  
	  public List<QuotationList> getTermsList() {
	    return this.termsList;
	  }
	  
	  public void setTermsList(List<QuotationList> termsList) {
	    this.termsList = termsList;
	  }
	}


