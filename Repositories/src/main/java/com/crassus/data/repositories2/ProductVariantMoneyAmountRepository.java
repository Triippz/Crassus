package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductVariantMoneyAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductVariantMoneyAmountRepository
  extends
    JpaRepository<ProductVariantMoneyAmount, String>,
    JpaSpecificationExecutor<ProductVariantMoneyAmount> {}
