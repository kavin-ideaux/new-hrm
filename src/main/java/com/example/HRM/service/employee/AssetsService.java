package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.Assets;
import com.example.HRM.repository.employee.AssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetsService {
	 @Autowired
	  private AssetsRepository repo;
	  
	  public Iterable<Assets> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public Assets getUserById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void update(Assets attendance, long AttendanceId) {
	    this.repo.save(attendance);
	  }
	  
	  public void deleteMemberById(Long id) {
	    this.repo.deleteById(id);
	  }
	  
	  public void save(Assets existingAttendance) {
	    this.repo.save(existingAttendance);
	  }
	  
	  public Assets findById(Long id) {
	    return this.repo.findById(id).get();
	  }
	}

