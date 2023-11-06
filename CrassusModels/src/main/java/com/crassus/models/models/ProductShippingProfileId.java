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
public class ProductShippingProfileId implements Serializable {

    private static final long serialVersionUID = -386387061985237092L;

    @NotNull
    @Column(name = "profile_id", nullable = false, length = Integer.MAX_VALUE)
    private String profileId;

    @NotNull
    @Column(name = "product_id", nullable = false, length = Integer.MAX_VALUE)
    private String productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductShippingProfileId entity = (ProductShippingProfileId) o;
        return Objects.equals(this.profileId, entity.profileId) &&
            Objects.equals(this.productId, entity.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, productId);
    }
}
