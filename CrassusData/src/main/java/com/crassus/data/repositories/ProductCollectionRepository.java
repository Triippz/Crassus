package com.crassus.data.repositories;

import com.crassus.models.models.ProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCollectionRepository
  extends JpaRepository<ProductCollection, String> {}
