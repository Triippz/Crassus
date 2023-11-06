package com.crassus.models.models;

import com.crassus.models.BaseEntity;
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
@Table(name = "product_category")
public class ProductCategory extends BaseEntity {

  @NotNull
  @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
  private String name;

  @NotNull
  @Column(name = "handle", nullable = false, length = Integer.MAX_VALUE)
  private String handle;

  @Column(name = "parent_category_id", length = Integer.MAX_VALUE)
  private String parentCategoryId;

  @Column(name = "mpath", length = Integer.MAX_VALUE)
  private String mpath;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "is_internal")
  private Boolean isInternal;

  @NotNull
  @Column(name = "rank", nullable = false)
  private Integer rank;

  @NotNull
  @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
  private String description;

  @Override
  protected String getIdPrefix() {
    return "pact";
  }
}
