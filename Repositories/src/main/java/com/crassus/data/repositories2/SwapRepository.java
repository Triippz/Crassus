package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Swap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SwapRepository
  extends JpaRepository<Swap, String>, JpaSpecificationExecutor<Swap> {}
