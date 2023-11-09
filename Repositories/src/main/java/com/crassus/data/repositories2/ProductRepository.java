package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository
  extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {}
