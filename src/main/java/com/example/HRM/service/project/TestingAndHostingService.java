package com.example.HRM.service.project;

import com.example.HRM.entity.project.TestingAndHosting;
import com.example.HRM.repository.project.TestingAndHostingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestingAndHostingService {

	@Autowired
	  private TestingAndHostingRepository testingAndHostingRepository;
	  
	  public List<TestingAndHosting> testingAndHostingForProject() {
	    return this.testingAndHostingRepository.findAll();
	  }
	  
	  public void saveProjectTestAndHost(TestingAndHosting testing) {
	    this.testingAndHostingRepository.save(testing);
	  }
	  
	  public void deleteProjectAssignedForTestAndHost(Long id) {
	    this.testingAndHostingRepository.deleteById(id);
	  }
	  
	  public TestingAndHosting findProjectAssignedForTestAndHostById(Long id) {
	    return this.testingAndHostingRepository.findById(id).get();
	  }
	}
