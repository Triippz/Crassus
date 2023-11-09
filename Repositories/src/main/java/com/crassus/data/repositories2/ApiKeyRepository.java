package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiKeyRepository
  extends JpaRepository<ApiKey, String>, JpaSpecificationExecutor<ApiKey> {}
