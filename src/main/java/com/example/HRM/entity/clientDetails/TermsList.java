package com.example.HRM.entity.clientDetails;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "terms")
public class TermsList {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long termListId;
	  
	  private String itemName;
	  
	  private int quantity;
	  
	  private double rate;
	  
	  private double amount;
	  
	  private String description;
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  public long getTermListId() {
	    return this.termListId;
	  }
	  
	  public void setTermListId(long termListId) {
	    this.termListId = termListId;
	  }
	  
	  public String getItemName() {
	    return this.itemName;
	  }
	  
	  public void setItemName(String itemName) {
	    this.itemName = itemName;
	  }
	  
	  public int getQuantity() {
	    return this.quantity;
	  }
	  
	  public void setQuantity(int quantity) {
	    this.quantity = quantity;
	  }
	  
	  public double getRate() {
	    return this.rate;
	  }
	  
	  public void setRate(double rate) {
	    this.rate = rate;
	  }
	  
	  public double getAmount() {
	    return this.amount;
	  }
	  
	  public void setAmount(double amount) {
	    this.amount = amount;
	  }
	}


