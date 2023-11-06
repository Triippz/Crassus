package com.crassus.data.repositories;

import com.crassus.models.models.DiscountConditionCustomerGroup;
import com.crassus.models.models.DiscountConditionCustomerGroupId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountConditionCustomerGroupRepository
  extends
    JpaRepository<DiscountConditionCustomerGroup, DiscountConditionCustomerGroupId> {}
