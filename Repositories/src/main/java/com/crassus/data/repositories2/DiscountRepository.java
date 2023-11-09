package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountRepository
  extends JpaRepository<Discount, String>, JpaSpecificationExecutor<Discount> {}
