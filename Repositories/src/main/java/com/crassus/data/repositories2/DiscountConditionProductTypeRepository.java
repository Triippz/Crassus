package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DiscountConditionProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountConditionProductTypeRepository
  extends
    JpaRepository<DiscountConditionProductType, String>,
    JpaSpecificationExecutor<DiscountConditionProductType> {}
