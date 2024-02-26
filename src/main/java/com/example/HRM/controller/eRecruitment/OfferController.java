package com.example.HRM.controller.eRecruitment;
import com.example.HRM.entity.erecruitment.Offer;
import com.example.HRM.repository.erecruitment.OfferRepository;
import com.example.HRM.service.eRecruitment.OfferService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class OfferController {
	@Autowired
	  private OfferService offerService;
	  
	  @Autowired
	  private OfferRepository offerRepository;
	  
	  @PostMapping({"/offer/save"})
	  public ResponseEntity<?> saveOffer(@RequestParam("projectName") Date joiningDate, @RequestParam("expiryDate") Date expiryDate, @RequestParam("salaryPackage") String salaryPackage, @RequestParam("acceptanceStatus") boolean acceptanceStatus, @RequestParam("candidateId") long candidateId, @RequestParam("appointmentId") long appointmentId, @RequestParam("certificate") MultipartFile certificate) throws SQLException {
	    try {
	      Offer offer = new Offer();
	      offer.setJoiningDate(joiningDate);
	      offer.setExpiryDate(expiryDate);
	      offer.setSalaryPackage(salaryPackage);
	      offer.setAcceptanceStatus(acceptanceStatus);
	      offer.setAppointmentId(appointmentId);
	      offer.setCandidateId(candidateId);
	      offer.setCertificate(convertToBlob(certificate));
	      this.offerService.SaveOfferLetter(offer);
	      return ResponseEntity.ok("Offer saved successfully.");
	    } catch (IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while saving the offer: " + e.getMessage());
	    } 
	  }
	  
	  private Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
	    if (file != null && !file.isEmpty()) {
	      byte[] bytes = file.getBytes();
	      return new SerialBlob(bytes);
	    } 
	    return null;
	  }
	  
	  @GetMapping({"/offer/view"})
	  public ResponseEntity<?> getUser() {
	    try {
	      List<Offer> offer = this.offerService.listAll();
	      List<Offer> offerList = new ArrayList<>();
	      for (Offer offers : offer) {
	        String certificateUrl = "/offerLetter/" + offers.getOfferId();
	        Offer offerResponse = new Offer();
	        offerResponse.setOfferId(offers.getOfferId());
	        offerResponse.setAppointmentId(offers.getAppointmentId());
	        offerResponse.setAcceptanceStatus(offers.isAcceptanceStatus());
	        offerResponse.setCandidateId(offers.getCandidateId());
	        offerResponse.setCertificateUrl(certificateUrl);
	        offerResponse.setExpiryDate(offers.getExpiryDate());
	        offerResponse.setJoiningDate(offers.getJoiningDate());
	        offerResponse.setSalaryPackage(offers.getSalaryPackage());
	        offerList.add(offerResponse);
	      } 
	      return ResponseEntity.ok(offerList);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving offerLetter");
	    } 
	  }
	  
	  @GetMapping({"/offerLetter/{offerId}"})
	  public ResponseEntity<byte[]> downloadOfferFile(@PathVariable long offerId) {
	    try {
	      Offer offer = this.offerService.findById(Long.valueOf(offerId));
	      if (offer != null) {
	        Blob pdfBlob = offer.getCertificate();
	        if (pdfBlob != null) {
	          byte[] pdfBytes = pdfBlob.getBytes(1L, (int)pdfBlob.length());
	          HttpHeaders headers = new HttpHeaders();
	          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	          String filename = "offerFile.pdf";
	          headers.setContentDispositionFormData("attachment", filename);
	          return new ResponseEntity(pdfBytes, (MultiValueMap)headers, HttpStatus.OK);
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/offerLetter/{offerId}"})
	  public ResponseEntity<String> updateOfferFile(@PathVariable long offerId, @RequestParam("joiningDate") Date joiningDate, @RequestParam("expiryDate") Date expiryDate, @RequestParam("salaryPackage") String salaryPackage, @RequestParam("acceptanceStatus") boolean acceptanceStatus, @RequestParam("candidateId") long candidateId, @RequestParam("appointmentId") long appointmentId, @RequestParam("certificate") MultipartFile file) {
	    try {
	      Offer offer = this.offerService.findById(Long.valueOf(offerId));
	      if (offer != null) {
	        Blob pdfBlob = offer.getCertificate();
	        if (pdfBlob != null) {
	          byte[] newPdfBytes = file.getBytes();
	          pdfBlob.setBytes(1L, newPdfBytes);
	          offer.setAcceptanceStatus(acceptanceStatus);
	          offer.setJoiningDate(joiningDate);
	          offer.setExpiryDate(expiryDate);
	          offer.setSalaryPackage(salaryPackage);
	          offer.setCandidateId(candidateId);
	          offer.setAppointmentId(appointmentId);
	          this.offerService.SaveOfferLetter(offer);
	          return ResponseEntity.ok("Offer updated successfully.");
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException|IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/offer/delete/{id}"})
	  public ResponseEntity<String> deleteOffer(@PathVariable("id") Long id) {
	    this.offerService.deleteOfferId(id);
	    return ResponseEntity.ok("Offer details deleted successfully");
	  }
	  
	  @GetMapping({"/FindOfferDetails"})
	  public ResponseEntity<?> getOfferDetails() {
	    try {
	      List<Map<String, Object>> offers = this.offerRepository.FindOfferDetails();
	      List<Map<String, Object>> offerList = new ArrayList<>();
	      for (Map<String, Object> offer : offers) {
	        Map<String, Object> offerMap = new HashMap<>();
	        String certificateUrl = "/offerLetter/" + offer.get("offer_id");
	        offerMap.put("certificateUrl", certificateUrl);
	        offerMap.putAll(offer);
	        offerList.add(offerMap);
	      } 
	      return ResponseEntity.ok(offerList);
	    } catch (Exception e) {
	      String errorMessage = "Error occurred while retrieving candidate details";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Collections.singletonMap("error", errorMessage));
	    } 
	  }
	}
