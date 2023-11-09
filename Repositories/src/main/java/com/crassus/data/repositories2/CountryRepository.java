package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepository
  extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {}
