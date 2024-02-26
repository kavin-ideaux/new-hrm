package com.example.HRM.service.clientDetails;

import com.example.HRM.entity.clientDetails.ClientRequirement;
import com.example.HRM.repository.clientDetails.ClientRequirementRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientRequirementService {
	@Autowired
	  private ClientRequirementRepository clientRequirmentRepository;
	  
	  public List<ClientRequirement> listAll() {
	    return this.clientRequirmentRepository.findAll();
	  }
	  
	  public ClientRequirement getByClientId(long id) {
	    return this.clientRequirmentRepository.findByClientId(Long.valueOf(id)).get();
	  }
	  
	  public ClientRequirement SaveClientRequirmentDetails(ClientRequirement clientRequirment) {
	    return (ClientRequirement)this.clientRequirmentRepository.save(clientRequirment);
	  }
	  
	  public ClientRequirement getById(long id) {
	    return this.clientRequirmentRepository.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteClientRequirmentId(Long id) {
	    this.clientRequirmentRepository.deleteById(id);
	  }
	}

