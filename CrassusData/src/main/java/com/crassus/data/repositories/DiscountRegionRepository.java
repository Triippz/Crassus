package com.crassus.data.repositories;

import com.crassus.models.models.DiscountRegion;
import com.crassus.models.models.DiscountRegionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRegionRepository
  extends JpaRepository<DiscountRegion, DiscountRegionId> {}
