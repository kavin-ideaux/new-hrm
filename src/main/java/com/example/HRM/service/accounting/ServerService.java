package com.example.HRM.service.accounting;
import com.example.HRM.entity.accounting.Server;
import com.example.HRM.repository.accounting.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

	@Autowired
	  private ServerRepository Repo;
	  
	  public Iterable<Server> listAll() {
	    return this.Repo.findAll();
	  }
	  
	  public void SaveorUpdate(Server server) {
	    this.Repo.save(server);
	  }
	  
	  public void save(Server server) {
	    this.Repo.save(server);
	  }
	  
	  public Server findById(long serverId) {
	    return this.Repo.findById(Long.valueOf(serverId)).get();
	  }
	  
	  public void deleteAssestIdById(long server_id) {
	    this.Repo.deleteById(Long.valueOf(server_id));
	  }
	  
	  public Server getById(long id) {
	    return this.Repo.findById(Long.valueOf(id)).get();
	  }
	}

