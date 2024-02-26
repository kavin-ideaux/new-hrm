package com.example.HRM.controller.project;

import com.example.HRM.entity.project.ApiDocumentation;
import com.example.HRM.entity.project.ProjectDocumentation;
import com.example.HRM.repository.project.ApiDocumentationRepository;
import com.example.HRM.service.project.ApiDocumentationService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ApiDocumentationController {
	@Autowired
	  private ApiDocumentationService apiDocumentationService;
	  
	  @Autowired
	  private ApiDocumentationRepository apiDocumentationRepository;
	  
	  @PostMapping({"/api/documentation/save"})
	  public ResponseEntity<?> saveApiDocumentation(@RequestParam("date") Date date, @RequestParam("projectId") long projectId, @RequestParam("employeeId") long employeeId, @RequestParam("apiDocumentationUpload") MultipartFile apiDocumentationUpload) throws SQLException {
	    try {
	      ApiDocumentation apiDocumentation = new ApiDocumentation();
	      apiDocumentation.setApiDocumentationUpload(convertToBlob(apiDocumentationUpload));
	      apiDocumentation.setDate(date);
	      apiDocumentation.setEmployeeId(employeeId);
	      apiDocumentation.setProjectId(projectId);
	      this.apiDocumentationService.saveApiDocumentation(apiDocumentation);
	      return ResponseEntity.ok("ApiDocumentation saved successfully.");
	    } catch (IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while saving the ApiDocumentation: " + e.getMessage());
	    } 
	  }
	  
	  private Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
	    if (file != null && !file.isEmpty()) {
	      byte[] bytes = file.getBytes();
	      return new SerialBlob(bytes);
	    } 
	    return null;
	  }
	  
	  @GetMapping({"/api/documentation/view"})
	  public ResponseEntity<?> getDocumentation() {
	    try {
	      List<Map<String, Object>> documentationView = this.apiDocumentationRepository.getAllApiDocumentation();
	      List<ProjectDocumentation> documentationList = new ArrayList<>();
	      for (Map<String, Object> documentation : documentationView) {
	        String apiDocumentationUrl = "/apiDocumentationUrl/" + documentation.get("api_documentation_id");
	        ProjectDocumentation documentationResponse = new ProjectDocumentation();
	        documentationResponse.setDate((Date)documentation.get("date"));
	        documentationResponse.setEmployeeId(((Long)documentation.get("employee_id")).longValue());
	        documentationResponse.setProjectDocumentationId(((Long)documentation.get("api_documentation_id")).longValue());
	        documentationResponse.setProjectId(((Long)documentation.get("project_id")).longValue());
	        documentationResponse.setDocumentationUrl(apiDocumentationUrl);
	        documentationList.add(documentationResponse);
	      } 
	      return ResponseEntity.ok(documentationList);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving offerLetter");
	    } 
	  }
	  
	  @GetMapping({"/apiDocumentationUrl/{apiDocumentationId}"})
	  public ResponseEntity<byte[]> downloadApiDocumentation(@PathVariable long apiDocumentationId) {
	    try {
	      ApiDocumentation documentation = this.apiDocumentationService.findApiDocumentationById(Long.valueOf(apiDocumentationId));
	      if (documentation != null) {
	        Blob pdfBlob = documentation.getApiDocumentationUpload();
	        if (pdfBlob != null) {
	          byte[] pdfBytes = pdfBlob.getBytes(1L, (int)pdfBlob.length());
	          HttpHeaders headers = new HttpHeaders();
	          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	          String filename = "ProjectDocumentation.pdf";
	          headers.setContentDispositionFormData("attachment", filename);
	          return new ResponseEntity(pdfBytes, (MultiValueMap)headers, HttpStatus.OK);
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/api/documentation/{apiDocumentationId}"})
	  public ResponseEntity<String> updateApiDocumentation(@PathVariable long apiDocumentationId, @RequestParam("date") Date date, @RequestParam("projectId") long projectId, @RequestParam("employeeId") long employeeId, @RequestParam("apiDocumentationUpload") MultipartFile apiDocumentationUpload) {
	    try {
	      ApiDocumentation documentation = this.apiDocumentationService.findApiDocumentationById(Long.valueOf(apiDocumentationId));
	      if (documentation != null) {
	        if (apiDocumentationUpload != null && !apiDocumentationUpload.isEmpty()) {
	          Blob documentationBlob = convertToBlob(apiDocumentationUpload);
	          documentation.setApiDocumentationUpload(documentationBlob);
	        } 
	        documentation.setDate(date);
	        documentation.setEmployeeId(employeeId);
	        documentation.setProjectId(projectId);
	        this.apiDocumentationService.saveApiDocumentation(documentation);
	        return ResponseEntity.ok("Api Documentation updated successfully.");
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException|IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/api/documentation/delete/{id}"})
	  public ResponseEntity<String> deleteApiDocumentation(@PathVariable("id") Long id) {
	    this.apiDocumentationService.deleteApiDocumentation(id);
	    return ResponseEntity.ok("Api Documentation details deleted successfully");
	  }
	}
