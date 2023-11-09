package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentRepository
  extends JpaRepository<Payment, String>, JpaSpecificationExecutor<Payment> {}
