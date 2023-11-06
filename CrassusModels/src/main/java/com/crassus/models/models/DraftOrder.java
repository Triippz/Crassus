package com.crassus.models.models;

import com.crassus.models.BaseEntity;
import com.crassus.models.enumerations.DraftOrderStatusType;
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
@Table(name = "draft_order")
public class DraftOrder extends BaseEntity {
    @NotNull
    @Column(name = "status", nullable = false)
    private DraftOrderStatusType status;

    @NotNull
    @Column(name = "display_id", nullable = false)
    private Integer displayId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "canceled_at")
    private OffsetDateTime canceledAt;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @Column(name = "idempotency_key", length = Integer.MAX_VALUE)
    private String idempotencyKey;

    @Column(name = "no_notification_order")
    private Boolean noNotificationOrder;

    @Override
    protected String getIdPrefix() {
        return "dordr";
    }
}
