package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.AnalyticsConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnalyticsConfigRepository
  extends
    JpaRepository<AnalyticsConfig, String>, JpaSpecificationExecutor<AnalyticsConfig> {}
