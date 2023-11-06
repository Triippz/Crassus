package com.crassus.data.repositories;

import com.crassus.models.models.ClaimItemTag;
import com.crassus.models.models.ClaimItemTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimItemTagRepository
  extends JpaRepository<ClaimItemTag, ClaimItemTagId> {}
