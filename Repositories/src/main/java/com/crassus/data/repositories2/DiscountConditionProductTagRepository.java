package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DiscountConditionProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountConditionProductTagRepository
  extends
    JpaRepository<DiscountConditionProductTag, String>,
    JpaSpecificationExecutor<DiscountConditionProductTag> {}
