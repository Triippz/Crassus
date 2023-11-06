package com.crassus.data.repositories;

import com.crassus.models.models.LineItemTaxLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemTaxLineRepository
  extends JpaRepository<LineItemTaxLine, String> {}
