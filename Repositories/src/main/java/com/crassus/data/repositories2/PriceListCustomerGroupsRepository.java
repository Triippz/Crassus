package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.PriceListCustomerGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PriceListCustomerGroupsRepository
  extends
    JpaRepository<PriceListCustomerGroups, String>,
    JpaSpecificationExecutor<PriceListCustomerGroups> {}
