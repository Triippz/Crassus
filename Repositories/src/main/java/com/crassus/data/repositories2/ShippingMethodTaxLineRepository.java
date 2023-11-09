package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.ShippingMethodTaxLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShippingMethodTaxLineRepository
  extends
    JpaRepository<ShippingMethodTaxLine, String>,
    JpaSpecificationExecutor<ShippingMethodTaxLine> {}
