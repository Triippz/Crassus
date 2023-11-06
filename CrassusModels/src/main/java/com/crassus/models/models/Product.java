package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.ProductStatusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
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
@Table(name = "product")
public class Product extends SoftDeletableEntity {

  @NotNull
  @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
  private String title;

  @Column(name = "subtitle", length = Integer.MAX_VALUE)
  private String subtitle;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @Column(name = "handle", length = Integer.MAX_VALUE)
  private String handle;

  @NotNull
  @Column(name = "is_giftcard", nullable = false)
  private Boolean isGiftcard = false;

  @Column(name = "thumbnail", length = Integer.MAX_VALUE)
  private String thumbnail;

  @Column(name = "weight")
  private Integer weight;

  @Column(name = "length")
  private Integer length;

  @Column(name = "height")
  private Integer height;

  @Column(name = "width")
  private Integer width;

  @Column(name = "hs_code", length = Integer.MAX_VALUE)
  private String hsCode;

  @Column(name = "origin_country", length = Integer.MAX_VALUE)
  private String originCountry;

  @Column(name = "mid_code", length = Integer.MAX_VALUE)
  private String midCode;

  @Column(name = "material", length = Integer.MAX_VALUE)
  private String material;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "collection_id")
  private ProductCollection collection;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_id")
  private ProductType type;

  @NotNull
  @Column(name = "discountable", nullable = false)
  private Boolean discountable = false;

  @NotNull
  @Column(name = "status", nullable = false)
  private ProductStatusType status;

  @Column(name = "external_id", length = Integer.MAX_VALUE)
  private String externalId;

  @Override
  protected String getIdPrefix() {
    return "prod";
  }
}
