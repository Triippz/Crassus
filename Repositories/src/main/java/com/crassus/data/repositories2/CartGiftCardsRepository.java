package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.CartGiftCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartGiftCardsRepository
  extends JpaRepository<CartGiftCards, String>, JpaSpecificationExecutor<CartGiftCards> {}
