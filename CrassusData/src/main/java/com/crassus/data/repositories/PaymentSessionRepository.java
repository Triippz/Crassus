package com.crassus.data.repositories;

import com.crassus.models.models.PaymentSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSessionRepository extends JpaRepository<PaymentSession, String> {}
