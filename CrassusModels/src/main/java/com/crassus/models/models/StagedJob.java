package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.BaseIdEntity;
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
@Table(name = "staged_job")
public class StagedJob extends BaseIdEntity {

  @NotNull
  @Column(name = "event_name", nullable = false, length = Integer.MAX_VALUE)
  private String eventName;

  @NotNull
  @Column(name = "data", nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> data;

  @NotNull
  @Column(name = "options", nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> options;

  @Override
  protected String getIdPrefix() {
    return "job";
  }
}
