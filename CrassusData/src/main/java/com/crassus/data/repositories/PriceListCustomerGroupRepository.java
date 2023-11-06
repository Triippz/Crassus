package com.crassus.data.repositories;

import com.crassus.models.models.PriceListCustomerGroup;
import com.crassus.models.models.PriceListCustomerGroupId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListCustomerGroupRepository
  extends JpaRepository<PriceListCustomerGroup, PriceListCustomerGroupId> {}
