package com.example.HRM.controller.accounting;
import com.example.HRM.entity.accounting.Server;
import com.example.HRM.repository.accounting.ServerRepository;
import com.example.HRM.service.accounting.ServerService;
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
public class ServerController {

	@Autowired
	  private ServerService service;
	  
	  @Autowired
	  private ServerRepository repo;
	  
	  @GetMapping({"/server"})
	  public ResponseEntity<?> getDetails(@RequestParam(required = true) String serverParam) {
	    try {
	      if ("server".equals(serverParam)) {
	        Iterable<Server> designationDetails = this.service.listAll();
	        return new ResponseEntity(designationDetails, HttpStatus.OK);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided serverParam is not supported.");
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while retrieving designation details.";
	      return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	  }
	  
	  @PostMapping({"/server/save"})
	  public ResponseEntity<?> saveBank(@RequestBody Server server) {
	    try {
	      server.setStatus(true);
	      this.service.SaveorUpdate(server);
	      return ResponseEntity.status(HttpStatus.CREATED).body("assest details saved successfully.");
	    } catch (Exception e) {
	      String errorMessage = "An error occurred while saving assest details.";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } 
	  }
	  
	  @RequestMapping({"/server/{serverId}"})
	  private Server getAssest(@PathVariable(name = "serverId") long serverId) {
	    return this.service.findById(serverId);
	  }
	  
	  @PutMapping({"/server/edit/{servertId}"})
	  public ResponseEntity<Server> updateAssest(@PathVariable long serverId, @RequestBody Server assestDetails) {
	    try {
	      Server existingAssest = this.service.findById(serverId);
	      if (existingAssest == null)
	        return ResponseEntity.notFound().build(); 
	      existingAssest.setServerName(assestDetails.getServerName());
	      existingAssest.setDate(assestDetails.getDate());
	      existingAssest.setServer(assestDetails.getServer());
	      this.service.save(existingAssest);
	      return ResponseEntity.ok(existingAssest);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/server/delete/{serverId}"})
	  public ResponseEntity<String> deleteprojectName(@PathVariable("serverId") Long serverId) {
	    this.service.deleteAssestIdById(serverId.longValue());
	    return ResponseEntity.ok("server deleted successfully");
	  }
	}

