package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.OrderDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDiscountsRepository
  extends
    JpaRepository<OrderDiscounts, String>, JpaSpecificationExecutor<OrderDiscounts> {}
