package com.example.HRM.service.eRecruitment;
import com.example.HRM.entity.erecruitment.Offer;
import com.example.HRM.repository.erecruitment.OfferRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

	@Autowired
	  private OfferRepository offerRepo;
	  
	  public List<Offer> listAll() {
	    return this.offerRepo.findAll();
	  }
	  
	  public Offer SaveOfferLetter(Offer offer) {
	    return (Offer)this.offerRepo.save(offer);
	  }
	  
	  public Offer findById(Long offerId) {
	    return this.offerRepo.findById(offerId).get();
	  }
	  
	  public void deleteOfferId(Long id) {
	    this.offerRepo.deleteById(id);
	  }
	}

