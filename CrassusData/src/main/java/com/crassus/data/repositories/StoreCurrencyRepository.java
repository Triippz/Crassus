package com.crassus.data.repositories;

import com.crassus.models.models.StoreCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCurrencyRepository
  extends JpaRepository<StoreCurrency, com.crassus.models.models.StoreCurrencyId> {}
