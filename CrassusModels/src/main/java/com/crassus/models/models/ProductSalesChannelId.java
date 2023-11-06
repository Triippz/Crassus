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
public class ProductSalesChannelId implements Serializable {
    private static final long serialVersionUID = -386387061985237092L;
    @NotNull
    @Column(name = "product_id", nullable = false, length = Integer.MAX_VALUE)
    private String productId;

    @NotNull
    @Column(name = "sales_channel_id", nullable = false, length = Integer.MAX_VALUE)
    private String salesChannelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductSalesChannelId entity = (ProductSalesChannelId) o;
        return Objects.equals(this.salesChannelId, entity.salesChannelId) &&
            Objects.equals(this.productId, entity.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesChannelId, productId);
    }

}
