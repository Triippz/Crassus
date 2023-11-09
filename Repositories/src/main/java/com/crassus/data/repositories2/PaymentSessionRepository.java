package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.PaymentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentSessionRepository
  extends
    JpaRepository<PaymentSession, String>, JpaSpecificationExecutor<PaymentSession> {}
