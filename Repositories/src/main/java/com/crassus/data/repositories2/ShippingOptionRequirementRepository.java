package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ShippingOptionRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShippingOptionRequirementRepository
  extends
    JpaRepository<ShippingOptionRequirement, String>,
    JpaSpecificationExecutor<ShippingOptionRequirement> {}
