package com.example.HRM.controller.clientDetails;
import com.example.HRM.entity.clientDetails.ClientInvoice;
import com.example.HRM.entity.clientDetails.Receipts;
import com.example.HRM.repository.clientDetails.ReceiptRepository;
import com.example.HRM.service.clientDetails.ClientInvoiceService;
import com.example.HRM.service.clientDetails.ReceiptService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReceiptController {
	@Autowired
	  private ReceiptService receiptService;
	  
	  @Autowired
	  private ClientInvoiceService clientInvoiceService;
	  
	  @Autowired
	  private ReceiptRepository receiptRepository;
	  
	  @PostMapping({"/receipt/save"})
	  public String saveBook(@RequestBody Receipts receipt) {
	    double receivedAmount = receipt.getReceivedAmount();
	    receipt.setReceivedAmount(receivedAmount);
	    ClientInvoice invoice = this.clientInvoiceService.findInvoiceById(Long.valueOf(receipt.getInvoiceId()));
	    if (invoice.getBalanceAmount() == 0.0D)
	      return "Invoice balance is already 0. No further payment required."; 
	    this.receiptService.SaveReceipt(receipt);
	    double balance = invoice.getBalanceAmount() - receivedAmount;
	    invoice.setBalanceAmount(balance);
	    this.clientInvoiceService.saveInvoice(invoice);
	    if (balance == 0.0D)
	      return "Payment successfully saved. Balance is now 0."; 
	    return "Payment successfully saved. Balance remaining: " + balance;
	  }
}
	  
//	  @GetMapping({"/receipt/details"})
//	  public ResponseEntity<Object> getAllReceiptDetailsByInvoice(@RequestParam(required = true) String receipt) {
//	    if ("Receipts".equals(receipt)) {
//	      List<Map<String, Object>> receiptList = new ArrayList<>();
//	      List<Map<String, Object>> receiptDetails = this.receiptRepository.getAllReceiptDetailsByInvoice();
//	      Map<String, List<Map<String, Object>>> receiptGroupMap = (Map<String, List<Map<String, Object>>>)receiptDetails.stream().collect(Collectors.groupingBy(action -> action.get("client_id").toString()));
//	      for (Map.Entry<String, List<Map<String, Object>>> receiptLoop : receiptGroupMap.entrySet()) {
//	        Map<String, Object> receiptMap = new HashMap<>();
//	        receiptMap.put("client_id", receiptLoop.getKey());
//	        receiptMap.put("client_name", ((Map)((List<Map>)receiptLoop.getValue()).get(0)).get("client_name"));
//	        receiptMap.put("clientDetails", receiptLoop.getValue());
//	        receiptList.add(receiptMap);
//	      } 
//	      return ResponseEntity.ok(receiptList);
//	    } 
//	    String errorMessage = "Invalid value for 'receipt'. Expected 'Receipts'.";
//	    return ResponseEntity.badRequest().body(errorMessage);
//	  }
//	  
//	  @GetMapping({"/receipt/{id}"})
//	  public List<Map<String, Object>> getAllReceiptDetailsByInvoiceId(@PathVariable("id") Long client_id) {
//	    List<Map<String, Object>> receiptList = new ArrayList<>();
//	    List<Map<String, Object>> receiptDetails = this.receiptRepository.getAllReceiptDetailsByClientId(client_id);
//	    Map<String, List<Map<String, Object>>> receiptGroupMap = (Map<String, List<Map<String, Object>>>)receiptDetails.stream().collect(Collectors.groupingBy(action -> action.get("client_id").toString()));
//	    for (Map.Entry<String, List<Map<String, Object>>> receiptLoop : receiptGroupMap.entrySet()) {
//	      Map<String, Object> receiptMap = new HashMap<>();
//	      receiptMap.put("client_id", receiptLoop.getKey());
//	      receiptMap.put("client_name", ((Map)((List<Map>)receiptLoop.getValue()).get(0)).get("client_name"));
//	      receiptMap.put("clientDetails", receiptLoop.getValue());
//	      receiptList.add(receiptMap);
//	    } 
//	    return receiptList;
//	  }
//	}

