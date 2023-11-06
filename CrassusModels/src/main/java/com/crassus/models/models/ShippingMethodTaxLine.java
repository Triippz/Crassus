package com.crassus.models.models;

import com.crassus.models.TaxLine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_method_tax_line")
public class ShippingMethodTaxLine extends TaxLine {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipping_method_id", nullable = false)
    private ShippingMethod shippingMethod;

    @Override
    protected String getIdPrefix() {
        return "smtl";
    }
}
