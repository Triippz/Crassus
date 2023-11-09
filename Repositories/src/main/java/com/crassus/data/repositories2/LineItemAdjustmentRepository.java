package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.LineItemAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LineItemAdjustmentRepository
  extends
    JpaRepository<LineItemAdjustment, String>,
    JpaSpecificationExecutor<LineItemAdjustment> {}
