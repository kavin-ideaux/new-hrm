package com.example.HRM.service.accounting;
import com.example.HRM.entity.accounting.CompanyAssets;
import com.example.HRM.repository.accounting.CompanyAssetsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyAssetsService {

	@Autowired
	  private CompanyAssetsRepository repo;
	  
	  public List<CompanyAssets> listAll() {
	    return this.repo.findAll();
	  }
	  
	  public void SaveorUpdate(CompanyAssets announcement) {
	    this.repo.save(announcement);
	  }
	  
	  public void save(CompanyAssets announcement) {
	    this.repo.save(announcement);
	  }
	  
	  public CompanyAssets findById(Long companyAssets_id) {
	    return this.repo.findById(companyAssets_id).get();
	  }
	  
	  public CompanyAssets findByAccessoriesIdAndBrantId(Long accessoriesId, Long brantId) {
	    return this.repo.findByAccessoriesIdAndBrantId(accessoriesId, brantId);
	  }
	  
	  public void deleteCompanyAssetsIdById(Long companyAssets_id) {
	    this.repo.deleteById(companyAssets_id);
	  }
	  
	  public Optional<CompanyAssets> getCompanyAssetsById(Long companyAssets_id) {
	    return this.repo.findById(companyAssets_id);
	  }
	}
