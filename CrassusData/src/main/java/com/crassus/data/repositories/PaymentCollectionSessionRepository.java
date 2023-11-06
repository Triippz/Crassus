package com.crassus.data.repositories;

import com.crassus.models.models.PaymentCollectionSession;
import com.crassus.models.models.PaymentCollectionSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCollectionSessionRepository
  extends JpaRepository<PaymentCollectionSession, PaymentCollectionSessionId> {}
