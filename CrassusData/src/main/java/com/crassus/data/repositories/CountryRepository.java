package com.crassus.data.repositories;

import com.crassus.models.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {}
