package com.crassus.data.repositories;

import com.crassus.models.models.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItem, String> {}
