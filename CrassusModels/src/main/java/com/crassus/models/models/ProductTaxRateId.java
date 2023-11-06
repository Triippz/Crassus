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
public class ProductTaxRateId implements Serializable {

  private static final long serialVersionUID = -7960174300941227441L;

  @NotNull
  @Column(name = "product_id", nullable = false, length = Integer.MAX_VALUE)
  private String productId;

  @NotNull
  @Column(name = "rate_id", nullable = false, length = Integer.MAX_VALUE)
  private String rateId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ProductTaxRateId entity = (ProductTaxRateId) o;
    return (
      Objects.equals(this.productId, entity.productId) &&
      Objects.equals(this.rateId, entity.rateId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, rateId);
  }
}
