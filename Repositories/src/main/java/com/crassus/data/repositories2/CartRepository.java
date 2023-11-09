package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository
  extends JpaRepository<Cart, String>, JpaSpecificationExecutor<Cart> {}
