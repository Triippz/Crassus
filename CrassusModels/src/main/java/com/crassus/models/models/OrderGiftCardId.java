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
public class OrderGiftCardId implements Serializable {

  private static final long serialVersionUID = 6607314500875322256L;

  @NotNull
  @Column(name = "order_id", nullable = false, length = Integer.MAX_VALUE)
  private String orderId;

  @NotNull
  @Column(name = "gift_card_id", nullable = false, length = Integer.MAX_VALUE)
  private String giftCardId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    OrderGiftCardId entity = (OrderGiftCardId) o;
    return (
      Objects.equals(this.giftCardId, entity.giftCardId) &&
      Objects.equals(this.orderId, entity.orderId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(giftCardId, orderId);
  }
}
