package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTagRepository
  extends JpaRepository<ProductTag, String>, JpaSpecificationExecutor<ProductTag> {}
