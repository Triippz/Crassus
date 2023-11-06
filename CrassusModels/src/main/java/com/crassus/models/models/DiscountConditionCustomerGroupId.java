package com.crassus.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DiscountConditionCustomerGroupId implements Serializable {
    private static final long serialVersionUID = -1464068217784449915L;
    @NotNull
    @Column(name = "customer_group_id", nullable = false, length = Integer.MAX_VALUE)
    private String customerGroupId;

    @NotNull
    @Column(name = "condition_id", nullable = false, length = Integer.MAX_VALUE)
    private String conditionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiscountConditionCustomerGroupId entity = (DiscountConditionCustomerGroupId) o;
        return Objects.equals(this.customerGroupId, entity.customerGroupId) &&
            Objects.equals(this.conditionId, entity.conditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerGroupId, conditionId);
    }

}
