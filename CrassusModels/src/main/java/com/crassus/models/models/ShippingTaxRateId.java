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
public class ShippingTaxRateId implements Serializable {

  private static final long serialVersionUID = -548607337678111913L;

  @NotNull
  @Column(name = "shipping_option_id", nullable = false, length = Integer.MAX_VALUE)
  private String shippingOptionId;

  @NotNull
  @Column(name = "rate_id", nullable = false, length = Integer.MAX_VALUE)
  private String rateId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ShippingTaxRateId entity = (ShippingTaxRateId) o;
    return (
      Objects.equals(this.shippingOptionId, entity.shippingOptionId) &&
      Objects.equals(this.rateId, entity.rateId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(shippingOptionId, rateId);
  }
}
