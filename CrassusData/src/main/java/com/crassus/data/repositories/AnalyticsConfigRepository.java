package com.crassus.data.repositories;

import com.crassus.models.models.AnalyticsConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsConfigRepository
  extends JpaRepository<AnalyticsConfig, String> {}
