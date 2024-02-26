package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.EmergencyContacts;
import com.example.HRM.repository.employee.EmergencyContactsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyContactsService {

	@Autowired
	  private EmergencyContactsRepository repo;
	  
	  public List<EmergencyContacts> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(EmergencyContacts emergencyContacts) {
	    this.repo.save(emergencyContacts);
	  }
	  
	  public EmergencyContacts getByEmployeeId(long id) {
	    return this.repo.findByEmployeeId(id).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	  
	  public Optional<EmergencyContacts> getDesignationById(Long bankId) {
	    return this.repo.findById(bankId);
	  }
	}

