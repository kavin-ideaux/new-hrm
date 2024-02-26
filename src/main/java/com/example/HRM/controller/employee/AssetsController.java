package com.example.HRM.controller.employee;

import com.example.HRM.entity.accounting.CompanyAssets;
import com.example.HRM.entity.employee.Assets;
import com.example.HRM.entity.employee.AssetsList;
import com.example.HRM.service.accounting.CompanyAssetsService;
import com.example.HRM.service.employee.AssetsService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AssetsController {

	@Autowired
	  private AssetsService service;
	  
	  @Autowired
	  private CompanyAssetsService companyAssetsService;
	  
	  @GetMapping({"/assets"})
	  public ResponseEntity<?> getDetails(@RequestParam(required = true) String assets) {
	    try {
	      if ("assetDetail".equals(assets)) {
	        Iterable<Assets> assetDetails = this.service.listAll();
	        List<Assets> sortedAssets = (List<Assets>)StreamSupport.stream(assetDetails.spliterator(), false).sorted(Comparator.comparing(Assets::getAssetsId).reversed()).collect(Collectors.toList());
	        return new ResponseEntity(sortedAssets, HttpStatus.OK);
	      } 
	      String errorMessage = "The provided asset is not supported.";
	      return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while retrieving asset details.";
	      return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	  }
	  
	  @PostMapping({"/assets/save"})
	  public ResponseEntity<String> saveAssets(@RequestBody Assets assets) {
	    try {
	      Long brantId = null;
	      Long accessoriesId = null;
	      Integer count = null;
	      List<AssetsList> assetsList = assets.getAssets();
	      for (AssetsList asset : assetsList) {
	        brantId = asset.getBrantId();
	        accessoriesId = asset.getAccessoriesId();
	        count = Integer.valueOf(asset.getCount());
	      } 
	      CompanyAssets existingCompanyAssets = this.companyAssetsService.findByAccessoriesIdAndBrantId(accessoriesId, brantId);
	      if (existingCompanyAssets != null) {
	        existingCompanyAssets.setCount(existingCompanyAssets.getCount() - count.intValue());
	        this.companyAssetsService.save(existingCompanyAssets);
	      } 
	      this.service.save(assets);
	      System.out.println("Saving assets: " + assets);
	      long assetsId = assets.getAssetsId();
	      return ResponseEntity.ok("Assets saved successfully. Assets ID: " + assetsId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving assets: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/assets/edit/{id}"})
	  public ResponseEntity<Assets> updatePayroll(@PathVariable("id") Long assetsId, @RequestBody Assets assets) {
	    try {
	      Assets existingSalaryType = this.service.findById(assetsId);
	      if (existingSalaryType == null)
	        return ResponseEntity.notFound().build(); 
	      existingSalaryType.setAssetsDate(assets.getAssetsDate());
	      existingSalaryType.setAssets(assets.getAssets());
	      this.service.save(existingSalaryType);
	      return ResponseEntity.ok(existingSalaryType);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/assets/delete/{id}"})
	  public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
	    try {
	      this.service.deleteMemberById(Long.valueOf(id));
	      return ResponseEntity.ok("Assets deleted successfully");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error deleting Assets: " + e.getMessage());
	    } 
	  }
	}

