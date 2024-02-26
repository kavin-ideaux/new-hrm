package com.example.HRM.entity.clientDetails;
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
@Table(name = "invoice")
public class ClientInvoice {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long invoiceId;
	  
	  private long companyId;
	  
	  private long clientId;
	  
	  private String gstType;
	  
	  private Date invoiceDate;
	  
	  @Column(length = 5000)
	  private String description;
	  
	  private String paymentType;
	  
	  private double amount;
	  
	  private double received;
	  
	  private double balance;
	  
	  private double taxAmount;
	  
	  private double roundOffAmount;
	  
	  private double balanceAmount;
	  
	  @OneToMany(cascade = {CascadeType.ALL})
	  @JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId")
	  private List<ClientInvoiceList> invoiceList;
	  
	  public String getGstType() {
	    return this.gstType;
	  }
	  
	  public void setGstType(String gstType) {
	    this.gstType = gstType;
	  }
	  
	  public long getInvoiceId() {
	    return this.invoiceId;
	  }
	  
	  public void setInvoiceId(long invoiceId) {
	    this.invoiceId = invoiceId;
	  }
	  
	  public long getCompanyId() {
	    return this.companyId;
	  }
	  
	  public void setCompanyId(long companyId) {
	    this.companyId = companyId;
	  }
	  
	  public long getClientId() {
	    return this.clientId;
	  }
	  
	  public void setClientId(long clientId) {
	    this.clientId = clientId;
	  }
	  
	  public Date getInvoiceDate() {
	    return this.invoiceDate;
	  }
	  
	  public void setInvoiceDate(Date invoiceDate) {
	    this.invoiceDate = invoiceDate;
	  }
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  public String getPaymentType() {
	    return this.paymentType;
	  }
	  
	  public void setPaymentType(String paymentType) {
	    this.paymentType = paymentType;
	  }
	  
	  public double getAmount() {
	    return this.amount;
	  }
	  
	  public void setAmount(double amount) {
	    this.amount = amount;
	  }
	  
	  public double getReceived() {
	    return this.received;
	  }
	  
	  public void setReceived(double received) {
	    this.received = received;
	  }
	  
	  public double getBalance() {
	    return this.balance;
	  }
	  
	  public void setBalance(double balance) {
	    this.balance = balance;
	  }
	  
	  public double getTaxAmount() {
	    return this.taxAmount;
	  }
	  
	  public void setTaxAmount(double taxAmount) {
	    this.taxAmount = taxAmount;
	  }
	  
	  public double getRoundOffAmount() {
	    return this.roundOffAmount;
	  }
	  
	  public void setRoundOffAmount(double roundOffAmount) {
	    this.roundOffAmount = roundOffAmount;
	  }
	  
	  public double getBalanceAmount() {
	    return this.balanceAmount;
	  }
	  
	  public void setBalanceAmount(double balanceAmount) {
	    this.balanceAmount = balanceAmount;
	  }
	  
	  public List<ClientInvoiceList> getInvoiceList() {
	    return this.invoiceList;
	  }
	  
	  public void setInvoiceList(List<ClientInvoiceList> invoiceList) {
	    this.invoiceList = invoiceList;
	  }
	}


