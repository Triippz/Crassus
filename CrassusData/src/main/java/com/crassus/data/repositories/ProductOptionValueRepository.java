package com.crassus.data.repositories;

import com.crassus.models.models.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionValueRepository
  extends JpaRepository<ProductOptionValue, String> {}
