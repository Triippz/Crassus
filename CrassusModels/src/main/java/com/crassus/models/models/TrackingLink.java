package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracking_link")
public class TrackingLink extends SoftDeletableEntity {
    @Column(name = "url", length = Integer.MAX_VALUE)
    private String url;

    @NotNull
    @Column(name = "tracking_number", nullable = false, length = Integer.MAX_VALUE)
    private String trackingNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fulfillment_id", nullable = false)
    private Fulfillment fulfillment;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
    private String idempotencyKey;

    @Override
    protected String getIdPrefix() {
        return "tlink";
    }
}
