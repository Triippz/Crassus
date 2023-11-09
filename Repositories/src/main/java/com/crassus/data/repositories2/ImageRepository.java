package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository
  extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {}
