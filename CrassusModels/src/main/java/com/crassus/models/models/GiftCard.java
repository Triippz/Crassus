package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Map;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gift_card")
public class GiftCard extends SoftDeletableEntity {

  @NotNull
  @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
  private String code;

  @NotNull
  @Column(name = "value", nullable = false)
  private Integer value;

  @NotNull
  @Column(name = "balance", nullable = false)
  private Integer balance;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @NotNull
  @Column(name = "is_disabled", nullable = false)
  private Boolean isDisabled = false;

  @Column(name = "ends_at")
  private OffsetDateTime endsAt;

  @NotNull
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @NotNull
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = "deleted_at")
  private OffsetDateTime deletedAt;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @Column(name = "tax_rate")
  private Float taxRate;

  @Override
  protected String getIdPrefix() {
    return "gift";
  }
}
