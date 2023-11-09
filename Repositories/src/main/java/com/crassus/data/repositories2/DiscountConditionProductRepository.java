package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DiscountConditionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountConditionProductRepository
  extends
    JpaRepository<DiscountConditionProduct, String>,
    JpaSpecificationExecutor<DiscountConditionProduct> {}
