package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "note")
public class Note extends SoftDeletableEntity {

  @NotNull
  @Column(name = "value", nullable = false, length = Integer.MAX_VALUE)
  private String value;

  @NotNull
  @Column(name = "resource_type", nullable = false, length = Integer.MAX_VALUE)
  private String resourceType;

  @NotNull
  @Column(name = "resource_id", nullable = false, length = Integer.MAX_VALUE)
  private String resourceId;

  @Column(name = "author_id", length = Integer.MAX_VALUE)
  private String authorId;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @Override
  protected String getIdPrefix() {
    return "note";
  }
}
