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
@Table(name = "product_variant_inventory_item")
public class ProductVariantInventoryItem extends SoftDeletableEntity {
    @NotNull
    @Column(name = "inventory_item_id", nullable = false, length = Integer.MAX_VALUE)
    private String inventoryItemId;

    @NotNull
    @Column(name = "variant_id", nullable = false, length = Integer.MAX_VALUE)
    private String variantId;

    @NotNull
    @Column(name = "required_quantity", nullable = false)
    private Integer requiredQuantity;

    @Override
    protected String getIdPrefix() {
        return "pvitem";
    }
}
