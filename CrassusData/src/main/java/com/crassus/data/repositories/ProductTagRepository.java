package com.crassus.data.repositories;

import com.crassus.models.models.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTagRepository extends JpaRepository<ProductTag, String> {}
