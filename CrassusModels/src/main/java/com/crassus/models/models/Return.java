package com.crassus.models.models;

import com.crassus.models.BaseEntity;
import com.crassus.models.enumerations.ReturnStatusType;
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
@Table(name = "return")
public class Return extends BaseEntity {
    @NotNull
    @Column(name = "status", nullable = false)
    private ReturnStatusType status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "swap_id")
    private Swap swap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "shipping_data")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> shippingData;

    @NotNull
    @Column(name = "refund_amount", nullable = false)
    private Integer refundAmount;

    @Column(name = "received_at")
    private OffsetDateTime receivedAt;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
    private String idempotencyKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_order_id")
    private ClaimOrder claimOrder;

    @Column(name = "no_notification")
    private Boolean noNotification;

    @Column(name = "location_id", length = Integer.MAX_VALUE)
    private String locationId;

    @Override
    protected String getIdPrefix() {
        return "ret";
    }
}
