package com.crassus.data.repositories;

import com.crassus.models.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, String> {}
