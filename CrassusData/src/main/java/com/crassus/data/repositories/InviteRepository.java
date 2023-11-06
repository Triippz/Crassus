package com.crassus.data.repositories;

import com.crassus.models.models.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteRepository extends JpaRepository<Invite, String> {}
