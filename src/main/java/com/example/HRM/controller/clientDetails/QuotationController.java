package com.example.HRM.controller.clientDetails;
import com.example.HRM.entity.clientDetails.Quotation;
import com.example.HRM.repository.clientDetails.QuotationRepository;
import com.example.HRM.service.clientDetails.QuotationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class QuotationController {
	@Autowired
	  private QuotationService quotationService;
	  
	  @Autowired
	  private QuotationRepository quotationRepository;
	  
	  @GetMapping({"/qutty"})
	  public ResponseEntity<?> getAllQuotation() {
	    return ResponseEntity.ok(this.quotationService.listAll());
	  }
	  
	  @PostMapping({"/quotation/save"})
	  public ResponseEntity<String> saveQuotation(@RequestBody Quotation quotation) {
	    try {
	      quotation.setQuotationStatus("pending");
	      this.quotationService.SaveorUpdate(quotation);
	      long quotationId = quotation.getQuotationId();
	      return ResponseEntity.ok("Quotation saved successfully. quotation ID: " + quotationId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving quotation: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/quotation/edit/{id}"})
	  public ResponseEntity<Quotation> updateQuotation(@PathVariable("id") long id, @RequestBody Quotation quotation) {
	    try {
	      Quotation existingQuotation = this.quotationService.findById(Long.valueOf(id));
	      if (existingQuotation == null)
	        return ResponseEntity.notFound().build(); 
	      existingQuotation.setClientId(quotation.getClientId());
	      existingQuotation.setCompanyId(quotation.getCompanyId());
	      existingQuotation.setDate(quotation.getDate());
	      existingQuotation.setProjectTypeId(quotation.getProjectTypeId());
	      existingQuotation.setStats(quotation.isStats());
	      existingQuotation.setQuotationLevel(quotation.isQuotationLevel());
	      existingQuotation.setReason(quotation.getReason());
	      existingQuotation.setQuotationStatus(quotation.getQuotationStatus());
	      existingQuotation.setTermsList(quotation.getTermsList());
	      existingQuotation.setQuotationList(quotation.getQuotationList());
	      this.quotationService.SaveorUpdate(existingQuotation);
	      return ResponseEntity.ok(existingQuotation);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @GetMapping({"/quotation/view"})
	  public ResponseEntity<?> getAllQuotationByClientDetails(@RequestParam(required = true) String quotation) {
	    if ("details".equals(quotation)) {
	      ObjectMapper object = new ObjectMapper();
	      List<Quotation> quotationList = this.quotationService.listAll();
	      List<Map<String, Object>> quotationDataList = (List<Map<String, Object>>)object.convertValue(quotationList, List.class);
	      List<Map<String, Object>> quotationDetails = this.quotationRepository.getAllQuotationByClientDetails();
	      Map<String, Map<String, Object>> quotationMap = new HashMap<>();
	      for (Map<String, Object> map : quotationDetails)
	        quotationMap.put(map.get("quotation_id").toString(), map); 
	      for (Map<String, Object> map : quotationDataList) {
	        Map<String, Object> innerMap = quotationMap.get(map.get("quotationId").toString());
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
	        map.put("projectType", innerMap.get("project_type"));
	      } 
	      return ResponseEntity.ok(quotationDataList);
	    } 
	    String errorMessage = "Invalid value for 'quotation'. Expected 'details'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PutMapping({"/quotation/approval/rejection/{quotationId}"})
	  public ResponseEntity<?> approvalOrRejection(@PathVariable("quotationId") long quotationId, @RequestParam("quotationStatus") String quotationStatus) {
	    Quotation existingQuotation = this.quotationService.findById(Long.valueOf(quotationId));
	    if (existingQuotation != null) {
	      existingQuotation.setQuotationStatus(quotationStatus);
	      if ("confirmed".equals(existingQuotation.getQuotationStatus())) {
	        existingQuotation.setQuotationLevel(true);
	      } else if ("cancelled".equals(existingQuotation.getQuotationStatus())) {
	        existingQuotation.setQuotationLevel(false);
	      } else {
	        existingQuotation.setQuotationLevel(false);
	      } 
	      this.quotationService.SaveorUpdate(existingQuotation);
	      return ResponseEntity.ok("quotation status updated successfully.");
	    } 
	    return ResponseEntity.notFound().build();
	  }
	}

