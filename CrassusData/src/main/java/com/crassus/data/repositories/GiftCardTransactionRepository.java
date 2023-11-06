package com.crassus.data.repositories;

import com.crassus.models.models.GiftCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftCardTransactionRepository
  extends JpaRepository<GiftCardTransaction, String> {}
