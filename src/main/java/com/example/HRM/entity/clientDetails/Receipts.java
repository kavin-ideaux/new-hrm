package com.example.HRM.entity.clientDetails;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "receipt")
public class Receipts {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long receiptId;
	  
	  private long invoiceId;
	  
	  private Date paymentDate;
	  
	  private double receivedAmount;
	  
	  private String paymentType;
	  
	  private double balance;
	  
	  public long getReceiptId() {
	    return this.receiptId;
	  }
	  
	  public void setReceiptId(long receiptId) {
	    this.receiptId = receiptId;
	  }
	  
	  public long getInvoiceId() {
	    return this.invoiceId;
	  }
	  
	  public void setInvoiceId(long invoiceId) {
	    this.invoiceId = invoiceId;
	  }
	  
	  public Date getPaymentDate() {
	    return this.paymentDate;
	  }
	  
	  public void setPaymentDate(Date paymentDate) {
	    this.paymentDate = paymentDate;
	  }
	  
	  public double getReceivedAmount() {
	    return this.receivedAmount;
	  }
	  
	  public void setReceivedAmount(double receivedAmount) {
	    this.receivedAmount = receivedAmount;
	  }
	  
	  public String getPaymentType() {
	    return this.paymentType;
	  }
	  
	  public void setPaymentType(String paymentType) {
	    this.paymentType = paymentType;
	  }
	  
	  public double getBalance() {
	    return this.balance;
	  }
	  
	  public void setBalance(double balance) {
	    this.balance = balance;
	  }
	}


