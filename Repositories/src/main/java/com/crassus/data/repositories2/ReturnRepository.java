package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReturnRepository
  extends JpaRepository<Return, String>, JpaSpecificationExecutor<Return> {}
