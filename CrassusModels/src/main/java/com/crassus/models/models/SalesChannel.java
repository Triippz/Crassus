package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "sales_channel")
public class SalesChannel extends SoftDeletableEntity {

  @NotNull
  @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
  private String name;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @NotNull
  @Column(name = "is_disabled", nullable = false)
  private Boolean isDisabled = false;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @Override
  protected String getIdPrefix() {
    return "sc";
  }
}
