package com.example.HRM.service.eRecruitments;
import com.example.HRM.entity.eRecruitments.Certificate;
import com.example.HRM.repository.eRecruitments.CertificateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {
	 @Autowired
	  private CertificateRepository certificateRepository;
	  
	  public List<Certificate> listAll() {
	    return this.certificateRepository.findAll();
	  }
	  
	  public Certificate SaveCertificate(Certificate certificate) {
	    return (Certificate)this.certificateRepository.save(certificate);
	  }
	  
	  public Certificate findById(Long certificateId) {
	    return this.certificateRepository.findById(certificateId).get();
	  }
	  
	  public void deleteCertificateId(Long id) {
	    this.certificateRepository.deleteById(id);
	  }
	

}
