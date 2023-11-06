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
public class DiscountRegionId implements Serializable {
    private static final long serialVersionUID = -4728672676193637284L;
    @NotNull
    @Column(name = "discount_id", nullable = false, length = Integer.MAX_VALUE)
    private String discountId;

    @NotNull
    @Column(name = "region_id", nullable = false, length = Integer.MAX_VALUE)
    private String regionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiscountRegionId entity = (DiscountRegionId) o;
        return Objects.equals(this.regionId, entity.regionId) &&
            Objects.equals(this.discountId, entity.discountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId, discountId);
    }

}
