package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShippingMethodRepository
  extends
    JpaRepository<ShippingMethod, String>, JpaSpecificationExecutor<ShippingMethod> {}
