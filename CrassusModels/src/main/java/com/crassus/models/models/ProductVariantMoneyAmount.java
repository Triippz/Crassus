package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variant_money_amount")
public class ProductVariantMoneyAmount extends SoftDeletableEntity {

    @NotNull
    @Column(name = "money_amount_id", nullable = false, length = Integer.MAX_VALUE)
    private String moneyAmountId;

    @NotNull
    @Column(name = "variant_id", nullable = false, length = Integer.MAX_VALUE)
    private String variantId;


    @Override
    protected String getIdPrefix() {
        return "pyma_";
    }
}
