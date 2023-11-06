package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "invite")
public class Invite extends SoftDeletableEntity {

  @NotNull
  @Column(name = "user_email", nullable = false, length = Integer.MAX_VALUE)
  private String userEmail;

  @NotNull
  @Column(name = "accepted", nullable = false)
  private Boolean accepted = false;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @NotNull
  @Column(name = "token", nullable = false, length = Integer.MAX_VALUE)
  private String token;

  @NotNull
  @Column(name = "expires_at", nullable = false)
  private OffsetDateTime expiresAt;

  @Override
  protected String getIdPrefix() {
    return "invite";
  }
  /*
    TODO [JPA Buddy] create field to map the 'role' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "role", columnDefinition = "invite_role_type(0, 0)")
    private Object role;
*/
}
