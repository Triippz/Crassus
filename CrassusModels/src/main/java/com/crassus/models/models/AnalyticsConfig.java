package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "analytics_config")
public class AnalyticsConfig extends SoftDeletableEntity {

  @NotNull
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @NotNull
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = "deleted_at")
  private OffsetDateTime deletedAt;

  @NotNull
  @Column(name = "user_id", nullable = false, length = Integer.MAX_VALUE)
  private String userId;

  @NotNull
  @Column(name = "opt_out", nullable = false)
  private Boolean optOut = false;

  @NotNull
  @Column(name = "anonymize", nullable = false)
  private Boolean anonymize = false;

  @Override
  protected String getIdPrefix() {
    return "acfg";
  }
}
