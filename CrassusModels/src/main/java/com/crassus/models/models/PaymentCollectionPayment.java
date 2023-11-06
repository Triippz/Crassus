package com.crassus.models.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_collection_payments")
public class PaymentCollectionPayment {

  @EmbeddedId
  private PaymentCollectionPaymentId id;

  @MapsId("paymentCollectionId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "payment_collection_id", nullable = false)
  private PaymentCollection paymentCollection;

  @MapsId("paymentId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "payment_id", nullable = false)
  private Payment payment;
}
