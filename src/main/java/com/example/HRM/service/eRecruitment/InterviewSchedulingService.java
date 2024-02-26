package com.example.HRM.service.eRecruitment;
import com.example.HRM.entity.erecruitment.InterviewSchedule;
import com.example.HRM.repository.erecruitment.InterviewSchedulingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewSchedulingService {@Autowired
	  private InterviewSchedulingRepository interviewSchedulingRepository;

	  public List<InterviewSchedule> listAll() {
	    return this.interviewSchedulingRepository.findAll();
	  }
	  
	  public void SaveInterviewSchedule(InterviewSchedule interviewSchedule) {
	    this.interviewSchedulingRepository.save(interviewSchedule);
	  }
	  
	  public InterviewSchedule findById(Long id) {
	    return this.interviewSchedulingRepository.findById(id).get();
	  }
	  
	  public void deleteInterviewSchedulingId(Long id) {
	    this.interviewSchedulingRepository.deleteById(id);
	  }
	}