package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ClaimTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClaimTagRepository
  extends JpaRepository<ClaimTag, String>, JpaSpecificationExecutor<ClaimTag> {}
