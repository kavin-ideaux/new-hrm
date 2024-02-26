package com.example.HRM.service.clientDetails;

import com.example.HRM.entity.clientDetails.Quotation;
import com.example.HRM.repository.clientDetails.QuotationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {

	@Autowired
	  private QuotationRepository QuotationRepository;
	  
	  public List<Quotation> listAll() {
	    return this.QuotationRepository.findAll();
	  }
	  
	  public void SaveorUpdate(Quotation quotation) {
	    this.QuotationRepository.save(quotation);
	  }
	  
	  public void save(Quotation quotation) {
	    this.QuotationRepository.save(quotation);
	  }
	  
	  public Quotation findById(Long quotationId) {
	    return this.QuotationRepository.findById(quotationId).get();
	  }
	  
	  public Quotation getByClientId(long id) {
	    return this.QuotationRepository.findByClientId(id).get();
	  }
	  
	  public void deleteById(Long quotationId) {
	    this.QuotationRepository.deleteById(quotationId);
	  }
	  
	  public Optional<Quotation> getQuotationById(Long quotationId) {
	    return this.QuotationRepository.findById(quotationId);
	  }
	  
	  public Optional<Quotation> getById1(Long id) {
	    return Optional.of(this.QuotationRepository.findById(id).get());
	  }
	}

