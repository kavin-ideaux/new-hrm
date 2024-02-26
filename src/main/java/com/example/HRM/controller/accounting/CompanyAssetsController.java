package com.example.HRM.controller.accounting;

import com.example.HRM.entity.accounting.CompanyAssets;
import com.example.HRM.repository.accounting.CompanyAssetsRepository;
import com.example.HRM.service.accounting.CompanyAssetsService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class CompanyAssetsController {
	 @Autowired
	  private CompanyAssetsService service;
	  
	  @Autowired
	  private CompanyAssetsRepository repo;
	  
	  @GetMapping({"/companyassets"})
	  public ResponseEntity<?> displayAllImages(@RequestParam(required = true) String CompanyAssetsType) {
	    try {
	      if ("companyassets".equals(CompanyAssetsType)) {
	        List<CompanyAssets> CompanyAssetss = this.service.listAll();
	        List<CompanyAssets> CompanyAssetsResponses = new ArrayList<>();
	        for (CompanyAssets CompanyAssets : CompanyAssetss) {
	          int randomNumber = generateRandomNumber();
	          String imageUrl = "companyassets/" + randomNumber + "/" + CompanyAssets.getCompanyAssetsId();
	          CompanyAssets CompanyAssetsResponse = new CompanyAssets();
	          CompanyAssetsResponse.setAccessoriesId(CompanyAssets.getAccessoriesId());
	          CompanyAssetsResponse.setCompanyAssetsId(CompanyAssets.getCompanyAssetsId());
	          CompanyAssetsResponse.setUrl(imageUrl);
	          CompanyAssetsResponse.setDate(CompanyAssets.getDate());
	          CompanyAssetsResponse.setAssetValues(CompanyAssets.getAssetValues());
	          CompanyAssetsResponse.setBrantId(CompanyAssets.getBrantId());
	          CompanyAssetsResponse.setCount(CompanyAssets.getCount());
	          CompanyAssetsResponse.setStatus(CompanyAssets.isStatus());
	          CompanyAssetsResponses.add(CompanyAssetsResponse);
	        } 
	        return ResponseEntity.ok().body(CompanyAssetsResponses);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	        .body("The provided CompanyAssetsType is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PostMapping({"/companyassets/save"})
	  public ResponseEntity<String> addCompanyAssetsWithImage(@RequestParam("billing") MultipartFile file, @RequestParam("count") int count, @RequestParam("brantId") Long brantId, @RequestParam("date") Date date, @RequestParam("accessoriesId") Long accessoriesId, @RequestParam("assetValues") int assetValues) {
	    try {
	      byte[] bytes = file.getBytes();
	      Blob blob = new SerialBlob(bytes);
	      CompanyAssets existingCompanyAssets = this.service.findByAccessoriesIdAndBrantId(accessoriesId, brantId);
	      if (existingCompanyAssets != null) {
	        existingCompanyAssets.setCount(existingCompanyAssets.getCount() + count);
	        this.service.save(existingCompanyAssets);
	      } else {
	        CompanyAssets companyAssets = new CompanyAssets();
	        companyAssets.setBilling(blob);
	        companyAssets.setAccessoriesId(accessoriesId);
	        companyAssets.setBrantId(brantId);
	        companyAssets.setCount(count);
	        companyAssets.setDate(date);
	        companyAssets.setAssetValues(assetValues);
	        companyAssets.setStatus(true);
	        this.service.save(companyAssets);
	      } 
	      return ResponseEntity.status(HttpStatus.CREATED).body("CompanyAssets details saved successfully.");
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding CompanyAssets.");
	    } 
	  }
	  
	  @RequestMapping({"/companyassets/{companyassetsId}"})
	  private Optional<CompanyAssets> getCompanyAssets(@PathVariable(name = "companyassetsId") long CompanyAssetsId) {
	    return this.service.getCompanyAssetsById(Long.valueOf(CompanyAssetsId));
	  }
	  
	  private int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(1000000);
	  }
	  
	  @GetMapping({"companyassets/{randomNumber}/{id}"})
	  public ResponseEntity<ByteArrayResource> serveFile(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<CompanyAssets> complaintsOptional = this.service.getCompanyAssetsById(id);
	    if (complaintsOptional.isPresent()) {
	      byte[] fileBytes;
	      CompanyAssets complaints = complaintsOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        fileBytes = complaints.getBilling().getBytes(1L, (int)complaints.getBilling().length());
	      } catch (SQLException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "inline; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  private String determineFileExtension(byte[] fileBytes) {
	    try {
	      String fileSignature = bytesToHex(Arrays.copyOfRange(fileBytes, 0, 4));
	      if (fileSignature.startsWith("89504E47"))
	        return "png"; 
	      if (fileSignature.startsWith("FFD8FF"))
	        return "jpg"; 
	      if (fileSignature.startsWith("52494646"))
	        return "webp"; 
	      if (fileSignature.startsWith("47494638"))
	        return "gif"; 
	      if (fileSignature.startsWith("66747970"))
	        return "mp4"; 
	      if (fileSignature.startsWith("25504446"))
	        return "pdf"; 
	    } catch (Exception exception) {}
	    return "unknown";
	  }
	  
	  private MediaType determineMediaType(String extension) {
	    switch (extension) {
	      case "png":
	        return MediaType.IMAGE_PNG;
	      case "jpg":
	        return MediaType.IMAGE_JPEG;
	      case "pdf":
	        return MediaType.APPLICATION_PDF;
	      case "webp":
	        return MediaType.parseMediaType("image/webp");
	      case "gif":
	        return MediaType.parseMediaType("image/gif");
	      case "mp4":
	        return MediaType.parseMediaType("video/mp4");
	    } 
	    return MediaType.APPLICATION_OCTET_STREAM;
	  }
	  
	  private String bytesToHex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	      sb.append(String.format("%02X", new Object[] { Byte.valueOf(b) }));
	    } 
	    return sb.toString();
	  }
	  
	  @PutMapping({"/companyassets/or/{companyassets_id}"})
	  public ResponseEntity<Boolean> toggleCustomerStatus(@PathVariable(name = "companyassets_id") long CompanyAssets_id) {
	    try {
	      CompanyAssets CompanyAssets = this.service.findById(Long.valueOf(CompanyAssets_id));
	      if (CompanyAssets != null) {
	        boolean currentStatus = CompanyAssets.isStatus();
	        CompanyAssets.setStatus(!currentStatus);
	        this.service.SaveorUpdate(CompanyAssets);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(CompanyAssets.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/companyassets/edit/{companyassetsId}"})
	  public ResponseEntity<?> updateCompanyAssets(@PathVariable Long companyAssetsId, @RequestParam(value = "billing", required = false) MultipartFile file, @RequestParam(value = "count", required = false) int count, @RequestParam(value = "brantId", required = false) Long brantId, @RequestParam(value = "date", required = false) Date date, @RequestParam(value = "accessoriesId", required = false) Long accessoriesId, @RequestParam(value = "assetValues", required = false) int assetValues) {
	    try {
	      CompanyAssets existingCompanyAssets = this.service.findById(companyAssetsId);
	      if (existingCompanyAssets == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company asset not found."); 
	      existingCompanyAssets.setAccessoriesId(accessoriesId);
	      existingCompanyAssets.setBrantId(brantId);
	      existingCompanyAssets.setCount(count);
	      existingCompanyAssets.setDate(date);
	      existingCompanyAssets.setAssetValues(assetValues);
	      if (file != null && !file.isEmpty()) {
	        byte[] bytes = file.getBytes();
	        Blob blob = new SerialBlob(bytes);
	        existingCompanyAssets.setBilling(blob);
	      } 
	      this.service.save(existingCompanyAssets);
	      return ResponseEntity.ok(existingCompanyAssets);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating company asset.");
	    } 
	  }
	  
	  @DeleteMapping({"/companyassets/delete/{companyAssets_id}"})
	  public ResponseEntity<String> deleteTitle(@PathVariable("companyAssets_id") Long companyAssets_id) {
	    this.service.deleteCompanyAssetsIdById(companyAssets_id);
	    return ResponseEntity.ok("CompanyAssets deleted successfully");
	  }
	  
	  @GetMapping({"/companyassets/view"})
	  public ResponseEntity<List<Map<String, Object>>> displayAllImages2(@RequestParam(required = true) String trueParam) {
	    try {
	      if ("companyassets".equals(trueParam)) {
	        List<Map<String, Object>> employeeDetails = this.repo.AllEmployees();
	        List<Map<String, Object>> employeeResponses = new ArrayList<>();
	        for (Map<String, Object> employeeDetail : employeeDetails) {
	          int randomNumber = generateRandomNumber();
	          String imageUrl = "companyassets/" + randomNumber + "/" + employeeDetail.get("company_assets_id");
	          Map<String, Object> employeeResponse = new HashMap<>();
	          employeeResponse.put("companyAssetsId", employeeDetail.get("company_assets_id"));
	          employeeResponse.put("accessoriesId", employeeDetail.get("accessories_id"));
	          employeeResponse.put("assetValues", employeeDetail.get("asset_values"));
	          employeeResponse.put("brantId", employeeDetail.get("brant_id"));
	          employeeResponse.put("count", employeeDetail.get("count"));
	          employeeResponse.put("status", employeeDetail.get("status"));
	          employeeResponse.put("brandName", employeeDetail.get("brand_name"));
	          employeeResponse.put("accessoriesName", employeeDetail.get("accessories_name"));
	          employeeResponse.put("date", employeeDetail.get("date"));
	          employeeResponse.put("url", imageUrl);
	          employeeResponses.add(employeeResponse);
	        } 
	        return ResponseEntity.ok(employeeResponses);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	}


