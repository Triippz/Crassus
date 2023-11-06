package com.crassus.data.repositories;

import com.crassus.models.models.NotificationProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationProviderRepository
  extends JpaRepository<NotificationProvider, String> {}
