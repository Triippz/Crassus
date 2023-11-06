package com.crassus.data.repositories;

import com.crassus.models.models.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, String> {}
