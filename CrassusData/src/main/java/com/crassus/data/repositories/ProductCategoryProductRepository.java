package com.crassus.data.repositories;

import com.crassus.models.models.ProductCategoryProduct;
import com.crassus.models.models.ProductCategoryProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryProductRepository
  extends JpaRepository<ProductCategoryProduct, ProductCategoryProductId> {}
