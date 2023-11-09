package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.NotificationProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationProviderRepository
  extends
    JpaRepository<NotificationProvider, String>,
    JpaSpecificationExecutor<NotificationProvider> {}
