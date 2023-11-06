package com.crassus.data.repositories;

import com.crassus.models.models.ProductTypeTaxRate;
import com.crassus.models.models.ProductTypeTaxRateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeTaxRateRepository
  extends JpaRepository<ProductTypeTaxRate, ProductTypeTaxRateId> {}
