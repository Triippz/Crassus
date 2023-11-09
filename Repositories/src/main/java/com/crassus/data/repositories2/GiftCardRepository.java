package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GiftCardRepository
  extends JpaRepository<GiftCard, String>, JpaSpecificationExecutor<GiftCard> {}
