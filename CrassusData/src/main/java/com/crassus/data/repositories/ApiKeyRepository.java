package com.crassus.data.repositories;

import com.crassus.models.models.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {}
