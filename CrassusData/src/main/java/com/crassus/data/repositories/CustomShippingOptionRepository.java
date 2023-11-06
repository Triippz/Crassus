package com.crassus.data.repositories;

import com.crassus.models.models.CustomShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomShippingOptionRepository
  extends JpaRepository<CustomShippingOption, String> {}
