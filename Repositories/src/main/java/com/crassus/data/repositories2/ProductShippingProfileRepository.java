package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductShippingProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductShippingProfileRepository
  extends
    JpaRepository<ProductShippingProfile, Void>,
    JpaSpecificationExecutor<ProductShippingProfile> {}
