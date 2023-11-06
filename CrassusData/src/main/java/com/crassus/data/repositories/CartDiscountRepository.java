package com.crassus.data.repositories;

import com.crassus.models.models.CartDiscount;
import com.crassus.models.models.CartDiscountId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDiscountRepository
  extends JpaRepository<CartDiscount, CartDiscountId> {}
