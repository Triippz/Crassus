package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductCategoryRepository
  extends
    JpaRepository<ProductCategory, String>, JpaSpecificationExecutor<ProductCategory> {}
