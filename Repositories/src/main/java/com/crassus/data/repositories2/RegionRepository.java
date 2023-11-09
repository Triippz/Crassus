package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegionRepository
  extends JpaRepository<Region, String>, JpaSpecificationExecutor<Region> {}
