package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DiscountConditionProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountConditionProductCollectionRepository
  extends
    JpaRepository<DiscountConditionProductCollection, String>,
    JpaSpecificationExecutor<DiscountConditionProductCollection> {}
