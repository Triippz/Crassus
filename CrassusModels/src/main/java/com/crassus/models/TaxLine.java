package com.crassus.models;

import com.crassus.core.converters.JsonToMapConverter;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class TaxLine extends BaseEntity {

  @Column(nullable = false)
  private Float rate; // 'real' in PostgreSQL is mapped to Java's Float

  @Column(nullable = false)
  private String name;

  @Column
  private String code;

  @Type(JsonBinaryType.class)
  @Column(columnDefinition = "jsonb")
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;
}
