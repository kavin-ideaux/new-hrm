package com.example.HRM.service.eRecruitment;
import com.example.HRM.entity.erecruitment.Candidate;
import com.example.HRM.repository.erecruitment.CandidateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

	@Autowired
	  private CandidateRepository candidateRepository;
	  
	  public List<Candidate> listAll() {
	    return this.candidateRepository.findAll();
	  }
	  
	  public Candidate SaveCandidateDetails(Candidate candidate) {
	    return (Candidate)this.candidateRepository.save(candidate);
	  }
	  
	  public Candidate findById(Long candidateId) {
	    return this.candidateRepository.findById(candidateId).get();
	  }
	  
	  public void deleteCandidateId(Long id) {
	    this.candidateRepository.deleteById(id);
	  }
	}

