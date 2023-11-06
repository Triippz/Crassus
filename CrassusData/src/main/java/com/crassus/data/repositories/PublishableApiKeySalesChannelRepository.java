package com.crassus.data.repositories;

import com.crassus.models.models.PublishableApiKeySalesChannel;
import com.crassus.models.models.PublishableApiKeySalesChannelId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishableApiKeySalesChannelRepository
  extends JpaRepository<PublishableApiKeySalesChannel, PublishableApiKeySalesChannelId> {}
