package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DraftOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DraftOrderRepository
  extends JpaRepository<DraftOrder, String>, JpaSpecificationExecutor<DraftOrder> {}
