package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.RegionPaymentProviders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegionPaymentProvidersRepository
  extends
    JpaRepository<RegionPaymentProviders, String>,
    JpaSpecificationExecutor<RegionPaymentProviders> {}
