package com.example.HRM.repository.accounting;
import com.example.HRM.entity.accounting.CompanyAssets;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface CompanyAssetsRepository extends JpaRepository<CompanyAssets, Long> {
	  @Query(value = " select c.*,b.brand_name,a.accessories_name from company_assets as c join brand as b on b.brand_id=c.brant_id join accessories as a on a.accessories_id=c.accessories_id;", nativeQuery = true)
	  List<Map<String, Object>> AllEmployees();
	  
	  CompanyAssets findByAccessoriesIdAndBrantId(Long paramLong1, Long paramLong2);
}