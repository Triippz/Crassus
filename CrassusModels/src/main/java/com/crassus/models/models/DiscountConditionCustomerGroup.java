package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Map;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discount_condition_customer_group")
public class DiscountConditionCustomerGroup {

  @EmbeddedId
  private DiscountConditionCustomerGroupId id;

  @Column(name = "customer_group_id", insertable = false, updatable = false)
  private String customerGroupId;

  @MapsId("customerGroupId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "customer_group_id", nullable = false)
  private CustomerGroup customerGroup;

  @Column(name = "condition_id", insertable = false, updatable = false)
  private String conditionId;

  @MapsId("conditionId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "condition_id", nullable = false)
  private DiscountCondition condition;

  @NotNull
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @NotNull
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;
}
