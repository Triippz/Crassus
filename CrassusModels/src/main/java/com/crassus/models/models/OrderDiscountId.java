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
public class OrderDiscountId implements Serializable {

  private static final long serialVersionUID = 2250192927004710586L;

  @NotNull
  @Column(name = "order_id", nullable = false, length = Integer.MAX_VALUE)
  private String orderId;

  @NotNull
  @Column(name = "discount_id", nullable = false, length = Integer.MAX_VALUE)
  private String discountId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    OrderDiscountId entity = (OrderDiscountId) o;
    return (
      Objects.equals(this.orderId, entity.orderId) &&
      Objects.equals(this.discountId, entity.discountId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, discountId);
  }
}
