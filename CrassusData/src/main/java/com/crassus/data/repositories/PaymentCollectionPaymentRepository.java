package com.crassus.data.repositories;

import com.crassus.models.models.PaymentCollectionPayment;
import com.crassus.models.models.PaymentCollectionPaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCollectionPaymentRepository
  extends JpaRepository<PaymentCollectionPayment, PaymentCollectionPaymentId> {}
