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
public class DiscountConditionProductTypeId implements Serializable {

  private static final long serialVersionUID = -3584218781483322623L;

  @NotNull
  @Column(name = "product_type_id", nullable = false, length = Integer.MAX_VALUE)
  private String productTypeId;

  @NotNull
  @Column(name = "condition_id", nullable = false, length = Integer.MAX_VALUE)
  private String conditionId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    DiscountConditionProductTypeId entity = (DiscountConditionProductTypeId) o;
    return (
      Objects.equals(this.conditionId, entity.conditionId) &&
      Objects.equals(this.productTypeId, entity.productTypeId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(conditionId, productTypeId);
  }
}
