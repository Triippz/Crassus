package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImagesRepository
  extends JpaRepository<ProductImages, String>, JpaSpecificationExecutor<ProductImages> {}
