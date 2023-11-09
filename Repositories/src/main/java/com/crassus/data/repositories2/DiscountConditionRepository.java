package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DiscountCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountConditionRepository
  extends
    JpaRepository<DiscountCondition, String>,
    JpaSpecificationExecutor<DiscountCondition> {}
