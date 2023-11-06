package com.crassus.data.repositories;

import com.crassus.models.models.OrderDiscount;
import com.crassus.models.models.OrderDiscountId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDiscountRepository
  extends JpaRepository<OrderDiscount, OrderDiscountId> {}
