package com.crassus.data.repositories;

import com.crassus.models.models.PublishableApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishableApiKeyRepository
  extends JpaRepository<PublishableApiKey, String> {}
