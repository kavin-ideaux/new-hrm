package com.example.HRM.service.clientDetails;
import com.example.HRM.entity.clientDetails.ClientInvoice;
import com.example.HRM.repository.clientDetails.ClientInvoiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientInvoiceService {
	 @Autowired
	  private ClientInvoiceRepository clientInvoiceRepository;
	  
	  public List<ClientInvoice> invoiceList() {
	    return this.clientInvoiceRepository.findAll();
	  }
	  
	  public void saveInvoice(ClientInvoice user) {
	    this.clientInvoiceRepository.save(user);
	  }
	  
	  public void deleteInvoiceById(Long id) {
	    this.clientInvoiceRepository.deleteById(id);
	  }
	  
	  public ClientInvoice findInvoiceById(Long id) {
	    return this.clientInvoiceRepository.findById(id).get();
	  }
	}


