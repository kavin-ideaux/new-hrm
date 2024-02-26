package com.example.HRM.service.accounting;
import com.example.HRM.entity.accounting.Accessories;
import com.example.HRM.repository.accounting.AccessoriesRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccessoriesService {
	@Autowired
	  private AccessoriesRepository Repo;
	  
	  public Iterable<Accessories> listAll() {
	    return this.Repo.findAll();
	  }
	  
	  public void SaveorUpdate(Accessories keyboardBrand) {
	    this.Repo.save(keyboardBrand);
	  }
	  
	  public void save(Accessories keyboardBrand) {
	    this.Repo.save(keyboardBrand);
	  }
	  
	  public Accessories findById(Long keyboard_brand_id) {
	    return this.Repo.findById(keyboard_brand_id).get();
	  }
	  
	  public void deleteKeyboardBrandById(Long keyboard_brand_id) {
	    this.Repo.deleteById(keyboard_brand_id);
	  }
	  
	  public Optional<Accessories> getKeyboardBrandById(Long keyboard_brand_id) {
	    return this.Repo.findById(keyboard_brand_id);
	  }
	}


