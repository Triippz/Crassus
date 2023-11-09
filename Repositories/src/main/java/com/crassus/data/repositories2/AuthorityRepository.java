package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorityRepository
  extends JpaRepository<Authority, String>, JpaSpecificationExecutor<Authority> {}
