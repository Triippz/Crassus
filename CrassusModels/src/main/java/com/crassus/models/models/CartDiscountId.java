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
public class CartDiscountId implements Serializable {
    private static final long serialVersionUID = -1561871482868556327L;
    @NotNull
    @Column(name = "cart_id", nullable = false, length = Integer.MAX_VALUE)
    private String cartId;

    @NotNull
    @Column(name = "discount_id", nullable = false, length = Integer.MAX_VALUE)
    private String discountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartDiscountId entity = (CartDiscountId) o;
        return Objects.equals(this.cartId, entity.cartId) &&
            Objects.equals(this.discountId, entity.discountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, discountId);
    }

}
