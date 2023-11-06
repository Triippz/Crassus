package com.crassus.data.repositories;

import com.crassus.models.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {}
