package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.PublishableApiKeySalesChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PublishableApiKeySalesChannelRepository
  extends
    JpaRepository<PublishableApiKeySalesChannel, String>,
    JpaSpecificationExecutor<PublishableApiKeySalesChannel> {}
