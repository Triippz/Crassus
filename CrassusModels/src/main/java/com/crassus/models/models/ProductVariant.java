package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variant")
public class ProductVariant extends SoftDeletableEntity {

  @NotNull
  @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
  private String title;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "sku", length = Integer.MAX_VALUE)
  private String sku;

  @Column(name = "barcode", length = Integer.MAX_VALUE)
  private String barcode;

  @Column(name = "ean", length = Integer.MAX_VALUE)
  private String ean;

  @Column(name = "upc", length = Integer.MAX_VALUE)
  private String upc;

  @NotNull
  @Column(name = "inventory_quantity", nullable = false)
  private Integer inventoryQuantity;

  @NotNull
  @Column(name = "allow_backorder", nullable = false)
  private Boolean allowBackorder = false;

  @NotNull
  @Column(name = "manage_inventory", nullable = false)
  private Boolean manageInventory = false;

  @Column(name = "hs_code", length = Integer.MAX_VALUE)
  private String hsCode;

  @Column(name = "origin_country", length = Integer.MAX_VALUE)
  private String originCountry;

  @Column(name = "mid_code", length = Integer.MAX_VALUE)
  private String midCode;

  @Column(name = "material", length = Integer.MAX_VALUE)
  private String material;

  @Column(name = "weight")
  private Integer weight;

  @Column(name = "length")
  private Integer length;

  @Column(name = "height")
  private Integer height;

  @Column(name = "width")
  private Integer width;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @Column(name = "variant_rank")
  private Integer variantRank;

  @Override
  protected String getIdPrefix() {
    return "variant";
  }
}
