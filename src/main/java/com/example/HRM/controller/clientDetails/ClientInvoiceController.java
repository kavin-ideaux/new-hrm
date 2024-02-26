package com.example.HRM.controller.clientDetails;

import com.example.HRM.entity.clientDetails.ClientInvoice;
import com.example.HRM.entity.clientDetails.Receipts;
import com.example.HRM.repository.clientDetails.ClientInvoiceRepository;
import com.example.HRM.repository.clientDetails.ReceiptRepository;
import com.example.HRM.service.clientDetails.ClientInvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ClientInvoiceController {

	@Autowired
	  private ClientInvoiceService clientInvoiceService;
	  
	  @Autowired
	  private ClientInvoiceRepository clientInvoiceRepository;
	  
	  @Autowired
	  private ReceiptRepository receiptRepository;
	  
	  @PostMapping({"/invoice/save"})
	  private long saveInvoice(@RequestBody ClientInvoice clientInvoice) {
	    double balance = clientInvoice.getBalanceAmount();
	    clientInvoice.setBalance(balance);
	    this.clientInvoiceService.saveInvoice(clientInvoice);
	    long invoiceId = clientInvoice.getInvoiceId();
	    double balanceAmount = clientInvoice.getBalanceAmount();
	    Receipts receipts = new Receipts();
	    receipts.setInvoiceId(invoiceId);
	    receipts.setBalance(balanceAmount);
	    this.receiptRepository.save(receipts);
	    return clientInvoice.getInvoiceId();
	  }
	  
	  @PutMapping({"/invoice/edit/{id}"})
	  public ResponseEntity<ClientInvoice> updateInvoice(@PathVariable("id") Long invoiceId, @RequestBody ClientInvoice clientInvoice) {
	    try {
	      ClientInvoice existingInvoice = this.clientInvoiceService.findInvoiceById(invoiceId);
	      if (existingInvoice == null)
	        return ResponseEntity.notFound().build(); 
	      existingInvoice.setAmount(clientInvoice.getAmount());
	      existingInvoice.setBalance(clientInvoice.getBalance());
	      existingInvoice.setBalanceAmount(clientInvoice.getBalanceAmount());
	      existingInvoice.setClientId(clientInvoice.getClientId());
	      existingInvoice.setCompanyId(clientInvoice.getCompanyId());
	      existingInvoice.setDescription(clientInvoice.getDescription());
	      existingInvoice.setInvoiceDate(clientInvoice.getInvoiceDate());
	      existingInvoice.setInvoiceList(clientInvoice.getInvoiceList());
	      existingInvoice.setPaymentType(clientInvoice.getPaymentType());
	      existingInvoice.setReceived(clientInvoice.getReceived());
	      existingInvoice.setTaxAmount(clientInvoice.getTaxAmount());
	      existingInvoice.setRoundOffAmount(clientInvoice.getRoundOffAmount());
	      existingInvoice.setPaymentType(clientInvoice.getPaymentType());
	      this.clientInvoiceService.saveInvoice(existingInvoice);
	      return ResponseEntity.ok(existingInvoice);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/invoice/delete/{id}"})
	  public ResponseEntity<String> deleteInvoice(@PathVariable("id") Long invoiceId) {
	    this.clientInvoiceService.deleteInvoiceById(invoiceId);
	    return ResponseEntity.ok("Invoice deleted successfully");
	  }
	  
	  @GetMapping({"/invoice"})
	  public ResponseEntity<?> getAllQuotationByClientDetails(@RequestParam(required = true) String invoice) {
	    if ("invoices".equals(invoice)) {
	      ObjectMapper object = new ObjectMapper();
	      List<ClientInvoice> clientInvoiceList = this.clientInvoiceService.invoiceList();
	      List<Map<String, Object>> clientDataList = (List<Map<String, Object>>)object.convertValue(clientInvoiceList, List.class);
	      List<Map<String, Object>> clientDetails = this.clientInvoiceRepository.getAllInvoiceDetailsList();
	      Map<String, Map<String, Object>> clientMap = new HashMap<>();
	      for (Map<String, Object> map : clientDetails)
	        clientMap.put(map.get("invoice_id").toString(), map); 
	      for (Map<String, Object> map : clientDataList) {
	        String invoiceId = map.get("invoiceId").toString();
	        Map<String, Object> innerMap = clientMap.get(invoiceId);
	        if (innerMap != null) {
	          map.put("clientName", innerMap.get("client_name"));
	          map.put("city", innerMap.get("city"));
	          map.put("address", innerMap.get("address"));
	          map.put("country", innerMap.get("country"));
	          map.put("email", innerMap.get("email"));
	          map.put("gender", innerMap.get("gender"));
	          map.put("phoneNumber", innerMap.get("phone_number"));
	          map.put("state", innerMap.get("state"));
	          map.put("zipCode", innerMap.get("zip_code"));
	          map.put("mobileNumber", innerMap.get("mobile_number"));
	          map.put("companyEmail", innerMap.get("companyEmail"));
	          map.put("companyName", innerMap.get("company_name"));
	          map.put("companyPhoneNumber", innerMap.get("phone_number1"));
	          map.put("companyMobileNumber", innerMap.get("phone_number2"));
	          map.put("companyCountry", innerMap.get("companyCountry"));
	          map.put("companyAddress", innerMap.get("companyAddress"));
	        } 
	      } 
	      return ResponseEntity.ok(clientDataList);
	    } 
	    String errorMessage = "Invalid value for 'invoice'. Expected 'invoices'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	}

