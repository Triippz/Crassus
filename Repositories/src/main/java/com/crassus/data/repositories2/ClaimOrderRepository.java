package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ClaimOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClaimOrderRepository
  extends JpaRepository<ClaimOrder, String>, JpaSpecificationExecutor<ClaimOrder> {}
