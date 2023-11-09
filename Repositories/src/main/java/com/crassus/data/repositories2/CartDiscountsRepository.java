package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.CartDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartDiscountsRepository
  extends JpaRepository<CartDiscounts, String>, JpaSpecificationExecutor<CartDiscounts> {}
