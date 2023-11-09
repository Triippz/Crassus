package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReturnItemRepository
  extends JpaRepository<ReturnItem, String>, JpaSpecificationExecutor<ReturnItem> {}
