package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DiscountConditionCustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountConditionCustomerGroupRepository
  extends
    JpaRepository<DiscountConditionCustomerGroup, String>,
    JpaSpecificationExecutor<DiscountConditionCustomerGroup> {}
