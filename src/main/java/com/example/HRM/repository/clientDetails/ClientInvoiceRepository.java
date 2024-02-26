package com.example.HRM.repository.clientDetails;

import com.example.HRM.entity.clientDetails.ClientInvoice;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ClientInvoiceRepository extends JpaRepository<ClientInvoice, Long> {
	  @Query(value = "select i.*,il.invoice_list_id,il.project_id,il.amount as listAmount,il.discount_percentage,il.gst,il.price,il.quantity,il.tax_include_price,il.tax_quantity_amount, il.total_tax_amount,c.email,c.client_name,c.phone_number from invoice as i\t\tjoin invoice_list as il on il.invoice_id = i.invoice_id\t\t join client_profile as c on c.client_id=i.client_id", nativeQuery = true)
	  List<Map<String, Object>> getAllInvoiceDetailsDemo();
	  
	  @Query(value = "select i.invoice_id,cp.address,cp.city,cp.client_name,cp.country,cp.email,cp.gender,cp.phone_number,cp.mobile_number,cp.state,cp.zip_code, c.company_name,c.email as companyEmail,c.phone_number1,c.phone_number2,c.pincode,c.country as companyCountry, c.address as companyAddress from invoice as i join  client_profile as cp on cp.client_id = i.client_id join company as c on c.company_id = i.company_id", nativeQuery = true)
	  List<Map<String, Object>> getAllInvoiceDetailsList();
	}
