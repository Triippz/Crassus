package com.crassus.data.repositories;

import com.crassus.models.models.ProductShippingProfile;
import com.crassus.models.models.ProductShippingProfileId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductShippingProfileRepository
  extends JpaRepository<ProductShippingProfile, ProductShippingProfileId> {}
