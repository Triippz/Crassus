package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.FulfillmentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FulfillmentItemRepository
  extends
    JpaRepository<FulfillmentItem, String>, JpaSpecificationExecutor<FulfillmentItem> {}
