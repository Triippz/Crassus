package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTagsRepository
  extends JpaRepository<ProductTags, String>, JpaSpecificationExecutor<ProductTags> {}
