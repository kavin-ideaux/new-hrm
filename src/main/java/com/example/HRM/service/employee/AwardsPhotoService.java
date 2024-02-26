package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.AwardsPhoto;
import com.example.HRM.repository.employee.AwardsPhotoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwardsPhotoService {

	@Autowired
	  private AwardsPhotoRepository awardsPhotoRepository;
	  
	  public void create(AwardsPhoto awardsPhoto) {
	    this.awardsPhotoRepository.save(awardsPhoto);
	  }
	  
	  public List<AwardsPhoto> findByAwardsId(long awardsId) {
	    return this.awardsPhotoRepository.findByAwardsAwardsId(awardsId);
	  }
	  
	  public AwardsPhoto createAwardsPhoto(AwardsPhoto awardsPhoto) {
	    return (AwardsPhoto)this.awardsPhotoRepository.save(awardsPhoto);
	  }
	  
	  public List<AwardsPhoto> getAwardsPhotosByAwardsId(long awardsId) {
	    return this.awardsPhotoRepository.findByAwardsAwardsId(awardsId);
	  }
	  
	  public Optional<AwardsPhoto> getAwardsPhotoById(long awardsPhotoId) {
	    return this.awardsPhotoRepository.findById(Long.valueOf(awardsPhotoId));
	  }
	  
	  public Optional<AwardsPhoto> getVideoFileById(Long id) {
	    return this.awardsPhotoRepository.findById(id);
	  }
	}

