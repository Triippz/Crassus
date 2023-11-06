package com.crassus.data.repositories;

import com.crassus.models.models.ProductTaxRate;
import com.crassus.models.models.ProductTaxRateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTaxRateRepository
  extends JpaRepository<ProductTaxRate, ProductTaxRateId> {}
