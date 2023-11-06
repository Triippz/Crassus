package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
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
@Table(name = "users")
public class User extends SoftDeletableEntity {

  @NotNull
  @Column(name = "login", nullable = false, length = Integer.MAX_VALUE)
  private String login;

  @Column(name = "first_name", length = Integer.MAX_VALUE)
  private String firstName;

  @Column(name = "last_name", length = Integer.MAX_VALUE)
  private String lastName;

  @Column(name = "email", length = Integer.MAX_VALUE)
  private String email;

  @NotNull
  @Column(name = "activated", nullable = false)
  private Boolean activated = false;

  @Column(name = "lang_key", length = Integer.MAX_VALUE)
  private String langKey;

  @Column(name = "last_modified_date")
  private Instant lastModifiedDate;

  @Column(name = "meta_data")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metaData;

  @Override
  protected String getIdPrefix() {
    return "usr";
  }
}
