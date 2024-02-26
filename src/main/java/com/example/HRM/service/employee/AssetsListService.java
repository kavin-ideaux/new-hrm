package com.example.HRM.service.employee;
import com.example.HRM.entity.employee.AssetsList;
import com.example.HRM.repository.employee.AssetsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetsListService {

	 @Autowired
	  private AssetsListRepository repo;
	  
	  public void save(AssetsList attendancelist) {
	    this.repo.save(attendancelist);
	  }
	  
	  public AssetsList findById(Long attendanceListId) {
	    return this.repo.findById(attendanceListId).get();
	  }
	}

