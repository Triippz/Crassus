package com.crassus.data.repositories;

import com.crassus.models.models.OrderGiftCard;
import com.crassus.models.models.OrderGiftCardId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGiftCardRepository
  extends JpaRepository<OrderGiftCard, OrderGiftCardId> {}
