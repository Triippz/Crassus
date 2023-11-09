package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.ClaimItemReasonType;
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
@Table(name = "claim_item")
public class ClaimItem extends SoftDeletableEntity {

  @Column(name = "claim_order_id", insertable = false, updatable = false)
  private String claimOrderId;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "claim_order_id", nullable = false)
  private ClaimOrder claimOrder;

  @Column(name = "item_id", insertable = false, updatable = false)
  private String itemId;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "item_id", nullable = false)
  private LineItem item;

  @Column(name = "variant_id", insertable = false, updatable = false)
  private String variantId;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "variant_id", nullable = false)
  private ProductVariant variant;

  @NotNull
  @Column(name = "reason", nullable = false)
  private ClaimItemReasonType reason;

  @Column(name = "note", length = Integer.MAX_VALUE)
  private String note;

  @NotNull
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

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

  @Override
  protected String getIdPrefix() {
    return "citm";
  }
}
