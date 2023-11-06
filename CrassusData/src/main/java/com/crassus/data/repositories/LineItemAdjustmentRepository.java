package com.crassus.data.repositories;

import com.crassus.models.models.LineItemAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemAdjustmentRepository
  extends JpaRepository<LineItemAdjustment, String> {}
