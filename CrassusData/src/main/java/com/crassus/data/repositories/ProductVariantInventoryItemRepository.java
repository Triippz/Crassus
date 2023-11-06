package com.crassus.data.repositories;

import com.crassus.models.models.ProductVariantInventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantInventoryItemRepository
  extends JpaRepository<ProductVariantInventoryItem, String> {}
