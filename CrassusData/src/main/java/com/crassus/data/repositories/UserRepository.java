package com.crassus.data.repositories;

import com.crassus.models.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findOneByLogin(String login);
}
