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
public class ProductTypeTaxRateId implements Serializable {

  private static final long serialVersionUID = -3070515790148172170L;

  @NotNull
  @Column(name = "product_type_id", nullable = false, length = Integer.MAX_VALUE)
  private String productTypeId;

  @NotNull
  @Column(name = "rate_id", nullable = false, length = Integer.MAX_VALUE)
  private String rateId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ProductTypeTaxRateId entity = (ProductTypeTaxRateId) o;
    return (
      Objects.equals(this.productTypeId, entity.productTypeId) &&
      Objects.equals(this.rateId, entity.rateId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(productTypeId, rateId);
  }
}
