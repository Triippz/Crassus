package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ShippingTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShippingTaxRateRepository
  extends
    JpaRepository<ShippingTaxRate, String>, JpaSpecificationExecutor<ShippingTaxRate> {}
