package com.crassus.data.repositories;

import com.crassus.models.models.ProductImage;
import com.crassus.models.models.ProductImageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository
  extends JpaRepository<ProductImage, ProductImageId> {}
