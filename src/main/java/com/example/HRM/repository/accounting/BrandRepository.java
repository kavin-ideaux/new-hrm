package com.example.HRM.repository.accounting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HRM.entity.accounting.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	
}

