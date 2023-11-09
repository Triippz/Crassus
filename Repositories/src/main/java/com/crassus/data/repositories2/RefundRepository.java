package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RefundRepository
  extends JpaRepository<Refund, String>, JpaSpecificationExecutor<Refund> {}
