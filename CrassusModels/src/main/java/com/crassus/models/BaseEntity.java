package com.crassus.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity extends BaseIdEntity {

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime updatedAt;
}
