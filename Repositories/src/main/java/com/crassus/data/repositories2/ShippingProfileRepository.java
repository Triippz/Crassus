package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ShippingProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShippingProfileRepository
  extends
    JpaRepository<ShippingProfile, String>, JpaSpecificationExecutor<ShippingProfile> {}
