package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.GiftCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GiftCardTransactionRepository
  extends
    JpaRepository<GiftCardTransaction, String>,
    JpaSpecificationExecutor<GiftCardTransaction> {}
