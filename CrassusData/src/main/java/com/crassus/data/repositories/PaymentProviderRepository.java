package com.crassus.data.repositories;

import com.crassus.models.models.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentProviderRepository
  extends JpaRepository<PaymentProvider, String> {}
