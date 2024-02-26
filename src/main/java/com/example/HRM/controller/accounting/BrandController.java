package com.example.HRM.controller.accounting;
import com.example.HRM.entity.accounting.Brand;
import com.example.HRM.service.accounting.BrandService;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BrandController {

	@Autowired
	  private BrandService brandService;
	  
	  @GetMapping({"/Brand"})
	  public ResponseEntity<?> getDetails() {
	    try {
	      Iterable<Brand> assestDetails = this.brandService.listAll();
	      List<Brand> sortedAssets = (List<Brand>)StreamSupport.stream(assestDetails.spliterator(), false).sorted(Comparator.comparing(Brand::getBrandId).reversed()).collect(Collectors.toList());
	      return new ResponseEntity(sortedAssets, HttpStatus.OK);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while retrieving l details.";
	      return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	  }
	  
	  @PostMapping({"/Brand/save"})
	  public ResponseEntity<?> saveBank(@RequestBody Brand brand) {
	    try {
	      this.brandService.SaveorUpdate(brand);
	      return ResponseEntity.status(HttpStatus.CREATED).body("brand details saved successfully.");
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving brand details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @RequestMapping({"/Brand/{brandId}"})
	  private Optional<Brand> getBrand(@PathVariable(name = "brandId") long brandId) {
	    return this.brandService.getBrandById(Long.valueOf(brandId));
	  }
	  
	  @PutMapping({"/Brand/editBrand/{brandId}"})
	  public ResponseEntity<Brand> updateBrand(@PathVariable("brandId") Long brandId, @RequestBody Brand brandDetails) {
	    try {
	      Brand existingBrand = this.brandService.findById(brandId);
	      if (existingBrand == null)
	        return ResponseEntity.notFound().build(); 
	      existingBrand.setBrandName(brandDetails.getBrandName());
	      this.brandService.save(existingBrand);
	      return ResponseEntity.ok(existingBrand);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/Brand/branddelete/{brandId}"})
	  public ResponseEntity<String> deleteBrand(@PathVariable("brandId") Long brandId) {
	    this.brandService.deleteBrandById(brandId);
	    return ResponseEntity.ok("Brand deleted successfully");
	  }
	}

