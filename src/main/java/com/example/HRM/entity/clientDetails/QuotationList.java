package com.example.HRM.entity.clientDetails;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotation_list")
public class QuotationList {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long quotationListId;
	  
	  private String termsAndCondition;
	  
	  private String additionalNotes;
	  
	  public long getQuotationListId() {
	    return this.quotationListId;
	  }
	  
	  public void setQuotationListId(long quotationListId) {
	    this.quotationListId = quotationListId;
	  }
	  
	  public String getTermsAndCondition() {
	    return this.termsAndCondition;
	  }
	  
	  public void setTermsAndCondition(String termsAndCondition) {
	    this.termsAndCondition = termsAndCondition;
	  }
	  
	  public String getAdditionalNotes() {
	    return this.additionalNotes;
	  }
	  
	  public void setAdditionalNotes(String additionalNotes) {
	    this.additionalNotes = additionalNotes;
	  }
	}


