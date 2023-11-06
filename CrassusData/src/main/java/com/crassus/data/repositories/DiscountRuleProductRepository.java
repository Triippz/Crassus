package com.crassus.data.repositories;

import com.crassus.models.models.DiscountRuleProduct;
import com.crassus.models.models.DiscountRuleProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRuleProductRepository
  extends JpaRepository<DiscountRuleProduct, DiscountRuleProductId> {}
