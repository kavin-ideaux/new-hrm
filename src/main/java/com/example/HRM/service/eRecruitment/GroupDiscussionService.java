package com.example.HRM.service.eRecruitment;

import com.example.HRM.entity.erecruitment.GroupDiscussion;
import com.example.HRM.repository.erecruitment.GroupDiscussionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupDiscussionService {

	 @Autowired
	  private GroupDiscussionRepository groupDiscussionRepository;
	  
	  public List<GroupDiscussion> listAll() {
	    return this.groupDiscussionRepository.findAll();
	  }
	  
	  public void SaveGroupDiscussion(GroupDiscussion discussion) {
	    this.groupDiscussionRepository.save(discussion);
	  }
	  
	  public GroupDiscussion findById(Long id) {
	    return this.groupDiscussionRepository.findById(id).get();
	  }
	  
	  public void deletegroupDiscussionId(Long id) {
	    this.groupDiscussionRepository.deleteById(id);
	  }
	}

