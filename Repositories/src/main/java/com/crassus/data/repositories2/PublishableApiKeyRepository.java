package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.PublishableApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PublishableApiKeyRepository
  extends
    JpaRepository<PublishableApiKey, String>,
    JpaSpecificationExecutor<PublishableApiKey> {}
