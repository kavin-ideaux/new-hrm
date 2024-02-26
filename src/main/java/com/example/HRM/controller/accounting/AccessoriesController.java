package com.example.HRM.controller.accounting;

import com.example.HRM.entity.accounting.Accessories;
import com.example.HRM.service.accounting.AccessoriesService;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AccessoriesController {
	@Autowired
	  private AccessoriesService keyboardBrandService;
	  
	  @GetMapping({"/accessories"})
	  public ResponseEntity<?> getDetails() {
	    try {
	      Iterable<Accessories> assestDetails = this.keyboardBrandService.listAll();
	      return new ResponseEntity(assestDetails, HttpStatus.OK);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while retrieving l details.";
	      return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	  }
	  
	  @PostMapping({"/accessories/save"})
	  public ResponseEntity<?> saveBank(@RequestBody Accessories accessories) {
	    try {
	      this.keyboardBrandService.SaveorUpdate(accessories);
	      return ResponseEntity.status(HttpStatus.CREATED).body("keyboardBrand details saved successfully.");
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving keyboardBrand details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @RequestMapping({"/accessories/{keyboardBrandId}"})
	  private Optional<Accessories> getKeyboardBrand(@PathVariable(name = "accessoriesId") long accessoriesId) {
	    return this.keyboardBrandService.getKeyboardBrandById(Long.valueOf(accessoriesId));
	  }
	  
	  @PutMapping({"/accessories/edit/{keyboardBrandId}"})
	  public ResponseEntity<Accessories> updateKeyboardBrand(@PathVariable("accessoriesId") Long accessoriesId, @RequestBody Accessories brandDetails) {
	    try {
	      Accessories existingBrand = this.keyboardBrandService.findById(accessoriesId);
	      if (existingBrand == null)
	        return ResponseEntity.notFound().build(); 
	      existingBrand.setAccessoriesName(brandDetails.getAccessoriesName());
	      this.keyboardBrandService.save(existingBrand);
	      return ResponseEntity.ok(existingBrand);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/accessories/delete/{accessoriesId}"})
	  public ResponseEntity<String> deleteKeyboardBrand(@PathVariable("accessoriesId") Long accessoriesId) {
	    this.keyboardBrandService.deleteKeyboardBrandById(accessoriesId);
	    return ResponseEntity.ok("keyboardBrandId deleted successfully");
	  }
	}


