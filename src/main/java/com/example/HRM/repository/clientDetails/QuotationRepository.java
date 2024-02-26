package com.example.HRM.repository.clientDetails;
import com.example.HRM.entity.clientDetails.Quotation;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
	  @Query(value = "  select q.*,c.client_name,c.phone_number,c.email,p.project_type from quotation as q join client_profile as c on c.client_id=q.client_id join project_type as p on p.project_type_id = q.project_type_id", nativeQuery = true)
	  List<Map<String, Object>> getAllQuotation();
	  
	  Optional<Quotation> findByClientId(long paramLong);
	  
	  @Query(value = "select q.*,ql.additional_notes,ql.terms_and_condition,ql.quotation_list_id,t.amount as quotationAmount,t.description,t.term_list_id,t.item_name,t.quantity,t.rate, cp.address,cp.city,cp.client_name,cp.country,cp.email,cp.gender,cp.phone_number,cp.mobile_number,cp.state,cp.zip_code,c.company_name,c.email as companyEmail,c.phone_number1,c.phone_number2,c.pincode,c.country as companyCountry, c.address as companyAddress,p.project_type from quotation as q join quotation_list as ql on ql.quotation_id = q.quotation_id join terms as t on t.quotation_id = q.quotation_id join  client_profile as cp on cp.client_id = q.client_id join company as c on c.company_id = q.company_id join project_type as p on p.project_type_id = q.project_type_id", nativeQuery = true)
	  List<Map<String, Object>> getAllQuotationByClirentDetails();
	  
	  @Query(value = "select  q.quotation_id,cp.address,cp.city,cp.client_name,cp.country,cp.email,cp.gender,cp.phone_number,cp.mobile_number,cp.state,cp.zip_code, c.company_name,c.email as companyEmail,c.phone_number1,c.phone_number2,c.pincode,c.country as companyCountry, c.address as companyAddress,p.project_type from quotation as q join  client_profile as cp on cp.client_id = q.client_id join company as c on c.company_id = q.company_id join project_type as p on p.project_type_id = q.project_type_id", nativeQuery = true)
	  List<Map<String, Object>> getAllQuotationByClientDetails();
	}
