package com.example.HRM.service.attendance;
import com.example.HRM.entity.attendance.Shift;
import com.example.HRM.repository.attendance.ShiftRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {
	@Autowired
	  private ShiftRepository shiftRepository;
	  
	  public List<Shift> getAllShift() {
	    return this.shiftRepository.findAll();
	  }
	  
	  public void saveShift(Shift shift) {
	    this.shiftRepository.save(shift);
	  }
	  
	  public Shift getShiftById(long id) {
	    return this.shiftRepository.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteShiftById(Long id) {
	    this.shiftRepository.deleteById(id);
	  }
	}


