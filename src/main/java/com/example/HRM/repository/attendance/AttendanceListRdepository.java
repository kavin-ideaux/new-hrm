package com.example.HRM.repository.attendance;
import com.example.HRM.entity.attendance.AttendanceList;
import java.sql.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceListRdepository extends JpaRepository<AttendanceList, Long> {
	  Optional<AttendanceList> findByEmployeeIdAndDate(Long paramLong, Date paramDate);
	}
