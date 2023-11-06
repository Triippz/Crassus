package com.crassus.data.repositories;

import com.crassus.models.models.DiscountConditionProductCollection;
import com.crassus.models.models.DiscountConditionProductCollectionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountConditionProductCollectionRepository
  extends
    JpaRepository<
      DiscountConditionProductCollection,
      DiscountConditionProductCollectionId
    > {}
