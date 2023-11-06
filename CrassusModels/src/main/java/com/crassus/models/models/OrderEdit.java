package com.crassus.models.models;

import com.crassus.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_edit")
public class OrderEdit extends BaseEntity {

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(name = "internal_note", length = Integer.MAX_VALUE)
  private String internalNote;

  @NotNull
  @Column(name = "created_by", nullable = false, length = Integer.MAX_VALUE)
  private String createdBy;

  @Column(name = "requested_by", length = Integer.MAX_VALUE)
  private String requestedBy;

  @Column(name = "requested_at")
  private OffsetDateTime requestedAt;

  @Column(name = "confirmed_by", length = Integer.MAX_VALUE)
  private String confirmedBy;

  @Column(name = "confirmed_at")
  private OffsetDateTime confirmedAt;

  @Column(name = "declined_by", length = Integer.MAX_VALUE)
  private String declinedBy;

  @Column(name = "declined_reason", length = Integer.MAX_VALUE)
  private String declinedReason;

  @Column(name = "declined_at")
  private OffsetDateTime declinedAt;

  @Column(name = "canceled_by", length = Integer.MAX_VALUE)
  private String canceledBy;

  @Column(name = "canceled_at")
  private OffsetDateTime canceledAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_collection_id")
  private PaymentCollection paymentCollection;

  @Override
  protected String getIdPrefix() {
    return "oe";
  }
}
