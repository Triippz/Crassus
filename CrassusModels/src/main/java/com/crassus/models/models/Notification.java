package com.crassus.models.models;

import com.crassus.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {
    @Column(name = "event_name", length = Integer.MAX_VALUE)
    private String eventName;

    @NotNull
    @Column(name = "resource_type", nullable = false, length = Integer.MAX_VALUE)
    private String resourceType;

    @NotNull
    @Column(name = "resource_id", nullable = false, length = Integer.MAX_VALUE)
    private String resourceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @Column(name = "\"to\"", nullable = false, length = Integer.MAX_VALUE)
    private String to;

    @NotNull
    @Column(name = "data", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Notification parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private NotificationProvider provider;

    @Override
    protected String getIdPrefix() {
        return "noti";
    }
}
