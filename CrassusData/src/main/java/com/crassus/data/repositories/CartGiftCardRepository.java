package com.crassus.data.repositories;

import com.crassus.models.models.CartGiftCard;
import com.crassus.models.models.CartGiftCardId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartGiftCardRepository
  extends JpaRepository<CartGiftCard, CartGiftCardId> {}
