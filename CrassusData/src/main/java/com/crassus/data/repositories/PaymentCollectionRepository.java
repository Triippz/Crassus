package com.crassus.data.repositories;

import com.crassus.models.models.PaymentCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCollectionRepository
  extends JpaRepository<PaymentCollection, String> {}
