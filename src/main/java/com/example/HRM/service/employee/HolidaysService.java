package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Holidays;
import com.example.HRM.repository.employee.HolidaysRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidaysService {

	@Autowired
	  private HolidaysRepository repo;
	  
	  public List<Holidays> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void saveOrUpdate(Holidays LeaveType) {
	    this.repo.save(LeaveType);
	  }
	  
	  public Holidays getById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.repo.deleteById(Long.valueOf(id));
	  }
	}
