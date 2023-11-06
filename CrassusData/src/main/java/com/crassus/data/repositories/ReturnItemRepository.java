package com.crassus.data.repositories;

import com.crassus.models.models.ReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnItemRepository
  extends JpaRepository<ReturnItem, com.crassus.models.models.ReturnItemId> {}
