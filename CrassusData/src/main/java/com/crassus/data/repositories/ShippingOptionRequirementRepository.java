package com.crassus.data.repositories;

import com.crassus.models.models.ShippingOptionRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingOptionRequirementRepository
  extends JpaRepository<ShippingOptionRequirement, String> {}
