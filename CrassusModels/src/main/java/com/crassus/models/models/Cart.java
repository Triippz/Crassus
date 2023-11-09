package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.CartType;
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
@Table(name = "cart")
public class Cart extends SoftDeletableEntity {

  @Column(name = "email", length = Integer.MAX_VALUE)
  private String email;

  @Column(name = "billing_address_id", insertable = false, updatable = false)
  private String billingAddressId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "billing_address_id")
  private Address billingAddress;

  @Column(name = "shipping_address_id", insertable = false, updatable = false)
  private String shippingAddressId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shipping_address_id")
  private Address shippingAddress;

  @Column(name = "region_id", insertable = false, updatable = false)
  private String regionId;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;

  @Column(name = "user_id", insertable = false, updatable = false)
  private String customerId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User customer;

  @Column(name = "payment_id", insertable = false, updatable = false)
  private String paymentId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_id")
  private Payment payment;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private CartType type;

  @Column(name = "completed_at")
  private OffsetDateTime completedAt;

  @NotNull
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @NotNull
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = "deleted_at")
  private OffsetDateTime deletedAt;

  @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
  private String idempotencyKey;

  @Column(name = "context")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> context;

  @Column(name = "payment_authorized_at")
  private OffsetDateTime paymentAuthorizedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sales_channel_id")
  private SalesChannel salesChannel;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @Override
  protected String getIdPrefix() {
    return "cart";
  }
}
