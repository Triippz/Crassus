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
public class ClaimItemTagId implements Serializable {

  private static final long serialVersionUID = 1925615666134840111L;

  @NotNull
  @Column(name = "item_id", nullable = false, length = Integer.MAX_VALUE)
  private String itemId;

  @NotNull
  @Column(name = "tag_id", nullable = false, length = Integer.MAX_VALUE)
  private String tagId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ClaimItemTagId entity = (ClaimItemTagId) o;
    return (
      Objects.equals(this.itemId, entity.itemId) &&
      Objects.equals(this.tagId, entity.tagId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, tagId);
  }
}
