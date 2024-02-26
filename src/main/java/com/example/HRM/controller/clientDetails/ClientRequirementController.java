package com.example.HRM.controller.clientDetails;

import com.example.HRM.entity.clientDetails.ClientRequirement;
import com.example.HRM.repository.clientDetails.ClientRequirementRepository;
import com.example.HRM.service.clientDetails.ClientRequirementService;
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
public class ClientRequirementController {

	 @Autowired
	  private ClientRequirementService clientRequirementService;
	  
	  @Autowired
	  private ClientRequirementRepository clientRequirementRepository;
	  
	  @PostMapping({"/clientRequirement/save"})
	  public ResponseEntity<?> saveOffer(@RequestParam("projectName") String projectName, @RequestParam("duration") String duration, @RequestParam("date") Date date, @RequestParam("serviceId") long clientId, @RequestParam("clientId") long serviceId, @RequestParam("projectTypeId") long projectTypeId, @RequestParam("fileUpload") MultipartFile fileUpload) throws SQLException {
	    try {
	      ClientRequirement clientRequirement = new ClientRequirement();
	      clientRequirement.setDate(date);
	      clientRequirement.setDuration(duration);
	      clientRequirement.setClientId(clientId);
	      clientRequirement.setProjectName(projectName);
	      clientRequirement.setServiceId(serviceId);
	      clientRequirement.setProjectTypeId(projectTypeId);
	      clientRequirement.setFileUpload(convertToBlob(fileUpload));
	      this.clientRequirementService.SaveClientRequirmentDetails(clientRequirement);
	      return ResponseEntity.ok("ClientRequirement details saved successfully.");
	    } catch (IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while saving the clientRequirement: " + e.getMessage());
	    } 
	  }
	  
