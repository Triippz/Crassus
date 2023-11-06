package com.crassus.data.repositories;

import com.crassus.models.models.DiscountConditionProductTag;
import com.crassus.models.models.DiscountConditionProductTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountConditionProductTagRepository
  extends JpaRepository<DiscountConditionProductTag, DiscountConditionProductTagId> {}
