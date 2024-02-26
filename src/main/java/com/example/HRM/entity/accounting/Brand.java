package com.example.HRM.entity.accounting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long brandId;

	private String brandName;

	public long getBrandId() {
		return this.brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
