package com.crassus.models.models;

import com.crassus.models.BaseEntity;
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
@Table(name = "publishable_api_key")
public class PublishableApiKey extends BaseEntity {

  @Column(name = "created_by", length = Integer.MAX_VALUE)
  private String createdBy;

  @Column(name = "revoked_by", length = Integer.MAX_VALUE)
  private String revokedBy;

  @Column(name = "revoked_at")
  private OffsetDateTime revokedAt;

  @NotNull
  @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
  private String title;

  @Override
  protected String getIdPrefix() {
    return "pk";
  }
}
