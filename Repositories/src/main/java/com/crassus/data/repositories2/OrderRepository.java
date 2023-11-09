package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository
  extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {}
