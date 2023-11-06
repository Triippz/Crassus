package com.crassus.data.repositories;

import com.crassus.models.models.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, String> {}
