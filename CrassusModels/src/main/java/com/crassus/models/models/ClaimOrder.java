package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.ClaimOrderFulfillmentStatusType;
import com.crassus.models.enumerations.ClaimOrderPaymentStatusType;
import com.crassus.models.enumerations.ClaimOrderType;
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
@Table(name = "claim_order")
public class ClaimOrder extends SoftDeletableEntity {

  @Id
  @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
  private String id;

  @NotNull
  @Column(name = "payment_status", nullable = false)
  private ClaimOrderPaymentStatusType paymentStatus;

  @NotNull
  @Column(name = "fulfillment_status", nullable = false)
  private ClaimOrderFulfillmentStatusType fulfillmentStatus;

  @NotNull
  @Column(name = "type", nullable = false)
  private ClaimOrderType type;

  @Column(name = "order_id", insertable = false, updatable = false)
  private String orderId;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(name = "shipping_address_id", insertable = false, updatable = false)
  private String shippingAddressId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shipping_address_id")
  private Address shippingAddress;

  @Column(name = "refund_amount")
  private Integer refundAmount;

  @Column(name = "canceled_at")
  private OffsetDateTime canceledAt;

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

  @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
  private String idempotencyKey;

  @Column(name = "no_notification")
  private Boolean noNotification;

  @Override
  protected String getIdPrefix() {
    return "claim";
  }
}
