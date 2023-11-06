package com.crassus.data.repositories;

import com.crassus.models.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {}
