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
public class PublishableApiKeySalesChannelId implements Serializable {
    private static final long serialVersionUID = 7346872153167033862L;
    @NotNull
    @Column(name = "sales_channel_id", nullable = false, length = Integer.MAX_VALUE)
    private String salesChannelId;

    @NotNull
    @Column(name = "publishable_key_id", nullable = false, length = Integer.MAX_VALUE)
    private String publishableKeyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PublishableApiKeySalesChannelId entity = (PublishableApiKeySalesChannelId) o;
        return Objects.equals(this.publishableKeyId, entity.publishableKeyId) &&
            Objects.equals(this.salesChannelId, entity.salesChannelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publishableKeyId, salesChannelId);
    }

}
