package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.SwapFulfillmentStatusType;
import com.crassus.models.enumerations.SwapPaymentStatusType;
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
@Table(name = "swap")
public class Swap extends SoftDeletableEntity {

  @NotNull
  @Column(name = "fulfillment_status", nullable = false)
  private SwapFulfillmentStatusType fulfillmentStatus;

  @NotNull
  @Column(name = "payment_status", nullable = false)
  private SwapPaymentStatusType paymentStatus;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(name = "difference_due")
  private Integer differenceDue;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shipping_address_id")
  private Address shippingAddress;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @Column(name = "confirmed_at")
  private OffsetDateTime confirmedAt;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
  private String idempotencyKey;

  @Column(name = "no_notification")
  private Boolean noNotification;

  @Column(name = "canceled_at")
  private OffsetDateTime canceledAt;

  @NotNull
  @Column(name = "allow_backorder", nullable = false)
  private Boolean allowBackorder = false;

  @Override
  protected String getIdPrefix() {
    return "swap";
  }
}
