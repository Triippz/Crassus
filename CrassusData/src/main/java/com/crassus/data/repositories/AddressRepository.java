package com.crassus.data.repositories;

import com.crassus.models.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {}
