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
public class DiscountRuleProductId implements Serializable {
    private static final long serialVersionUID = -5531229941172436123L;
    @NotNull
    @Column(name = "discount_rule_id", nullable = false, length = Integer.MAX_VALUE)
    private String discountRuleId;

    @NotNull
    @Column(name = "product_id", nullable = false, length = Integer.MAX_VALUE)
    private String productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiscountRuleProductId entity = (DiscountRuleProductId) o;
        return Objects.equals(this.productId, entity.productId) &&
            Objects.equals(this.discountRuleId, entity.discountRuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, discountRuleId);
    }

}
