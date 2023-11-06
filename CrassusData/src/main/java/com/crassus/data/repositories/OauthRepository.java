package com.crassus.data.repositories;

import com.crassus.models.models.Oauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<Oauth, String> {}
