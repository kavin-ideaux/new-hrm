package com.example.HRM.repository.clientDetails;
import com.example.HRM.entity.clientDetails.Receipts;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ReceiptRepository extends JpaRepository<Receipts, Long> {
	  @Query(value = "select r.*,i.balance,c.company_name,c.address,c.state,c.country,c.pincode,cp.client_name,cp.client_id from receipt as r join invoice as i on i.invoice_id = r.invoice_id join client_profile as cp on cp.client_id = i.client_id join company as c on c.company_id = i.company_id", nativeQuery = true)
	  List<Map<String, Object>> getAllReceiptDetailsByInvoice();
	  
	  @Query(value = "select r.*,i.balance,c.company_name,c.address,c.state,c.country,c.pincode,cp.client_name,cp.client_id from receipt as r join invoice as i on i.invoice_id = r.invoice_id join client_profile as cp on cp.client_id = i.client_id join company as c on c.company_id = i.company_id where cp.client_id =:client_id", nativeQuery = true)
	  List<Map<String, Object>> getAllReceiptDetailsByClientId(Long client_id);
	}
