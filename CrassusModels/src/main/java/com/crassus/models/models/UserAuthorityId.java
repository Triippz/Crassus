package com.crassus.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import lombok.*;
import org.hibernate.Hibernate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserAuthorityId implements Serializable {

  private static final long serialVersionUID = 8959665724461082415L;

  @NotNull
  @Column(name = "user_id", nullable = false, length = Integer.MAX_VALUE)
  private String userId;

  @NotNull
  @Column(name = "authority_name", nullable = false, length = Integer.MAX_VALUE)
  private String authorityName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserAuthorityId entity = (UserAuthorityId) o;
    return (
      Objects.equals(this.authorityName, entity.authorityName) &&
      Objects.equals(this.userId, entity.userId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorityName, userId);
  }
}
