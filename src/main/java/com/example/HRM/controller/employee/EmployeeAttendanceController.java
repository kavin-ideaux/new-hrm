package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.EmployeeAttendance;
import com.example.HRM.repository.employee.EmployeeAttendanceRepository;
import com.example.HRM.service.employee.EmployeeAttendanceService;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EmployeeAttendanceController {
	@Autowired
	  private EmployeeAttendanceService service;
	  
	  @Autowired
	  private EmployeeAttendanceRepository attendanceRepository;
	  
	  @GetMapping({"/attendance"})
	  public ResponseEntity<?> getUser1(@RequestParam(required = true) String attendance) {
	    try {
	      if ("employeeattendance".equals(attendance)) {
	        Iterable<EmployeeAttendance> employeeAttendanceList = this.service.listAll1();
	        return ResponseEntity.ok(employeeAttendanceList);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	        .body("The provided attendance parameter is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving employee attendance");
	    } 
	  }
	  
	  @PostMapping({"/attendance/save"})
	  public ResponseEntity<String> saveMember(@RequestBody EmployeeAttendance attendance) {
	    try {
	      attendance.setInDate(LocalDate.now());
	      LocalTime localTime = LocalTime.now();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
	      String formattedTime = localTime.format(formatter);
	      attendance.setInTime(formattedTime);
	      Optional<EmployeeAttendance> existingAttendance = this.attendanceRepository.findByEmployeeIdAndInDate(attendance.getEmployeeId(), attendance.getInDate());
	      if (existingAttendance.isPresent())
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Attendance entry for employee ID " + attendance
	            .getEmployeeId() + " on date " + attendance.getInDate() + " already exists."); 
	      if (attendance.getAttendance().equals("PunchIn")) {
	        attendance.setPunchIn(true);
	        attendance.setAttendance("PunchIn");
	      } else {
	        attendance.setPunchIn(false);
	      } 
	      this.attendanceRepository.save(attendance);
	      System.out.println("Saving attendance: " + attendance);
	      String employeeAttId = (attendance.getEmployeeAttId() != null) ? attendance.getEmployeeAttId().toString() : "N/A";
	      return ResponseEntity.ok("Attendance entry saved successfully. EmployeeAttId: " + employeeAttId);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error saving attendance: " + e.getMessage());
	    } 
	  }
	  
	  @PutMapping({"/attendance/edit/{id}"})
	  public ResponseEntity<EmployeeAttendance> updateAssest(@PathVariable("id") Long attendanceId, @RequestBody EmployeeAttendance attendance) {
	    try {
	      EmployeeAttendance existingAttendance = this.service.getById(attendanceId.longValue());
	      if (existingAttendance == null)
	        return ResponseEntity.notFound().build(); 
	      existingAttendance.setAttendance(attendance.getAttendance());
	      if ("PunchOut".equals(attendance.getAttendance())) {
	        existingAttendance.setPunchOut(true);
	        LocalTime inTime = LocalTime.parse(existingAttendance.getInTime(), 
	            DateTimeFormatter.ofPattern("hh:mm a"));
	        LocalTime outTime = LocalTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
	        String formattedTime = outTime.format(formatter);
	        existingAttendance.setOutTime(formattedTime);
	        Duration duration = Duration.between(inTime, outTime);
	        long minutes = duration.toMinutes();
	        long hours = minutes / 60L;
	        minutes %= 60L;
	        String formattedDuration = String.format("%02d:%02d", new Object[] { Long.valueOf(hours), Long.valueOf(minutes) });
	        existingAttendance.setWorkingHour(formattedDuration);
	      } 
	      this.service.save(existingAttendance);
	      return ResponseEntity.ok(existingAttendance);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/attendance/demo/edit/{id}"})
	  public ResponseEntity<String> updateAttendance1(@PathVariable Long id, @RequestBody EmployeeAttendance updatedAttendance) {
	    try {
	      LocalTime localTime = LocalTime.now();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
	      String formattedTime = localTime.format(formatter);
	      Optional<EmployeeAttendance> existingAttendance = this.attendanceRepository.findById(id);
	      if (existingAttendance.isPresent()) {
	        LocalTime outTimeLocal;
	        EmployeeAttendance oldAttendance = existingAttendance.get();
	        String inTime = oldAttendance.getInTime();
	        String outTime = updatedAttendance.getOutTime();
	        oldAttendance.setPunchOut(updatedAttendance.isPunchOut());
	        oldAttendance.setAttendance("PunchOut");
	        oldAttendance.setOutTime(formattedTime);
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
	        LocalTime inTimeLocal = LocalTime.parse(inTime, timeFormatter);
	        try {
	          outTimeLocal = LocalTime.parse(outTime, timeFormatter);
	        } catch (DateTimeParseException e) {
	          e.printStackTrace();
	          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body("Error parsing outTime: " + e.getMessage());
	        } 
	        Duration duration = Duration.between(inTimeLocal, outTimeLocal);
	        long minutes = duration.toMinutes();
	        long hours = minutes / 60L;
	        minutes %= 60L;
	        String workingHour = String.format("%02d:%02d", new Object[] { Long.valueOf(hours), Long.valueOf(minutes) });
	        oldAttendance.setWorkingHour(workingHour);
	        this.attendanceRepository.save(oldAttendance);
	        System.out.println("Updated attendance: " + oldAttendance);
	        return ResponseEntity.ok("Attendance entry updated successfully.");
	      } 
	      return ResponseEntity.status(HttpStatus.NOT_FOUND)
	        .body("Attendance entry with ID " + id + " not found.");
	    } catch (DateTimeParseException e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error parsing time: " + e.getMessage());
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error updating attendance: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/attendance/month/{employee_id}"})
	  public List<Map<String, Object>> getAllMemberDetailsByMemberId(@PathVariable Long employee_id) {
	    return this.attendanceRepository.Allfilter(employee_id.longValue());
	  }
	  
	  @GetMapping({"/attendance/current/{employee_id}"})
	  public List<Map<String, Object>> getAllMemberDetailsByMemberId1(@PathVariable Long employee_id) {
	    return this.attendanceRepository.Allfilter2(employee_id.longValue());
	  }
	  
	  @GetMapping({"/attendance/{employeeId}"})
	  public List<Map<String, Object>> getAllMemberDetailsByMemberId8(@PathVariable Long employeeId) {
	    return this.attendanceRepository.AttendanceforEmployeeId1(employeeId.longValue());
	  }
	  
	  @GetMapping({"/attendance/today/{employeeId}"})
	  public Map<String, Object> getAllMemberDetails(@PathVariable Long employeeId) {
	    return this.attendanceRepository.AttendanceforEmployeeId(employeeId.longValue());
	  }
	  
	  @GetMapping({"/attendance/date"})
	  public List<Map<String, Object>> getAllMemberDetailsByMemberId3(@RequestParam(required = true) String date) {
	    try {
	      if ("date".equals(date))
	        return this.attendanceRepository.Allfilter3(); 
	      throw new IllegalArgumentException("The provided attendance parameter is not supported.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	  
	  @GetMapping({"/attendance/view"})
	  public List<Map<String, Object>> INeedList(@RequestParam(required = true) String view) {
	    try {
	      if ("attendance".equals(view))
	        return this.attendanceRepository.GoodAllWorks(); 
	      throw new IllegalArgumentException("The provided attendance parameter is not supported.");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return Collections.emptyList();
	    } 
	  }
	}

