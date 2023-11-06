package com.crassus.data.repositories;

import com.crassus.models.models.RegionPaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionPaymentProviderRepository
  extends
    JpaRepository<
      RegionPaymentProvider,
      com.crassus.models.models.RegionPaymentProviderId
    > {}
