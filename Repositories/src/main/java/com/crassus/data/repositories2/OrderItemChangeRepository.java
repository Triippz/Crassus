package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.OrderItemChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemChangeRepository
  extends
    JpaRepository<OrderItemChange, String>, JpaSpecificationExecutor<OrderItemChange> {}
