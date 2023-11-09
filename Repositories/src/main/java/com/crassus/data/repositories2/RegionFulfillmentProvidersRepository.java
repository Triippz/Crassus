package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.RegionFulfillmentProviders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegionFulfillmentProvidersRepository
  extends
    JpaRepository<RegionFulfillmentProviders, String>,
    JpaSpecificationExecutor<RegionFulfillmentProviders> {}
