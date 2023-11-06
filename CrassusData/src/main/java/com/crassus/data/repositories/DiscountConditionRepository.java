package com.crassus.data.repositories;

import com.crassus.models.models.DiscountCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountConditionRepository
  extends JpaRepository<DiscountCondition, String> {}
