package com.crassus.data.repositories;

import com.crassus.models.models.CustomerGroupCustomer;
import com.crassus.models.models.CustomerGroupCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupCustomerRepository
  extends JpaRepository<CustomerGroupCustomer, CustomerGroupCustomerId> {}
