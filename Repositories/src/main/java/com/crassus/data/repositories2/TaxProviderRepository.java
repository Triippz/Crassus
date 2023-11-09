package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.TaxProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaxProviderRepository
  extends JpaRepository<TaxProvider, String>, JpaSpecificationExecutor<TaxProvider> {}
