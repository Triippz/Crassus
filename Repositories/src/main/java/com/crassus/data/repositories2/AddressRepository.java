package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository
  extends JpaRepository<Address, String>, JpaSpecificationExecutor<Address> {}
