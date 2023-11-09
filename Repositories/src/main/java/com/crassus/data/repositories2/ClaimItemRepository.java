package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ClaimItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClaimItemRepository
  extends JpaRepository<ClaimItem, String>, JpaSpecificationExecutor<ClaimItem> {}
