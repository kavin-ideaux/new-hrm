package com.example.HRM.service.clientDetails;

import com.example.HRM.entity.clientDetails.ClientProfile;
import com.example.HRM.repository.clientDetails.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	@Autowired
	  private ClientRepository clientRepository;
	  
	  public List<ClientProfile> listAll() {
	    return this.clientRepository.findAll();
	  }
	  
	  public void SaveClientProfile(ClientProfile client) {
	    this.clientRepository.save(client);
	  }
	  
	  public ClientProfile findById(Long id) {
	    return this.clientRepository.findById(id).get();
	  }
	  
	  public void deleteClientId(Long id) {
	    this.clientRepository.deleteById(id);
	  }
	  
//	  @Bean
//	  public PasswordEncoder passwordEncoder() {
//	    return (PasswordEncoder)new BCryptPasswordEncoder();
//	  }
	}
