package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
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
@Table(name = "region")
public class Region extends SoftDeletableEntity {
    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_code", nullable = false)
    private Currency currencyCode;

    @NotNull
    @Column(name = "tax_rate", nullable = false)
    private Float taxRate;

    @Column(name = "tax_code", length = Integer.MAX_VALUE)
    private String taxCode;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @NotNull
    @Column(name = "gift_cards_taxable", nullable = false)
    private Boolean giftCardsTaxable = false;

    @NotNull
    @Column(name = "automatic_taxes", nullable = false)
    private Boolean automaticTaxes = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_provider_id")
    private TaxProvider taxProvider;

    @Override
    protected String getIdPrefix() {
        return "reg";
    }
}
