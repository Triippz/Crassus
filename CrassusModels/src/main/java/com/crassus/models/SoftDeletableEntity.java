package com.crassus.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class SoftDeletableEntity extends BaseEntity {

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime deletedAt;
}
