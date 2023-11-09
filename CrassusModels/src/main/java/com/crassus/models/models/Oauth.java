package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
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
@Table(name = "oauth")
public class Oauth {

  @Id
  @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
  private String id;

  @NotNull
  @Column(name = "display_name", nullable = false, length = Integer.MAX_VALUE)
  private String displayName;

  @NotNull
  @Column(name = "application_name", nullable = false, length = Integer.MAX_VALUE)
  private String applicationName;

  @Column(name = "install_url", length = Integer.MAX_VALUE)
  private String installUrl;

  @Column(name = "uninstall_url", length = Integer.MAX_VALUE)
  private String uninstallUrl;

  @Column(name = "data")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> data;
}
