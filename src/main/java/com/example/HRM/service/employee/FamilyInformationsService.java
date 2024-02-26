package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.FamilyInformations;
import com.example.HRM.repository.employee.FamilyInformationsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyInformationsService {

	@Autowired
	  private FamilyInformationsRepository repo;
	  
	  public Iterable<FamilyInformations> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(FamilyInformations familyInformations) {
	    this.repo.save(familyInformations);
	  }
	  
	  public FamilyInformations getByEmployeeId(long id) {
	    return this.repo.findByEmployeeId(id).get();
	  }
	  
	  public FamilyInformations getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteDepartmentRollById(Long FamilyInformations) {
	    this.repo.deleteById(FamilyInformations);
	  }
	  
	  public Optional<FamilyInformations> getdepartmentRollById(Long FamilyInformations) {
	    return this.repo.findById(FamilyInformations);
	  }
	  
	  public void deleteById(Long FamilyInformationsId) {
	    this.repo.deleteById(FamilyInformationsId);
	  }
	}

