package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.OrderEdit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderEditRepository
  extends JpaRepository<OrderEdit, String>, JpaSpecificationExecutor<OrderEdit> {}
