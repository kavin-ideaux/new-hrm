package com.example.HRM.service.eRecruitment;
import com.example.HRM.entity.erecruitment.HrInterview;
import com.example.HRM.repository.erecruitment.HrInterviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrInterviewService {

	@Autowired
	  private HrInterviewRepository hrInterviewRepository;
	  
	  public List<HrInterview> listAll() {
	    return this.hrInterviewRepository.findAll();
	  }
	  
	  public void SaveHrInterview(HrInterview hrInterview) {
	    this.hrInterviewRepository.save(hrInterview);
	  }
	  
	  public HrInterview findById(Long id) {
	    return this.hrInterviewRepository.findById(id).get();
	  }
	  
	  public void deleteHrInterviewId(Long id) {
	    this.hrInterviewRepository.deleteById(id);
	  }
	}
