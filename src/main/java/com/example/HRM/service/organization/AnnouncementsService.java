package com.example.HRM.service.organization;
import com.example.HRM.entity.organization.Announcements;
import com.example.HRM.repository.organization.AnnouncementsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementsService {

	@Autowired
	  private AnnouncementsRepository announcementsRepository;
	  
	  public List<Announcements> listAll() {
	    return this.announcementsRepository.findAll();
	  }
	  
	  public void SaveorUpdate(Announcements announcement) {
	    this.announcementsRepository.save(announcement);
	  }
	  
	  public void save(Announcements announcement) {
	    this.announcementsRepository.save(announcement);
	  }
	  
	  public Announcements findById(Long announcement_id) {
	    return this.announcementsRepository.findById(announcement_id).get();
	  }
	  
	  public void deleteAnnouncementsIdById(Long announcement_id) {
	    this.announcementsRepository.deleteById(announcement_id);
	  }
	  
	  public Optional<Announcements> getAnnouncementsById(Long announcement_id) {
	    return this.announcementsRepository.findById(announcement_id);
	  }
	}

