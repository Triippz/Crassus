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
@Table(name = "return_reason")
public class ReturnReason extends SoftDeletableEntity {

  @NotNull
  @Column(name = "value", nullable = false, length = Integer.MAX_VALUE)
  private String value;

  @NotNull
  @Column(name = "label", nullable = false, length = Integer.MAX_VALUE)
  private String label;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_return_reason_id")
  private ReturnReason parentReturnReason;

  @Override
  protected String getIdPrefix() {
    return "rr";
  }
}
