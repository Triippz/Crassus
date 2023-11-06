package com.crassus.data.repositories;

import com.crassus.models.models.Fulfillment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FulfillmentRepository extends JpaRepository<Fulfillment, String> {}
