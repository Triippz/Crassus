package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.CustomShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomShippingOptionRepository
  extends
    JpaRepository<CustomShippingOption, String>,
    JpaSpecificationExecutor<CustomShippingOption> {}
