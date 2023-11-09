package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.TrackingLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TrackingLinkRepository
  extends JpaRepository<TrackingLink, String>, JpaSpecificationExecutor<TrackingLink> {}
