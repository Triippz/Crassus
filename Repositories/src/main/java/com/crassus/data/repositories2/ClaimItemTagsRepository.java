package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ClaimItemTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClaimItemTagsRepository
  extends JpaRepository<ClaimItemTags, String>, JpaSpecificationExecutor<ClaimItemTags> {}
