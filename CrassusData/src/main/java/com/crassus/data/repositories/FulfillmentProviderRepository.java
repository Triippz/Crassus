package com.crassus.data.repositories;

import com.crassus.models.models.FulfillmentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FulfillmentProviderRepository
  extends JpaRepository<FulfillmentProvider, String> {}
