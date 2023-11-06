package com.crassus.data.repositories;

import com.crassus.models.models.ShippingTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingTaxRateRepository
  extends JpaRepository<ShippingTaxRate, com.crassus.models.models.ShippingTaxRateId> {}
