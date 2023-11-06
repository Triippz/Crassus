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
public class ReturnItemId implements Serializable {

  private static final long serialVersionUID = 8132032997064448703L;

  @NotNull
  @Column(name = "return_id", nullable = false, length = Integer.MAX_VALUE)
  private String returnId;

  @NotNull
  @Column(name = "item_id", nullable = false, length = Integer.MAX_VALUE)
  private String itemId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ReturnItemId entity = (ReturnItemId) o;
    return (
      Objects.equals(this.itemId, entity.itemId) &&
      Objects.equals(this.returnId, entity.returnId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, returnId);
  }
}
