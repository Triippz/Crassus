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
public class DiscountConditionProductTagId implements Serializable {
    private static final long serialVersionUID = 6917597212100068761L;
    @NotNull
    @Column(name = "product_tag_id", nullable = false, length = Integer.MAX_VALUE)
    private String productTagId;

    @NotNull
    @Column(name = "condition_id", nullable = false, length = Integer.MAX_VALUE)
    private String conditionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiscountConditionProductTagId entity = (DiscountConditionProductTagId) o;
        return Objects.equals(this.productTagId, entity.productTagId) &&
            Objects.equals(this.conditionId, entity.conditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTagId, conditionId);
    }

}
