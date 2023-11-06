package com.crassus.data.repositories;

import com.crassus.models.models.OrderItemChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemChangeRepository
  extends JpaRepository<OrderItemChange, String> {}
