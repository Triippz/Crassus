package com.crassus.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import lombok.*;
import org.hibernate.Hibernate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PaymentCollectionPaymentId implements Serializable {

  private static final long serialVersionUID = -1482029322253930243L;

  @NotNull
  @Column(name = "payment_collection_id", nullable = false, length = Integer.MAX_VALUE)
  private String paymentCollectionId;

  @NotNull
  @Column(name = "payment_id", nullable = false, length = Integer.MAX_VALUE)
  private String paymentId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    PaymentCollectionPaymentId entity = (PaymentCollectionPaymentId) o;
    return (
      Objects.equals(this.paymentId, entity.paymentId) &&
      Objects.equals(this.paymentCollectionId, entity.paymentCollectionId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentId, paymentCollectionId);
  }
}
