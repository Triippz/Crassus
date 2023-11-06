package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.DiscountRuleAllocationType;
import com.crassus.models.enumerations.DiscountRuleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "discount_rule")
public class DiscountRule extends SoftDeletableEntity {

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @NotNull
  @Column(name = "type", nullable = false)
  private DiscountRuleType type;

  @NotNull
  @Column(name = "value", nullable = false)
  private Integer value;

  @Column(name = "allocation")
  private DiscountRuleAllocationType allocation;

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

  @Override
  protected String getIdPrefix() {
    return "dru";
  }
}
