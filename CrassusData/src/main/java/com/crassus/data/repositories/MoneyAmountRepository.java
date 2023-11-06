package com.crassus.data.repositories;

import com.crassus.models.models.MoneyAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyAmountRepository extends JpaRepository<MoneyAmount, String> {}
