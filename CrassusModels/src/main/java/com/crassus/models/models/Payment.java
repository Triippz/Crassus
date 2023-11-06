package com.crassus.models.models;

import com.crassus.models.BaseEntity;
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
@Table(name = "payment")
public class Payment extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "swap_id")
  private Swap swap;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @NotNull
  @Column(name = "amount", nullable = false)
  private Integer amount;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "currency_code", nullable = false)
  private Currency currencyCode;

  @NotNull
  @Column(name = "amount_refunded", nullable = false)
  private Integer amountRefunded;

  @NotNull
  @Column(name = "provider_id", nullable = false, length = Integer.MAX_VALUE)
  private String providerId;

  @NotNull
  @Column(name = "data", nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> data;

  @Column(name = "captured_at")
  private OffsetDateTime capturedAt;

  @Column(name = "canceled_at")
  private OffsetDateTime canceledAt;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
  private String idempotencyKey;

  @Override
  protected String getIdPrefix() {
    return "pay";
  }
}
