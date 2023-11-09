package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ReturnReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReturnReasonRepository
  extends JpaRepository<ReturnReason, String>, JpaSpecificationExecutor<ReturnReason> {}
