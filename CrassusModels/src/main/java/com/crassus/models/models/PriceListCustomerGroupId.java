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
public class PriceListCustomerGroupId implements Serializable {
    private static final long serialVersionUID = -4485698468488766940L;
    @NotNull
    @Column(name = "price_list_id", nullable = false, length = Integer.MAX_VALUE)
    private String priceListId;

    @NotNull
    @Column(name = "customer_group_id", nullable = false, length = Integer.MAX_VALUE)
    private String customerGroupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PriceListCustomerGroupId entity = (PriceListCustomerGroupId) o;
        return Objects.equals(this.customerGroupId, entity.customerGroupId) &&
            Objects.equals(this.priceListId, entity.priceListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerGroupId, priceListId);
    }

}
