package com.crassus.data.repositories;

import com.crassus.models.models.RegionFulfillmentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionFulfillmentProviderRepository
  extends
    JpaRepository<
      RegionFulfillmentProvider,
      com.crassus.models.models.RegionFulfillmentProviderId
    > {}
