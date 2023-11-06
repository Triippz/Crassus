package com.crassus.data.repositories;

import com.crassus.models.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository
  extends JpaRepository<ProductCategory, String> {}
