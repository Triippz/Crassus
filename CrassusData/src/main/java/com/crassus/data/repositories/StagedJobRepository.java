package com.crassus.data.repositories;

import com.crassus.models.models.StagedJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagedJobRepository extends JpaRepository<StagedJob, String> {}
