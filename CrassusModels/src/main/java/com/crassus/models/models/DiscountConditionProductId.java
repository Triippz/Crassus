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
public class DiscountConditionProductId implements Serializable {

  private static final long serialVersionUID = -6520335391153819192L;

  @NotNull
  @Column(name = "product_id", nullable = false, length = Integer.MAX_VALUE)
  private String productId;

  @NotNull
  @Column(name = "condition_id", nullable = false, length = Integer.MAX_VALUE)
  private String conditionId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    DiscountConditionProductId entity = (DiscountConditionProductId) o;
    return (
      Objects.equals(this.productId, entity.productId) &&
      Objects.equals(this.conditionId, entity.conditionId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, conditionId);
  }
}
