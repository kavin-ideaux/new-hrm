package com.example.HRM.service.attendance;
import com.example.HRM.entity.attendance.ShiftType;
import com.example.HRM.repository.attendance.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftTypeService {
	@Autowired
	  private ShiftTypeRepository shiftTypeRepository;
	  
	  public void addShiftTypeService() {
	    ShiftType regular = new ShiftType();
	    regular.setShiftTypeId(1L);
	    regular.setShiftName("Regular");
	    this.shiftTypeRepository.save(regular);
	    ShiftType shift = new ShiftType();
	    shift.setShiftTypeId(2L);
	    shift.setShiftName("Shift");
	    this.shiftTypeRepository.save(shift);
	  }
	}

