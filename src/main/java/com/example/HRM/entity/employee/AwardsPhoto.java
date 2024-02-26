package com.example.HRM.entity.employee;
import com.example.HRM.entity.employee.Awards;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "awardsphoto")
public class AwardsPhoto {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "awardsPhotoId")
	  private long awardsPhotoId;
	  
	  private String url;
	  
	  @Lob
	  @JsonIgnore
	  private Blob awardsPhoto;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "awardsId", referencedColumnName = "awardsId")
	  private Awards awards;
	  
	  public long getAwardsPhotoId() {
	    return this.awardsPhotoId;
	  }
	  
	  public String getUrl() {
	    return this.url;
	  }
	  
	  public void setUrl(String url) {
	    this.url = url;
	  }
	  
	  public void setAwardsPhotoId(long awardsPhotoId) {
	    this.awardsPhotoId = awardsPhotoId;
	  }
	  
	  public Blob getAwardsPhoto() {
	    return this.awardsPhoto;
	  }
	  
	  public void setAwardsPhoto(Blob awardsPhoto) {
	    this.awardsPhoto = awardsPhoto;
	  }
	}


