package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.DiscountRuleProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountRuleProductsRepository
  extends
    JpaRepository<DiscountRuleProducts, String>,
    JpaSpecificationExecutor<DiscountRuleProducts> {}
