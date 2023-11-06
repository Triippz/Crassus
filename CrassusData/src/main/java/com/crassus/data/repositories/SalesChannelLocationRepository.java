package com.crassus.data.repositories;

import com.crassus.models.models.SalesChannelLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesChannelLocationRepository
  extends JpaRepository<SalesChannelLocation, String> {}
