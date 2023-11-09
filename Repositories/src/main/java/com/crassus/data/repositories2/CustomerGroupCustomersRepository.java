package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.CustomerGroupCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerGroupCustomersRepository
  extends
    JpaRepository<CustomerGroupCustomers, String>,
    JpaSpecificationExecutor<CustomerGroupCustomers> {}
