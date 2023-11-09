package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.PaymentCollectionPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentCollectionPaymentsRepository
  extends
    JpaRepository<PaymentCollectionPayments, String>,
    JpaSpecificationExecutor<PaymentCollectionPayments> {}