	  private Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
	    if (file != null && !file.isEmpty()) {
	      byte[] bytes = file.getBytes();
	      return new SerialBlob(bytes);
	    } 
	    return null;
	  }
	  
	  @GetMapping({"/clientRequirement"})
	  public ResponseEntity<?> getUser() {
	    try {
	      List<ClientRequirement> clientRequirement = this.clientRequirementService.listAll();
	      List<ClientRequirement> requirementList = new ArrayList<>();
	      for (ClientRequirement clientRequirements : clientRequirement) {
	        String fileUploadUrl = "/fileUpload/" + clientRequirements.getProjectId();
	        ClientRequirement requirementResponse = new ClientRequirement();
	        requirementResponse.setProjectId(clientRequirements.getProjectId());
	        requirementResponse.setClientId(clientRequirements.getClientId());
	        requirementResponse.setDate(clientRequirements.getDate());
	        requirementResponse.setDuration(clientRequirements.getDuration());
	        requirementResponse.setProjectName(clientRequirements.getProjectName());
	        requirementResponse.setProjectTypeId(clientRequirements.getProjectTypeId());
	        requirementResponse.setServiceId(clientRequirements.getServiceId());
	        requirementResponse.setProjectStatus(clientRequirements.getProjectStatus());
	        requirementResponse.setStatus(clientRequirements.isStatus());
	        requirementResponse.setFileUploadUrl(fileUploadUrl);
	        requirementList.add(requirementResponse);
	      } 
	      return ResponseEntity.ok(requirementList);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving clientRequirement");
	    } 
	  }
	  
	  @GetMapping({"/fileUpload/{clientId}"})
	  public ResponseEntity<byte[]> downloadOfferFile(@PathVariable long clientId) {
	    try {
	      ClientRequirement clientRequirement = this.clientRequirementService.getById(clientId);
	      if (clientRequirement != null) {
	        Blob pdfBlob = clientRequirement.getFileUpload();
	        if (pdfBlob != null) {
	          byte[] pdfBytes = pdfBlob.getBytes(1L, (int)pdfBlob.length());
	          HttpHeaders headers = new HttpHeaders();
	          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	          String filename = "clientRequirementFile.pdf";
	          headers.setContentDispositionFormData("attachment", filename);
	          return new ResponseEntity(pdfBytes, (MultiValueMap)headers, HttpStatus.OK);
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/clientRequirements/{projectId}"})
	  public ResponseEntity<String> updateOfferFile(@PathVariable("projectId") long projectId, @RequestParam("duration") String duration, @RequestParam("projectName") String projectName, @RequestParam("serviceId") long serviceId, @RequestParam("projectTypeId") long projectTypeId, @RequestParam("date") Date date, @RequestParam("fileUpload") MultipartFile fileUpload) {
	    try {
	      ClientRequirement clientRequirement = this.clientRequirementService.getById(projectId);
	      if (clientRequirement != null) {
	        Blob pdfBlob = clientRequirement.getFileUpload();
	        if (pdfBlob != null) {
	          byte[] newPdfBytes = fileUpload.getBytes();
	          pdfBlob.setBytes(1L, newPdfBytes);
	          clientRequirement.setDate(date);
	          clientRequirement.setDuration(duration);
	          clientRequirement.setServiceId(serviceId);
	          clientRequirement.setProjectTypeId(projectTypeId);
	          clientRequirement.setProjectName(projectName);
	          this.clientRequirementService.SaveClientRequirmentDetails(clientRequirement);
	          return ResponseEntity.ok("ClientRequirement updated successfully.");
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException|IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/clientRequirements/approval/rejection/{projectId}"})
	  public ResponseEntity<?> approvalOrRejection(@PathVariable("projectId") long projectId, @RequestParam("projectStatus") String projectStatus) {
	    ClientRequirement clientRequirement = this.clientRequirementService.getById(projectId);
	    if (clientRequirement != null) {
	      clientRequirement.setProjectStatus(projectStatus);
	      if ("approved".equals(clientRequirement.getProjectStatus())) {
	        clientRequirement.setStatus(true);
	      } else if ("rejected".equals(clientRequirement.getProjectStatus())) {
	        clientRequirement.setStatus(false);
	      } else {
	        clientRequirement.setStatus(false);
	      } 
	      this.clientRequirementService.SaveClientRequirmentDetails(clientRequirement);
	      return ResponseEntity.ok("ClientRequirement status updated successfully.");
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  @DeleteMapping({"/clientRequirement/delete/{id}"})
	  public ResponseEntity<String> deleteClientRequirementDetails(@PathVariable("id") Long id) {
	    this.clientRequirementService.deleteClientRequirmentId(id);
	    return ResponseEntity.ok("ClientRequirement details deleted successfully");
	  }
	  
	  @GetMapping({"/findClientRequirementDetails"})
	  public ResponseEntity<?> getClientRequirementDetails() {
	    try {
	      List<Map<String, Object>> clientRequirements = this.clientRequirementRepository.findClientRequirementDetails();
	      List<Map<String, Object>> clientRequirementList = new ArrayList<>();
	      for (Map<String, Object> clientRequirement : clientRequirements) {
	        Map<String, Object> clientRequirementMap = new HashMap<>();
	        Object projectId = clientRequirement.get("project_id");
	        if (projectId != null) {
	          String fileUploadUrl = "/fileUpload/" + projectId;
	          clientRequirementMap.put("fileUploadUrl", fileUploadUrl);
	        } 
	        clientRequirementMap.putAll(clientRequirement);
	        clientRequirementList.add(clientRequirementMap);
	      } 
	      return ResponseEntity.ok(clientRequirementList);
	    } catch (Exception e) {
	      String errorMessage = "Error occurred while retrieving clientRequirement details";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Collections.singletonMap("error", errorMessage));
	    } 
	  }
	  
	  @GetMapping({"/clientRequirement/view"})
	  public ResponseEntity<List<Map<String, Object>>> displayAllImages2(@RequestParam(required = true) String trueParam) {
	    try {
	      if ("clientRequirements".equals(trueParam)) {
	        List<Map<String, Object>> employeeDetails = this.clientRequirementRepository.findClientRequirementDetails1();
	        List<Map<String, Object>> employeeResponses = new ArrayList<>();
	        for (Map<String, Object> employeeDetail : employeeDetails) {
	          String imageUrl = "fileUpload/" + employeeDetail.get("client_id");
	          Map<String, Object> employeeResponse = new HashMap<>();
	          employeeResponse.put("projectName", employeeDetail.get("project_name"));
	          employeeResponse.put("projectId", employeeDetail.get("project_id"));
	          employeeResponse.put("clientId", employeeDetail.get("client_id"));
	          employeeResponse.put("duration", employeeDetail.get("duration"));
	          employeeResponse.put("projectTypeId", employeeDetail.get("project_type_id"));
	          employeeResponse.put("date", employeeDetail.get("date"));
	          employeeResponse.put("serviceId", employeeDetail.get("server_id"));
	          employeeResponse.put("clientName", employeeDetail.get("client_name"));
	          employeeResponse.put("projectType", employeeDetail.get("project_type"));
	          employeeResponse.put("serverName", employeeDetail.get("server_name"));
	          employeeResponse.put("serverType", employeeDetail.get("server_type"));
	          employeeResponse.put("projectStatus", employeeDetail.get("project_status"));
	          employeeResponse.put("status", employeeDetail.get("status"));
	          employeeResponse.put("fileUpload", imageUrl);
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

