package com.example.HRM.controller.eRecruitment;
import com.example.HRM.entity.erecruitment.TaskAssigned;
import com.example.HRM.repository.erecruitment.TaskAssignedRepository;
import com.example.HRM.service.eRecruitment.TaskAssignedService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class TaskAssignedController {
	@Autowired
	  private TaskAssignedService taskAssignedService;
	  
	  @Autowired
	  private TaskAssignedRepository taskAssignedRepository;
	  
	  private String formatTime(String time, SimpleDateFormat formatter) throws Exception {
	    SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm:ss a");
	    java.util.Date date = inputFormat.parse(time);
	    return formatter.format(date);
	  }
	  
	  private Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
	    if (file != null && !file.isEmpty()) {
	      byte[] bytes = file.getBytes();
	      return new SerialBlob(bytes);
	    } 
	    return null;
	  }
	  
	  @GetMapping({"/taskAssigned/view"})
	  public ResponseEntity<?> getTaskAssigned() {
	    try {
	      List<TaskAssigned> taskAssigned = this.taskAssignedService.listAll();
	      List<TaskAssigned> taskAssignedList = new ArrayList<>();
	      for (TaskAssigned tasks : taskAssigned) {
	        String taskUrl = "/taskAssigned/" + tasks.getTaskId();
	        TaskAssigned taskAssignedResponse = new TaskAssigned();
	        taskAssignedResponse.setTaskId(tasks.getTaskId());
	        taskAssignedResponse.setCandidateId(tasks.getCandidateId());
	        taskAssignedResponse.setComment(tasks.getComment());
	        taskAssignedResponse.setDate(tasks.getDate());
	        taskAssignedResponse.setEndTime(tasks.getEndTime());
	        taskAssignedResponse.setInterviewProcessId(tasks.getInterviewProcessId());
	        taskAssignedResponse.setStartTime(tasks.getStartTime());
	        taskAssignedResponse.setStarted(tasks.isStarted());
	        taskAssignedResponse.setCompleted(tasks.isCompleted());
	        taskAssignedResponse.setTaskPriority(tasks.getTaskPriority());
	        taskAssignedResponse.setTaskAssignee(tasks.getTaskAssignee());
	        taskAssignedResponse.setTaskUrl(taskUrl);
	        taskAssignedList.add(taskAssignedResponse);
	      } 
	      return ResponseEntity.ok(taskAssignedList);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving taskAssigned");
	    } 
	  }
	  
	  @GetMapping({"/taskAssigned/{taskId}"})
	  public ResponseEntity<byte[]> downloadOfferFile(@PathVariable long taskId) {
	    try {
	      TaskAssigned taskAssigned = this.taskAssignedService.findById(Long.valueOf(taskId));
	      if (taskAssigned != null) {
	        Blob pdfBlob = taskAssigned.getTaskFile();
	        if (pdfBlob != null) {
	          byte[] pdfBytes = pdfBlob.getBytes(1L, (int)pdfBlob.length());
	          HttpHeaders headers = new HttpHeaders();
	          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	          String filename = "offerFile.pdf";
	          headers.setContentDispositionFormData("attachment", filename);
	          return new ResponseEntity(pdfBytes, (MultiValueMap)headers, HttpStatus.OK);
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/taskAssigned/{taskId}"})
	  public ResponseEntity<String> updateTaskAssigned(@PathVariable long taskId, @RequestParam("taskAssignee") String taskAssignee, @RequestParam("date") Date date, @RequestParam("startTime") Time startTime, @RequestParam("endTime") Time endTime, @RequestParam("taskPriority") String taskPriority, @RequestParam("candidateId") long candidateId, @RequestParam("approvalType") String approvalType, @RequestParam("taskFile") MultipartFile taskFile) {
	    try {
	      TaskAssigned taskAssigned = this.taskAssignedService.findById(Long.valueOf(taskId));
	      if (taskAssigned != null) {
	        Blob pdfBlob = taskAssigned.getTaskFile();
	        if (pdfBlob != null) {
	          byte[] newPdfBytes = taskFile.getBytes();
	          pdfBlob.setBytes(1L, newPdfBytes);
	          updateTaskStatus(taskAssigned, approvalType);
	          taskAssigned.setCandidateId(candidateId);
	          taskAssigned.setDate(date);
	          taskAssigned.setTaskPriority(taskPriority);
	          taskAssigned.setTaskAssignee(taskAssignee);
	          this.taskAssignedService.SaveTaskAssigned(taskAssigned);
	          return ResponseEntity.ok("TaskAssigned updated successfully.");
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException|IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  private void updateTaskStatus(TaskAssigned taskAssigned, String approvalType) {
	    if ("started".equals(approvalType)) {
	      taskAssigned.setStarted(true);
	      taskAssigned.setCompleted(false);
	    } else if ("completed".equals(approvalType)) {
	      taskAssigned.setStarted(false);
	      taskAssigned.setCompleted(true);
	    } else {
	      taskAssigned.setStarted(false);
	      taskAssigned.setCompleted(false);
	    } 
	  }
	  
	  @DeleteMapping({"/taskAssigned/delete/{id}"})
	  public ResponseEntity<String> deletetaskAssigned(@PathVariable("id") Long id) {
	    this.taskAssignedService.deleteTaskAssignedId(id);
	    return ResponseEntity.ok("TaskAssigned details deleted successfully");
	  }
	  
	  @GetMapping({"/findTaskAssignedDetails"})
	  public ResponseEntity<?> getTaskAssignedDetails(@RequestParam(required = true) String TaskAssigned) {
	    try {
	      if ("findTaskAssignedDetails".equals(TaskAssigned)) {
	        List<Map<String, Object>> tasks = this.taskAssignedRepository.findTaskAssignedDetails();
	        List<Map<String, Object>> taskList = new ArrayList<>();
	        for (Map<String, Object> taskAssigned : tasks) {
	          Map<String, Object> taskAssignedMap = new HashMap<>();
	          String taskUrl = "/taskAssigned/" + taskAssigned.get("task_id");
	          taskAssignedMap.put("taskUrl", taskUrl);
	          taskAssignedMap.putAll(taskAssigned);
	          taskList.add(taskAssignedMap);
	        } 
	        return ResponseEntity.ok(taskList);
	      } 
	      String errorMessage = "Invalid value for 'TaskAssigned'. Expected 'findTaskAssignedDetails'.";
	      return ResponseEntity.badRequest().body(Collections.singletonMap("error", errorMessage));
	    } catch (Exception e) {
	      String errorMessage = "Error occurred while retrieving task assigned details";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Collections.singletonMap("error", errorMessage));
	    } 
	  }
	}
