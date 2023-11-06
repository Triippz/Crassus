package com.crassus.data.repositories;

import com.crassus.models.models.DiscountConditionProductType;
import com.crassus.models.models.DiscountConditionProductTypeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountConditionProductTypeRepository
  extends JpaRepository<DiscountConditionProductType, DiscountConditionProductTypeId> {}
