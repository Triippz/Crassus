package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTaxRateRepository
  extends
    JpaRepository<ProductTaxRate, String>, JpaSpecificationExecutor<ProductTaxRate> {}
