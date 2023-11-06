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
public class CustomerGroupCustomerId implements Serializable {

  private static final long serialVersionUID = 282965401272960725L;

  @NotNull
  @Column(name = "customer_group_id", nullable = false, length = Integer.MAX_VALUE)
  private String customerGroupId;

  @NotNull
  @Column(name = "customer_id", nullable = false, length = Integer.MAX_VALUE)
  private String customerId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    CustomerGroupCustomerId entity = (CustomerGroupCustomerId) o;
    return (
      Objects.equals(this.customerGroupId, entity.customerGroupId) &&
      Objects.equals(this.customerId, entity.customerId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerGroupId, customerId);
  }
}
