package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerGroupRepository
  extends JpaRepository<CustomerGroup, String>, JpaSpecificationExecutor<CustomerGroup> {}
