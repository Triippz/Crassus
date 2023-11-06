package com.crassus.models.models;

import com.crassus.models.BaseEntity;
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
@Table(name = "fulfillment")
public class Fulfillment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "swap_id")
    private Swap swap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @Column(name = "tracking_numbers", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> trackingNumbers;

    @NotNull
    @Column(name = "data", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> data;

    @Column(name = "shipped_at")
    private OffsetDateTime shippedAt;

    @Column(name = "canceled_at")
    private OffsetDateTime canceledAt;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
    private String idempotencyKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private FulfillmentProvider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_order_id")
    private ClaimOrder claimOrder;

    @Column(name = "no_notification")
    private Boolean noNotification;

    @Column(name = "location_id", length = Integer.MAX_VALUE)
    private String locationId;

    @Override
    protected String getIdPrefix() {
        return "ful";
    }
}
