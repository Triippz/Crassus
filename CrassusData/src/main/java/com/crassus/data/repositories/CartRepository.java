package com.crassus.data.repositories;

import com.crassus.models.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {}
