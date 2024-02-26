package com.example.HRM.service.eRecruitment;

import com.example.HRM.entity.erecruitment.InterviewProcess;
import com.example.HRM.repository.erecruitment.InterviewProcessRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewProcessService {
	@Autowired
	  private InterviewProcessRepository interviewProcessRepository;
	  
	  public List<InterviewProcess> listAll() {
	    return this.interviewProcessRepository.findAll();
	  }
	  
	  public void SaveInterviewProcess(InterviewProcess interviewProcess) {
	    this.interviewProcessRepository.save(interviewProcess);
	  }
	  
	  public InterviewProcess findById(Long id) {
	    return this.interviewProcessRepository.findById(id).get();
	  }
	  
	  public void deleteInterviewProcessId(Long id) {
	    this.interviewProcessRepository.deleteById(id);
	  }
	}


