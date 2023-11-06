package com.crassus.data.repositories;

import com.crassus.models.models.DiscountConditionProduct;
import com.crassus.models.models.DiscountConditionProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountConditionProductRepository
  extends JpaRepository<DiscountConditionProduct, DiscountConditionProductId> {}
