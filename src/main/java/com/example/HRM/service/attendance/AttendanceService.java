package com.example.HRM.service.attendance;
import com.example.HRM.entity.attendance.Attendance;
import com.example.HRM.entity.attendance.AttendanceList;
import com.example.HRM.repository.attendance.AttendanceListRdepository;
import com.example.HRM.repository.attendance.AttendanceRepository;
import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

	@Autowired
	  private AttendanceRepository repo;
	  
	  @Autowired
	  private AttendanceListRdepository attendanceListRdepository;
	  
	  public Iterable<Attendance> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public Attendance getUserById(long id) {
	    return this.repo.findById(Long.valueOf(id)).get();
	  }
	  
	  public void update(Attendance attendance, long AttendanceId) {
	    this.repo.save(attendance);
	  }
	  
	  public void deleteMemberById(Long id) {
	    this.repo.deleteById(id);
	  }
	  
	  public void save(Attendance existingAttendance) {
	    this.repo.save(existingAttendance);
	  }
	  
	  public Optional<AttendanceList> findByEmployeeIdAndDate(Long employeeId, Date date) {
	    return this.attendanceListRdepository.findByEmployeeIdAndDate(employeeId, date);
	  }
	  
	  public Attendance findById(Long id) {
	    return this.repo.findById(id).get();
	  }
	}

