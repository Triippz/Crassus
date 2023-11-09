package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductVariantInventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductVariantInventoryItemRepository
  extends
    JpaRepository<ProductVariantInventoryItem, String>,
    JpaSpecificationExecutor<ProductVariantInventoryItem> {}
