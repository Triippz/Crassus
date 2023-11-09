package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ClaimImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClaimImageRepository
  extends JpaRepository<ClaimImage, String>, JpaSpecificationExecutor<ClaimImage> {}
