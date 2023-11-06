package com.crassus.data.repositories;

import com.crassus.models.models.ProductSalesChannel;
import com.crassus.models.models.ProductSalesChannelId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSalesChannelRepository
  extends JpaRepository<ProductSalesChannel, ProductSalesChannelId> {}
