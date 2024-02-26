package com.example.HRM.controller.project;
import com.example.HRM.entity.project.ProjectDocumentation;
import com.example.HRM.repository.project.ProjectDocumentationRepository;
import com.example.HRM.service.project.ProjectDocumentationService;
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
public class ProjectDocumentationController {

	@Autowired
	  private ProjectDocumentationService projectDocumentationService;
	  
	  @Autowired
	  private ProjectDocumentationRepository projectDocumentationRepository;
	  
	  @PostMapping({"/project/documentation/save"})
	  public ResponseEntity<?> saveProjectDocumentation(@RequestParam("date") Date date, @RequestParam("projectId") long projectId, @RequestParam("employeeId") long employeeId, @RequestParam("documentationUpload") MultipartFile documentationUpload) throws SQLException {
	    try {
	      ProjectDocumentation projectDocumentation = new ProjectDocumentation();
	      projectDocumentation.setDocumentationUpload(convertToBlob(documentationUpload));
	      projectDocumentation.setDate(date);
	      projectDocumentation.setEmployeeId(employeeId);
	      projectDocumentation.setProjectId(projectId);
	      this.projectDocumentationService.saveProjectDocumentation(projectDocumentation);
	      return ResponseEntity.ok("ProjectDocumentation saved successfully.");
	    } catch (IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while saving the ProjectDocumentation: " + e.getMessage());
	    } 
	  }
	  
	  private Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
	    if (file != null && !file.isEmpty()) {
	      byte[] bytes = file.getBytes();
	      return new SerialBlob(bytes);
	    } 
	    return null;
	  }
	  
	  @GetMapping({"/project/documentation/view"})
	  public ResponseEntity<?> getDocumentation() {
	    try {
	      List<Map<String, Object>> documentationView = this.projectDocumentationRepository.getAllProjectDocumentation();
	      List<ProjectDocumentation> documentationList = new ArrayList<>();
	      for (Map<String, Object> documentation : documentationView) {
	        String documentationUrl = "/documentationUrl/" + documentation.get("project_documentation_id");
	        ProjectDocumentation documentationResponse = new ProjectDocumentation();
	        documentationResponse.setDate((Date)documentation.get("date"));
	        documentationResponse.setEmployeeId(((Long)documentation.get("employee_id")).longValue());
	        documentationResponse.setProjectDocumentationId(((Long)documentation.get("project_documentation_id")).longValue());
	        documentationResponse.setProjectId(((Long)documentation.get("project_id")).longValue());
	        documentationResponse.setDocumentationUrl(documentationUrl);
	        documentationList.add(documentationResponse);
	      } 
	      return ResponseEntity.ok(documentationList);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving offerLetter");
	    } 
	  }
	  
	  @GetMapping({"/documentationUrl/{projectDocumentationId}"})
	  public ResponseEntity<byte[]> downloadProjectDocumentation(@PathVariable long projectDocumentationId) {
	    try {
	      ProjectDocumentation documentation = this.projectDocumentationService.findProjectDocumentationUploadedId(Long.valueOf(projectDocumentationId));
	      if (documentation != null) {
	        Blob pdfBlob = documentation.getDocumentationUpload();
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
	  
	  @PutMapping({"/project/documentation/{projectDocumentationId}"})
	  public ResponseEntity<String> updateProjectDocumentation(@PathVariable long projectDocumentationId, @RequestParam("date") Date date, @RequestParam("projectId") long projectId, @RequestParam("employeeId") long employeeId, @RequestParam("documentationUpload") MultipartFile documentationUpload) {
	    try {
	      ProjectDocumentation documentation = this.projectDocumentationService.findProjectDocumentationUploadedId(Long.valueOf(projectDocumentationId));
	      if (documentation != null) {
	        if (documentationUpload != null && !documentationUpload.isEmpty()) {
	          Blob documentationBlob = convertToBlob(documentationUpload);
	          documentation.setDocumentationUpload(documentationBlob);
	        } 
	        documentation.setDate(date);
	        documentation.setEmployeeId(employeeId);
	        documentation.setProjectId(projectId);
	        this.projectDocumentationService.saveProjectDocumentation(documentation);
	        return ResponseEntity.ok("Project Documentation updated successfully.");
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException|IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/project/documentation/delete/{id}"})
	  public ResponseEntity<String> deleteProjectDocumentation(@PathVariable("id") Long id) {
	    this.projectDocumentationService.deleteProjectDocumentation(id);
	    return ResponseEntity.ok("Project Documentation details deleted successfully");
	  }
	}

