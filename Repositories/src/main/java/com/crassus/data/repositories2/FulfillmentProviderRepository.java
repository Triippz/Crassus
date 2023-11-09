package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.FulfillmentProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FulfillmentProviderRepository
  extends
    JpaRepository<FulfillmentProvider, String>,
    JpaSpecificationExecutor<FulfillmentProvider> {}
