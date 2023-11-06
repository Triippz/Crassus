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
public class FulfillmentItemId implements Serializable {
    private static final long serialVersionUID = -7065916475689396868L;
    @NotNull
    @Column(name = "fulfillment_id", nullable = false, length = Integer.MAX_VALUE)
    private String fulfillmentId;

    @NotNull
    @Column(name = "item_id", nullable = false, length = Integer.MAX_VALUE)
    private String itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FulfillmentItemId entity = (FulfillmentItemId) o;
        return Objects.equals(this.fulfillmentId, entity.fulfillmentId) &&
            Objects.equals(this.itemId, entity.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fulfillmentId, itemId);
    }

}
