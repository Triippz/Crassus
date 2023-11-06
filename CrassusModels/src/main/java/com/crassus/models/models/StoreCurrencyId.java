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
public class StoreCurrencyId implements Serializable {
    private static final long serialVersionUID = -1669640461555950350L;
    @NotNull
    @Column(name = "store_id", nullable = false, length = Integer.MAX_VALUE)
    private String storeId;

    @NotNull
    @Column(name = "currency_code", nullable = false, length = Integer.MAX_VALUE)
    private String currencyCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StoreCurrencyId entity = (StoreCurrencyId) o;
        return Objects.equals(this.storeId, entity.storeId) &&
            Objects.equals(this.currencyCode, entity.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, currencyCode);
    }

}
