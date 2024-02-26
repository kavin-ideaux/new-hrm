package com.example.HRM.entity.accounting;

import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_assets")
public class CompanyAssets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyAssetsId;

	private Date date;

	private Blob billing;

	private int assetValues;

	private int count;

	private Long brantId;

	private Long accessoriesId;

	private boolean status;

	private String url;

	public String getUrl() {
		return this.url;
	}

	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setStatus(Boolean status) {
		this.status = status.booleanValue();
	}

	public Long getAccessoriesId() {
		return this.accessoriesId;
	}

	public void setAccessoriesId(Long accessoriesId) {
		this.accessoriesId = accessoriesId;
	}

	public Long getCompanyAssetsId() {
		return this.companyAssetsId;
	}

	public void setCompanyAssetsId(Long companyAssetsId) {
		this.companyAssetsId = companyAssetsId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Blob getBilling() {
		return this.billing;
	}

	public void setBilling(Blob billing) {
		this.billing = billing;
	}

	public int getAssetValues() {
		return this.assetValues;
	}

	public void setAssetValues(int assetValues) {
		this.assetValues = assetValues;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getBrantId() {
		return this.brantId;
	}

	public void setBrantId(Long brantId) {
		this.brantId = brantId;
	}
}
