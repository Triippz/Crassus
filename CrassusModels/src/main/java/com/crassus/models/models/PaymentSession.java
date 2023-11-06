package com.crassus.models.models;

import com.crassus.models.BaseEntity;
import com.crassus.models.enumerations.PaymentSessionStatusType;
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
@Table(name = "payment_session")
public class PaymentSession extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @NotNull
  @Column(name = "provider_id", nullable = false, length = Integer.MAX_VALUE)
  private String providerId;

  @Column(name = "is_selected")
  private Boolean isSelected;

  @NotNull
  @Column(name = "status", nullable = false)
  private PaymentSessionStatusType status;

  @NotNull
  @Column(name = "data", nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> data;

  @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
  private String idempotencyKey;

  @Column(name = "payment_authorized_at")
  private OffsetDateTime paymentAuthorizedAt;

  @Column(name = "amount")
  private Integer amount;

  @NotNull
  @Column(name = "is_initiated", nullable = false)
  private Boolean isInitiated = false;

  @Override
  protected String getIdPrefix() {
    return "ps";
  }
}
