package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Personal;
import com.example.HRM.repository.employee.PersonalRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalService {
	@Autowired
	  private PersonalRepository repo;
	  
	  public Iterable<Personal> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(Personal personal) {
	    this.repo.save(personal);
	  }
	  
	  public Personal findById(Long Personal) {
	    return this.repo.findById(Personal).get();
	  }
	  
	  public Personal getByEmployeeId(long id) {
	    return this.repo.findByEmployeeId(id).get();
	  }
	  
	  public void deleteDepartmentRollById(Long Personal) {
	    this.repo.deleteById(Personal);
	  }
	  
	  public Optional<Personal> getdepartmentRollById(Long Personal) {
	    return this.repo.findById(Personal);
	  }
	  
	  public void deleteById(Long PersonalId) {
	    this.repo.deleteById(PersonalId);
	  }
	  
	  public Optional<Personal> getDesignationById(Long personalId) {
	    return this.repo.findById(personalId);
	  }
	}


