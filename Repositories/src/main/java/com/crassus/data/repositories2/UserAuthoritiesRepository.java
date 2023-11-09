package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.UserAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserAuthoritiesRepository
  extends
    JpaRepository<UserAuthorities, String>, JpaSpecificationExecutor<UserAuthorities> {}
