package com.crassus.data.repositories;

import com.crassus.models.models.ShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingOptionRepository extends JpaRepository<ShippingOption, String> {}
