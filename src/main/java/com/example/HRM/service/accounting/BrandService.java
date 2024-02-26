package com.example.HRM.service.accounting;
import com.example.HRM.entity.accounting.Brand;
import com.example.HRM.repository.accounting.BrandRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

	@Autowired
	  private BrandRepository Repo;
	  
	  public Iterable<Brand> listAll() {
	    return this.Repo.findAll();
	  }
	  
	  public void SaveorUpdate(Brand brand) {
	    this.Repo.save(brand);
	  }
	  
	  public void save(Brand brand) {
	    this.Repo.save(brand);
	  }
	  
	  public Brand findById(Long brand_id) {
	    return this.Repo.findById(brand_id).get();
	  }
	  
	  public void deleteBrandById(Long brand_id) {
	    this.Repo.deleteById(brand_id);
	  }
	  
	  public Optional<Brand> getBrandById(Long brand_id) {
	    return this.Repo.findById(brand_id);
	  }
	}

