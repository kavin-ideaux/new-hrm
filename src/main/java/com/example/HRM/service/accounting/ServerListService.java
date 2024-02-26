package com.example.HRM.service.accounting;
import com.example.HRM.entity.accounting.ServerList;
import com.example.HRM.repository.accounting.ServerListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerListService {

	 @Autowired
	  private ServerListRepository Repo;
	  
	  public Iterable<ServerList> listAll() {
	    return this.Repo.findAll();
	  }
	  
	  public void SaveorUpdate(ServerList serverList) {
	    this.Repo.save(serverList);
	  }
	  
	  public void save(ServerList serverList) {
	    this.Repo.save(serverList);
	  }
	  
	  public ServerList findById(Long ServerList_id) {
	    return this.Repo.findById(ServerList_id).get();
	  }
	  
	  public void deleteAssestIdById(Long ServerList_id) {
	    this.Repo.deleteById(ServerList_id);
	  }
	  
	  public ServerList getById(long id) {
	    return this.Repo.findById(Long.valueOf(id)).get();
	  }
	}

