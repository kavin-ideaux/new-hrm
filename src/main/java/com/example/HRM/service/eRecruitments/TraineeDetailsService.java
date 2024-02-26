package com.example.HRM.service.eRecruitments;
import com.example.HRM.entity.eRecruitments.TraineeDetails;
import com.example.HRM.repository.eRecruitments.TraineeDetailsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeDetailsService {

	@Autowired
	  private TraineeDetailsRepository traineeDetailsRepository;
	  
	  public List<TraineeDetails> listAll() {
	    return this.traineeDetailsRepository.findAll();
	  }
	  
	  public TraineeDetails SaveTraineeDetails(TraineeDetails trainee) {
	    return (TraineeDetails)this.traineeDetailsRepository.save(trainee);
	  }
	  
	  public TraineeDetails findById(Long traineeId) {
	    return this.traineeDetailsRepository.findById(traineeId).get();
	  }
	  
	  public void deleteTraineeIdId(Long id) {
	    this.traineeDetailsRepository.deleteById(id);
	  }
	}

