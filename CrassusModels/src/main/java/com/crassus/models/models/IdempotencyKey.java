package com.crassus.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "idempotency_key")
public class IdempotencyKey {
    @Id
    @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
    private String id;

    @NotNull
    @Column(name = "idempotency_key", nullable = false, length = Integer.MAX_VALUE)
    private String idempotencyKey;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "locked_at")
    private OffsetDateTime lockedAt;

    @Column(name = "request_method", length = Integer.MAX_VALUE)
    private String requestMethod;

    @Column(name = "request_params")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> requestParams;

    @Column(name = "request_path", length = Integer.MAX_VALUE)
    private String requestPath;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "response_body")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> responseBody;

    @NotNull
    @Column(name = "recovery_point", nullable = false, length = Integer.MAX_VALUE)
    private String recoveryPoint;

}
