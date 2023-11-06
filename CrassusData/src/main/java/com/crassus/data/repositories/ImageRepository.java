package com.crassus.data.repositories;

import com.crassus.models.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {}
