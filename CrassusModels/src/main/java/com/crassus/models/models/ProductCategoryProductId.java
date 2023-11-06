package com.crassus.models.models;

import jakarta.persistence.*;
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
public class ProductCategoryProductId implements Serializable {

  private static final long serialVersionUID = -4485698468488769940L;

  @NotNull
  @Column(name = "product_category_id", nullable = false, length = Integer.MAX_VALUE)
  private String productCategoryId;

  @NotNull
  @Column(name = "product_id", nullable = false, length = Integer.MAX_VALUE)
  private String productId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ProductCategoryProductId entity = (ProductCategoryProductId) o;
    return (
      Objects.equals(this.productCategoryId, entity.productCategoryId) &&
      Objects.equals(this.productId, entity.productId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(productCategoryId, productId);
  }
}
