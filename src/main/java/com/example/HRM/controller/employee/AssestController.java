package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Assest;
import com.example.HRM.repository.employee.AssestRepository;
import com.example.HRM.service.employee.AssestService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AssestController {

	@Autowired
	  private AssestService assestservice;
	  
	  @Autowired
	  private AssestRepository repo;
	  
	  @GetMapping({"/asset"})
	  public ResponseEntity<?> getDetails(@RequestParam(required = true) String asset) {
	    try {
	      if ("assetDetails".equals(asset)) {
	        Iterable<Assest> assetDetails = this.assestservice.listAll();
	        List<Assest> sortedAssets = (List<Assest>)StreamSupport.stream(assetDetails.spliterator(), false).sorted(Comparator.comparing(Assest::getAssestId).reversed()).collect(Collectors.toList());
	        return new ResponseEntity(sortedAssets, HttpStatus.OK);
	      } 
	      String errorMessage = "The provided asset is not supported.";
	      return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while retrieving asset details.";
	      return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	  }
	  
	  @PostMapping({"/assest/save"})
	  public ResponseEntity<?> saveBank(@RequestBody Assest assest) {
	    try {
	      assest.setStatus(true);
	      this.assestservice.SaveorUpdate(assest);
	      return ResponseEntity.status(HttpStatus.CREATED).body("assest details saved successfully.");
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving assest details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @PutMapping({"/assest/or/{id}"})
	  public ResponseEntity<Boolean> toggleCustomerStatus(@PathVariable(name = "id") long assestId) {
	    try {
	      Assest existingAssest = this.assestservice.findById(Long.valueOf(assestId));
	      if (existingAssest != null) {
	        boolean currentStatus = existingAssest.isStatus();
	        existingAssest.setStatus(!currentStatus);
	        this.assestservice.save(existingAssest);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(existingAssest.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @RequestMapping({"/assest/{assestId}"})
	  private Assest getAssest(@PathVariable(name = "assestId") long assestId) {
	    return this.assestservice.findById(Long.valueOf(assestId));
	  }
	  
	  @PutMapping({"/assest/editassest/{assestId}"})
	  public ResponseEntity<Assest> updateAssest(@PathVariable("assestId") Long assestId, @RequestBody Assest assestDetails) {
	    try {
	      Assest existingAssest = this.assestservice.findById(assestId);
	      if (existingAssest == null)
	        return ResponseEntity.notFound().build(); 
	      existingAssest.setProductName(assestDetails.getProductName());
	      existingAssest.setSerialNumber(assestDetails.getSerialNumber());
	      existingAssest.setModelNumber(assestDetails.getModelNumber());
	      existingAssest.setBrand(assestDetails.getBrand());
	      existingAssest.setKeyboardBrand(assestDetails.getKeyboardBrand());
	      existingAssest.setMouseBrand(assestDetails.getMouseBrand());
	      existingAssest.setStatus(assestDetails.isStatus());
	      this.assestservice.save(existingAssest);
	      return ResponseEntity.ok(existingAssest);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/assest/assestdelete/{assestId}"})
	  public ResponseEntity<String> deleteprojectName(@PathVariable("assestId") Long assestId) {
	    this.assestservice.deleteAssestIdById(assestId);
	    return ResponseEntity.ok("assest deleted successfully");
	  }
	}

