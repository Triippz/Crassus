package com.crassus.data.repositories;

import com.crassus.models.models.ShippingProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingProfileRepository
  extends JpaRepository<ShippingProfile, String> {}
