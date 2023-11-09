package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductTypeTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTypeTaxRateRepository
  extends
    JpaRepository<ProductTypeTaxRate, String>,
    JpaSpecificationExecutor<ProductTypeTaxRate> {}
