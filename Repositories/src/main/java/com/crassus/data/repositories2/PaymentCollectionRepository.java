package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.PaymentCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentCollectionRepository
  extends
    JpaRepository<PaymentCollection, String>,
    JpaSpecificationExecutor<PaymentCollection> {}
