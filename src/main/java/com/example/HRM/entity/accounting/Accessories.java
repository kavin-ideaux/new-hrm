package com.example.HRM.entity.accounting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accessories")
public class Accessories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accessoriesId;

	private String accessoriesName;

	public Long getAccessoriesId() {
		return this.accessoriesId;
	}

	public void setAccessoriesId(Long accessoriesId) {
		this.accessoriesId = accessoriesId;
	}

	public String getAccessoriesName() {
		return this.accessoriesName;
	}

	public void setAccessoriesName(String accessoriesName) {
		this.accessoriesName = accessoriesName;
	}
}
