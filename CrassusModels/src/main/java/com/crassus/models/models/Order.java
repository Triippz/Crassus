package com.crassus.models.models;

import com.crassus.models.BaseEntity;
import com.crassus.models.enumerations.OrderFulfillmentStatusType;
import com.crassus.models.enumerations.OrderPaymentStatusType;
import com.crassus.models.enumerations.OrderStatusType;
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
@Table(name = "\"order\"")
public class Order extends BaseEntity {

  @NotNull
  @Column(name = "status", nullable = false)
  private OrderStatusType status;

  @NotNull
  @Column(name = "fulfillment_status", nullable = false)
  private OrderFulfillmentStatusType fulfillmentStatus;

  @NotNull
  @Column(name = "payment_status", nullable = false)
  private OrderPaymentStatusType paymentStatus;

  @NotNull
  @Column(name = "display_id", nullable = false)
  private Integer displayId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @NotNull
  @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
  private String email;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "billing_address_id")
  private Address billingAddress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shipping_address_id")
  private Address shippingAddress;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "currency_code", nullable = false)
  private Currency currencyCode;

  @Column(name = "tax_rate")
  private Float taxRate;

  @Column(name = "canceled_at")
  private OffsetDateTime canceledAt;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
  private String idempotencyKey;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "draft_order_id")
  private DraftOrder draftOrder;

  @Column(name = "no_notification")
  private Boolean noNotification;

  @Column(name = "external_id", length = Integer.MAX_VALUE)
  private String externalId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sales_channel_id")
  private SalesChannel salesChannel;

  @Override
  protected String getIdPrefix() {
    return "order";
  }
}
