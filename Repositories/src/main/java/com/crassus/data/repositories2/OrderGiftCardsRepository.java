package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.OrderGiftCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderGiftCardsRepository
  extends
    JpaRepository<OrderGiftCards, String>, JpaSpecificationExecutor<OrderGiftCards> {}
