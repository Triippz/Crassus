package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
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
@Table(name = "discount")
public class Discount extends SoftDeletableEntity {

  @Id
  @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
  private String id;

  @NotNull
  @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
  private String code;

  @NotNull
  @Column(name = "is_dynamic", nullable = false)
  private Boolean isDynamic = false;

  @Column(name = "rule_id", insertable = false, updatable = false)
  private String ruleId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rule_id")
  private DiscountRule rule;

  @NotNull
  @Column(name = "is_disabled", nullable = false)
  private Boolean isDisabled = false;

  @Column(name = "parent_discount_id", insertable = false, updatable = false)
  private String parentDiscountId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_discount_id")
  private Discount parentDiscount;

  @NotNull
  @Column(name = "starts_at", nullable = false)
  private OffsetDateTime startsAt;

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
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @Column(name = "usage_limit")
  private Integer usageLimit;

  @NotNull
  @Column(name = "usage_count", nullable = false)
  private Integer usageCount;

  @Column(name = "valid_duration", length = Integer.MAX_VALUE)
  private String validDuration;

  @Override
  protected String getIdPrefix() {
    return "disc";
  }
}
