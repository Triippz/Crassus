package com.crassus.data.repositories;

import com.crassus.models.models.FulfillmentItem;
import com.crassus.models.models.FulfillmentItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FulfillmentItemRepository
  extends JpaRepository<FulfillmentItem, FulfillmentItemId> {}
