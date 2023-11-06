package com.crassus.data.repositories;

import com.crassus.models.models.DraftOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftOrderRepository extends JpaRepository<DraftOrder, String> {}
