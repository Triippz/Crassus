package com.crassus.data.repositories;

import com.crassus.models.models.ProductVariantMoneyAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantMoneyAmountRepository
  extends JpaRepository<ProductVariantMoneyAmount, String> {}
