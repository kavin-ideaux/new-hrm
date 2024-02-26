package com.example.HRM.controller.clientDetails;
import com.example.HRM.PasswordMismatchException;
import com.example.HRM.entity.clientDetails.ClientProfile;
import com.example.HRM.entity.clientDetails.ClientRequirement;
import com.example.HRM.repository.clientDetails.ClientRepository;
import com.example.HRM.service.clientDetails.ClientRequirementService;
import com.example.HRM.service.clientDetails.ClientService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
public class ClientController {
	@Autowired
	  private ClientService clientService;
	  
	  @Autowired
	  private ClientRequirementService clientRequirementService;
	  
	  @Autowired
	  private ClientRepository clientRepository;
	  
	  @GetMapping({"/clientProfile"})
	  public ResponseEntity<Object> getClientProfile(@RequestParam(required = true) String client) {
	    if ("clientDetails".equals(client))
	      return ResponseEntity.ok(this.clientService.listAll()); 
	    String errorMessage = "Invalid value for 'client'. Expected 'clientDetails'.";
	    return ResponseEntity.badRequest().body(errorMessage);
	  }
	  
	  @PostMapping({"/clientProfile/save"})
	  private ResponseEntity<ClientProfile> saveSingleUser(@RequestBody ClientProfile clientProfile) {
	    String takenPassword = clientProfile.getPassword();
	    String confirmPassword = clientProfile.getConfirmPassword();
	    if (!takenPassword.equals(confirmPassword))
	      throw new PasswordMismatchException("Password and confirm password do not match"); 
	    clientProfile.setRoleId(3L);
	    clientProfile.setStatus(true);
	    this.clientService.SaveClientProfile(clientProfile);
	    long clientId = clientProfile.getClientId();
	    ClientRequirement requirement = new ClientRequirement();
	    requirement.setClientId(clientId);
	    this.clientRequirementService.SaveClientRequirmentDetails(requirement);
	    return ResponseEntity.ok(clientProfile);
	  }
	  
	  @PutMapping({"/clientProfile/{id}"})
	  public ResponseEntity<ClientProfile> updateClientProfile(@PathVariable("id") long id, @RequestParam("clientName") String clientName, @RequestParam("gender") String gender, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("address") String address, @RequestParam("email") String email, @RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("country") String country, @RequestParam("zipCode") int zipCode, @RequestParam("mobileNumber") String mobileNumber, @RequestParam("referral") String referral) {
	    try {
	      ClientProfile existingClient = this.clientService.findById(Long.valueOf(id));
	      if (existingClient == null)
	        return ResponseEntity.notFound().build(); 
	      existingClient.setClientName(clientName);
	      existingClient.setGender(gender);
	      existingClient.setPhoneNumber(phoneNumber);
	      existingClient.setAddress(address);
	      existingClient.setEmail(email);
	      existingClient.setCity(city);
	      existingClient.setState(state);
	      existingClient.setZipCode(zipCode);
	      existingClient.setMobileNumber(mobileNumber);
	      existingClient.setReferral(referral);
	      this.clientService.SaveClientProfile(existingClient);
	      return ResponseEntity.ok(existingClient);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/client/edit/{id}"})
	  public ResponseEntity<ClientProfile> updateOrder(@PathVariable("id") Long client_id, @RequestBody ClientProfile client) {
	    try {
	      ClientProfile existingClient = this.clientService.findById(client_id);
	      if (existingClient == null)
	        return ResponseEntity.notFound().build(); 
	      existingClient.setAddress(client.getAddress());
	      existingClient.setCity(client.getCity());
	      existingClient.setClientName(client.getClientName());
	      existingClient.setCountry(client.getCountry());
	      existingClient.setEmail(client.getEmail());
	      existingClient.setGender(client.getGender());
	      existingClient.setPhoneNumber(client.getPhoneNumber());
	      existingClient.setMobileNumber(client.getMobileNumber());
	      existingClient.setState(client.getState());
	      existingClient.setZipCode(client.getZipCode());
	      existingClient.setMobileNumber(client.getMobileNumber());
	      existingClient.setReferral(client.getReferral());
	      existingClient.setZipCode(client.getZipCode());
	      this.clientService.SaveClientProfile(existingClient);
	      return ResponseEntity.ok(existingClient);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/clientProfile/delete/{id}"})
	  public ResponseEntity<String> deleteclientProfile(@PathVariable("id") Long id) {
	    this.clientService.deleteClientId(id);
	    return ResponseEntity.ok("ClientProfile details deleted successfully");
	  }
	  
	  @GetMapping({"/client/true/false"})
	  public ResponseEntity<List<Map<String, Object>>> getAllClientDetails(@RequestParam(required = true) String client) {
	    List<Map<String, Object>> clientDetails;
	    if ("True".equals(client)) {
	      clientDetails = this.clientRepository.getAllClientDetailsTrue();
	    } else if ("False".equals(client)) {
	      clientDetails = this.clientRepository.getAllClientDetailsFalse();
	    } else {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } 
	    List<Map<String, Object>> clientMainMap = new ArrayList<>();
	    Map<String, List<Map<String, Object>>> clientGroupMap = (Map<String, List<Map<String, Object>>>)clientDetails.stream().collect(Collectors.groupingBy(action -> action.get("client_id").toString()));
	    for (Map.Entry<String, List<Map<String, Object>>> entry : clientGroupMap.entrySet()) {
	      Map<String, Object> clientMap = new HashMap<>();
	      List<Map<String, Object>> clientList = entry.getValue();
	      Map<String, Object> firstClient = clientList.get(0);
	      clientMap.put("clientId", entry.getKey());
	      clientMap.put("clientName", firstClient.get("client_name"));
	      clientMap.put("gender", firstClient.get("gender"));
	      clientMap.put("phoneNumber", firstClient.get("phone_number"));
	      clientMap.put("mobileNumber", firstClient.get("mobile_number"));
	      clientMap.put("address", firstClient.get("address"));
	      clientMap.put("email", firstClient.get("email"));
	      clientMap.put("city", firstClient.get("city"));
	      clientMap.put("state", firstClient.get("state"));
	      clientMap.put("country", firstClient.get("country"));
	      clientMap.put("referral", firstClient.get("referral"));
	      clientMap.put("roleId", firstClient.get("role_id"));
	      clientMap.put("roleName", firstClient.get("role_name"));
	      clientMap.put("zipCode", firstClient.get("zip_code"));
	      clientMainMap.add(clientMap);
	    } 
	    return ResponseEntity.ok(clientMainMap);
	  }
	}

