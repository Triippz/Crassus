package com.crassus.data.repositories;

import com.crassus.models.models.ClaimItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimItemRepository extends JpaRepository<ClaimItem, String> {}
