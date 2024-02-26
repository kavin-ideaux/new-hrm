package com.example.HRM.controller.eRecruitment;

import com.example.HRM.entity.erecruitment.Candidate;
import com.example.HRM.repository.erecruitment.CandidateRepository;
import com.example.HRM.service.eRecruitment.CandidateService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class CandidateController {
	@Autowired
	  private CandidateService candidateService;
	  
	  @Autowired
	  private CandidateRepository candidateRepository;
	  
	  @PostMapping({"/candidate/save"})
	  public ResponseEntity<String> saveCandidate(@RequestParam("userName") String userName, @RequestParam("emailId") String emailId, @RequestParam("mobileNumber") String mobileNumber, @RequestParam("gender") String gender, @RequestParam("dateofBirth") Date dateofBirth, @RequestParam("branch") String branch, @RequestParam("college") String college, @RequestParam("skillDetails") String skillDetails, @RequestParam("yearOfPassing") String yearOfPassing, @RequestParam("education") String education, @RequestParam("salaryExpectations") double salaryExpectations, @RequestParam("address") String address, @RequestParam("city") String city, @RequestParam("jobRole") String jobRole, @RequestParam("country") String country, @RequestParam("maritalStatus") String maritalStatus, @RequestParam("date") Date date, @RequestParam("cgpa") String cgpa, @RequestParam("workExperience") String workExperience, @RequestParam("resume") MultipartFile resume, @RequestParam("coverLetter") String coverLetter) throws SQLException {
	    try {
	      Candidate candidate = new Candidate();
	      candidate.setUserName(userName);
	      candidate.setEmailId(emailId);
	      candidate.setMobileNumber(mobileNumber);
	      candidate.setGender(gender);
	      candidate.setDateofBirth(dateofBirth);
	      candidate.setEducation(education);
	      candidate.setBranch(branch);
	      candidate.setCgpa(cgpa);
	      candidate.setCollege(college);
	      candidate.setSkillDetails(skillDetails);
	      candidate.setSalaryExpectations(salaryExpectations);
	      candidate.setAddress(address);
	      candidate.setCity(city);
	      candidate.setCountry(country);
	      candidate.setMaritalStatus(maritalStatus);
	      candidate.setDate(date);
	      candidate.setYearOfPassing(yearOfPassing);
	      candidate.setCoverLetter(coverLetter);
	      candidate.setJobRole(jobRole);
	      candidate.setWorkExperience(workExperience);
	      candidate.setResume(convertToBlob(resume));
	      this.candidateService.SaveCandidateDetails(candidate);
	      return ResponseEntity.ok("Candidate Details saved successfully.");
	    } catch (IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while saving the candidate: " + e.getMessage());
	    } 
	  }
	  
	  private Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
	    if (file != null && !file.isEmpty()) {
	      byte[] bytes = file.getBytes();
	      return new SerialBlob(bytes);
	    } 
	    return null;
	  }
	  
	  @GetMapping({"/candidate/candidate"})
	  public ResponseEntity<?> getCandidateDetails(@RequestParam(required = true) String candidate) {
	    try {
	      if ("candidate".equals(candidate)) {
	        List<Candidate> candidateList = this.candidateService.listAll();
	        List<Candidate> candidateResponseList = new ArrayList<>();
	        for (Candidate candidate1 : candidateList) {
	          String resumeUrl = "candidateResume/" + candidate1.getCandidateId();
	          Candidate candidateResponse = new Candidate();
	          candidateResponse.setCandidateId(candidate1.getCandidateId());
	          candidateResponse.setAddress(candidate1.getAddress());
	          candidateResponse.setUserName(candidate1.getUserName());
	          candidateResponse.setCity(candidate1.getCity());
	          candidateResponse.setCountry(candidate1.getCountry());
	          candidateResponse.setDate(candidate1.getDate());
	          candidateResponse.setDateofBirth(candidate1.getDateofBirth());
	          candidateResponse.setBranch(candidate1.getBranch());
	          candidateResponse.setEducation(candidate1.getEducation());
	          candidateResponse.setEmailId(candidate1.getEmailId());
	          candidateResponse.setGender(candidate1.getGender());
	          candidateResponse.setCgpa(candidate1.getCgpa());
	          candidateResponse.setCollege(candidate1.getCollege());
	          candidateResponse.setJobRole(candidate1.getJobRole());
	          candidateResponse.setYearOfPassing(candidate1.getYearOfPassing());
	          candidateResponse.setSkillDetails(candidate1.getSkillDetails());
	          candidateResponse.setMaritalStatus(candidate1.getMaritalStatus());
	          candidateResponse.setMobileNumber(candidate1.getMobileNumber());
	          candidateResponse.setSalaryExpectations(candidate1.getSalaryExpectations());
	          candidateResponse.setWorkExperience(candidate1.getWorkExperience());
	          candidateResponse.setCoverLetter(candidate1.getCoverLetter());
	          candidateResponse.setResumeUrl(resumeUrl);
	          candidateResponseList.add(candidateResponse);
	        } 
	        return ResponseEntity.ok(candidateResponseList);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameter value for hrInterview");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving candidate details: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"candidateResume/{candidateId}"})
	  public ResponseEntity<byte[]> downloadCoverLetterFile(@PathVariable long candidateId) {
	    try {
	      Candidate candidate = this.candidateService.findById(Long.valueOf(candidateId));
	      if (candidate != null) {
	        Blob pdfBlob = candidate.getResume();
	        if (pdfBlob != null) {
	          byte[] pdfBytes = pdfBlob.getBytes(1L, (int)pdfBlob.length());
	          HttpHeaders headers = new HttpHeaders();
	          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	          String filename = "candidateFile.pdf";
	          headers.setContentDispositionFormData("attachment", filename);
	          return new ResponseEntity(pdfBytes, (MultiValueMap)headers, HttpStatus.OK);
	        } 
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/candidate/{candidateId}"})
	  public ResponseEntity<String> updateCandidateFile(@PathVariable long candidateId, @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "emailId", required = false) String emailId, @RequestParam(value = "mobileNumber", required = false) String mobileNumber, @RequestParam(value = "gender", required = false) String gender, @RequestParam(value = "dateofBirth", required = false) Date dateofBirth, @RequestParam(value = "branch", required = false) String branch, @RequestParam(value = "college", required = false) String college, @RequestParam(value = "skillDetails", required = false) String skillDetails, @RequestParam(value = "yearOfPassing", required = false) String yearOfPassing, @RequestParam(value = "education", required = false) String education, @RequestParam(value = "salaryExpectations", required = false) double salaryExpectations, @RequestParam(value = "address", required = false) String address, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "jobRole", required = false) String jobRole, @RequestParam(value = "country", required = false) String country, @RequestParam(value = "maritalStatus", required = false) String maritalStatus, @RequestParam(value = "date", required = false) Date date, @RequestParam(value = "cgpa", required = false) String cgpa, @RequestParam(value = "workExperience", required = false) String workExperience, @RequestParam(value = "resume", required = false) MultipartFile resume, @RequestParam(value = "coverLetter", required = false) String coverLetter) {
	    try {
	      Candidate candidate = this.candidateService.findById(Long.valueOf(candidateId));
	      if (candidate != null) {
	        if (resume != null && !resume.isEmpty()) {
	          Blob resumeBlob = convertToBlob(resume);
	          candidate.setResume(resumeBlob);
	        } 
	        candidate.setUserName(userName);
	        candidate.setEmailId(emailId);
	        candidate.setMobileNumber(mobileNumber);
	        candidate.setGender(gender);
	        candidate.setDateofBirth(dateofBirth);
	        candidate.setBranch(branch);
	        candidate.setCollege(college);
	        candidate.setSkillDetails(skillDetails);
	        candidate.setYearOfPassing(yearOfPassing);
	        candidate.setEducation(education);
	        candidate.setSalaryExpectations(salaryExpectations);
	        candidate.setAddress(address);
	        candidate.setCity(city);
	        candidate.setCountry(country);
	        candidate.setJobRole(jobRole);
	        candidate.setWorkExperience(workExperience);
	        candidate.setMaritalStatus(maritalStatus);
	        candidate.setCgpa(cgpa);
	        candidate.setDate(date);
	        candidate.setCoverLetter(coverLetter);
	        this.candidateService.SaveCandidateDetails(candidate);
	        return ResponseEntity.ok("Candidate updated successfully.");
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (SQLException|IOException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @DeleteMapping({"/candidateDetail/delete/{id}"})
	  public ResponseEntity<String> deleteappointment(@PathVariable("id") Long id) {
	    this.candidateService.deleteCandidateId(id);
	    return ResponseEntity.ok("Candidate details deleted successfully");
	  }
	  
	  @GetMapping({"/view"})
	  public ResponseEntity<?> getCandidateDetails(@RequestParam(name = "candidateId") Long candidateId) {
	    try {
	      Candidate candidate = this.candidateService.findById(candidateId);
	      if (candidate == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found for ID: " + candidateId); 
	      Candidate candidateResponse = new Candidate();
	      candidateResponse.setCandidateId(candidate.getCandidateId());
	      candidateResponse.setAddress(candidate.getAddress());
	      candidateResponse.setCity(candidate.getCity());
	      candidateResponse.setCountry(candidate.getCountry());
	      candidateResponse.setDate(candidate.getDate());
	      candidateResponse.setDateofBirth(candidate.getDateofBirth());
	      candidateResponse.setBranch(candidate.getBranch());
	      candidateResponse.setEducation(candidate.getEducation());
	      candidateResponse.setEmailId(candidate.getEmailId());
	      candidateResponse.setGender(candidate.getGender());
	      candidateResponse.setCgpa(candidate.getCgpa());
	      candidateResponse.setCollege(candidate.getCollege());
	      candidateResponse.setJobRole(candidate.getJobRole());
	      candidateResponse.setYearOfPassing(candidate.getYearOfPassing());
	      candidateResponse.setSkillDetails(candidate.getSkillDetails());
	      candidateResponse.setUserName(candidate.getUserName());
	      candidateResponse.setMaritalStatus(candidate.getMaritalStatus());
	      candidateResponse.setMobileNumber(candidate.getMobileNumber());
	      candidateResponse.setSalaryExpectations(candidate.getSalaryExpectations());
	      candidateResponse.setWorkExperience(candidate.getWorkExperience());
	      candidateResponse.setResumeUrl("/candidateResume/" + candidateId);
	      return ResponseEntity.ok(candidateResponse);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error occurred while retrieving candidate details");
	    } 
	  }
	  
	  @GetMapping({"findCandidatesCount"})
	  public List<Map<String, Object>> findCandidatesCount() {
	    return this.candidateRepository.countOfCandidatesDetails();
	  }
	  
	  @GetMapping({"findCandidatesCounts"})
	  public List<Map<String, Object>> findCandidatesCount1(@RequestParam(name = "date", required = false) String date, @RequestParam(name = "month", required = false) String month, @RequestParam(name = "year", required = false) String year) {
	    return this.candidateRepository.countOfCandidatesDetails();
	  }
	  
	  @GetMapping({"/currentDateCandidatesDetails"})
	  public ResponseEntity<?> getCandidateDetail() {
	    try {
	      List<Map<String, Object>> candidates = this.candidateRepository.findCurrentDateCandidatesDetails();
	      List<Map<String, Object>> candidateList = new ArrayList<>();
	      for (Map<String, Object> candidate : candidates) {
	        Map<String, Object> candidateMap = new HashMap<>();
	        String resumeUrl = "/candidateResume/" + candidate.get("candidate_id");
	        String coverLetterUrl = "/candidateCoverLetter/" + candidate.get("candidate_id");
	        candidateMap.put("resumeUrl", resumeUrl);
	        candidateMap.put("coverLetterUrl", coverLetterUrl);
	        candidateMap.putAll(candidate);
	        candidateList.add(candidateMap);
	      } 
	      return ResponseEntity.ok(candidateList);
	    } catch (Exception e) {
	      String errorMessage = "Error occurred while retrieving candidate details";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Collections.singletonMap("error", errorMessage));
	    } 
	  }
	  
	  @GetMapping({"/highestExperienceCandidateDetails"})
	  public ResponseEntity<?> getCandidateDetails11() {
	    try {
	      List<Map<String, Object>> candidates = this.candidateRepository.findHighestExperienceDetails();
	      List<Map<String, Object>> candidateList = new ArrayList<>();
	      for (Map<String, Object> candidate : candidates) {
	        Map<String, Object> candidateMap = new HashMap<>();
	        Object candidateId = candidate.get("candidateId");
	        String resumeUrl = (candidateId != null) ? ("/candidateResume/" + candidateId) : null;
	        String coverLetterUrl = (candidateId != null) ? ("/candidateCoverLetter/" + candidateId) : null;
	        candidateMap.put("resumeUrl", resumeUrl);
	        candidateMap.put("coverLetterUrl", coverLetterUrl);
	        candidateMap.put("CandidateId", candidateId);
	        candidateMap.put("FirstName", candidate.get("firstName"));
	        candidateMap.put("LastName", candidate.get("lastName"));
	        candidateMap.put("date", candidate.get("date"));
	        candidateMap.put("jobRole", candidate.get("jobRole"));
	        candidateMap.put("skillDetails", candidate.get("skillDetails"));
	        candidateList.add(candidateMap);
	      } 
	      return ResponseEntity.ok(candidateList);
	    } catch (Exception e) {
	      String errorMessage = "Error occurred while retrieving candidate details";
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Collections.singletonMap("error", errorMessage));
	    } 
	  }
	}

