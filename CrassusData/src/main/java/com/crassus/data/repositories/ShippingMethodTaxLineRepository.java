package com.crassus.data.repositories;

import com.crassus.models.models.ShippingMethodTaxLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingMethodTaxLineRepository
  extends JpaRepository<ShippingMethodTaxLine, String> {}
