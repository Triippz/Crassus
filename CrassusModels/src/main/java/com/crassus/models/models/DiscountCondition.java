package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.DiscountConditionOperatorType;
import com.crassus.models.enumerations.DiscountConditionType;
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
@Table(name = "discount_condition")
public class DiscountCondition extends SoftDeletableEntity {
    @NotNull
    @Column(name = "type", nullable = false)
    private DiscountConditionType type;

    @NotNull
    @Column(name = "operator", nullable = false)
    private DiscountConditionOperatorType operator;

    @Column(name = "discount_rule_id", insertable = false, updatable = false)
    private String discountRuleId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discount_rule_id", nullable = false)
    private DiscountRule discountRule;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @Override
    protected String getIdPrefix() {
        return "discon";
    }
}
