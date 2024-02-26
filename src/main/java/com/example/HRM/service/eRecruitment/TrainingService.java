package com.example.HRM.service.eRecruitment;
import com.example.HRM.entity.erecruitment.Training;
import com.example.HRM.repository.erecruitment.TrainingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

	 @Autowired
	  private TrainingRepository trainingRepository;
	  
	  public List<Training> listAll() {
	    return this.trainingRepository.findAll();
	  }
	  
	  public void SaveTrainee(Training training) {
	    this.trainingRepository.save(training);
	  }
	  
	  public Training findById(Long id) {
	    return this.trainingRepository.findById(id).get();
	  }
	  
	  public void deleteTrainingId(Long id) {
	    this.trainingRepository.deleteById(id);
	  }
	}

