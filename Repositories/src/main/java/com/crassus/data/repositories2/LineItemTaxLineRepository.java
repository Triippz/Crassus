package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.LineItemTaxLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LineItemTaxLineRepository
  extends
    JpaRepository<LineItemTaxLine, String>, JpaSpecificationExecutor<LineItemTaxLine> {}
