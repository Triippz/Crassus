package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LineItemRepository
  extends JpaRepository<LineItem, String>, JpaSpecificationExecutor<LineItem> {}
