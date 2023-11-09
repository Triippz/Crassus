package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StoreRepository
  extends JpaRepository<Store, String>, JpaSpecificationExecutor<Store> {}
