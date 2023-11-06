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
public class PaymentCollectionSessionId implements Serializable {

  private static final long serialVersionUID = 5867625117756414562L;

  @NotNull
  @Column(name = "payment_collection_id", nullable = false, length = Integer.MAX_VALUE)
  private String paymentCollectionId;

  @NotNull
  @Column(name = "payment_session_id", nullable = false, length = Integer.MAX_VALUE)
  private String paymentSessionId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    PaymentCollectionSessionId entity = (PaymentCollectionSessionId) o;
    return (
      Objects.equals(this.paymentSessionId, entity.paymentSessionId) &&
      Objects.equals(this.paymentCollectionId, entity.paymentCollectionId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentSessionId, paymentCollectionId);
  }
}
