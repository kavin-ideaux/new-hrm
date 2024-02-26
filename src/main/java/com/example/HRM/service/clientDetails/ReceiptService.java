package com.example.HRM.service.clientDetails;
import com.example.HRM.entity.clientDetails.Receipts;
import com.example.HRM.repository.clientDetails.ReceiptRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
	 @Autowired
	  private ReceiptRepository receiptRepository;
	  
	  public List<Receipts> balanceReceipts() {
	    return this.receiptRepository.findAll();
	  }
	  
	  public void SaveReceipt(Receipts receipt) {
	    this.receiptRepository.save(receipt);
	  }
	  
	  public Receipts findReceiptById(Long id) {
	    return this.receiptRepository.findById(id).get();
	  }
	  
	  public void deleteReceiptId(Long id) {
	    this.receiptRepository.deleteById(id);
	  }
	}

