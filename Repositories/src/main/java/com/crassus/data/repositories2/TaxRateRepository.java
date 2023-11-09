package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaxRateRepository
  extends JpaRepository<TaxRate, String>, JpaSpecificationExecutor<TaxRate> {}
