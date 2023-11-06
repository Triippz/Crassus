package com.crassus.data.repositories;

import com.crassus.models.models.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String> {}
