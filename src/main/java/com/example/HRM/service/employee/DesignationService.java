package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Designation;
import com.example.HRM.repository.employee.DesignationRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesignationService {
	@Autowired
	  private DesignationRepository designationrepository;
	  
	  public Iterable<Designation> listAll() {
	    return this.designationrepository.findAll();
	  }
	  
	  public void SaveorUpdate(Designation designation) {
	    this.designationrepository.save(designation);
	  }
	  
	  public void save(Designation designation) {
	    this.designationrepository.save(designation);
	  }
	  
	  public Designation findById(Long designationId) {
	    return this.designationrepository.findById(designationId).get();
	  }
	  
	  public void deleteDesignationtById(Long designationId) {
	    this.designationrepository.deleteById(designationId);
	  }
	  
	  public Optional<Designation> getDesignationById(Long designationId) {
	    return this.designationrepository.findById(designationId);
	  }
	}

