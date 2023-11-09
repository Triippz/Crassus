package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentProviderRepository
  extends
    JpaRepository<PaymentProvider, String>, JpaSpecificationExecutor<PaymentProvider> {}
