package com.example.HRM.service.attendance;
import com.example.HRM.entity.attendance.AttendanceList;
import com.example.HRM.repository.attendance.AttendanceListRdepository;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceListService {
	@Autowired
	  private AttendanceListRdepository repo;
	  
	  public void save(AttendanceList attendancelist) {
	    this.repo.save(attendancelist);
	  }
	  
	  public AttendanceList findById(Long attendanceListId) {
	    return this.repo.findById(attendanceListId).get();
	  }
	  
	  public AttendanceList findByEmployeeIdAndDate(Long attendanceListId, Date date) {
	    return this.repo.findByEmployeeIdAndDate(attendanceListId, date).get();
	  }
	}

