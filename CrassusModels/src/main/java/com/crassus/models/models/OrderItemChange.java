package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.OrderItemChangeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item_change")
public class OrderItemChange extends SoftDeletableEntity {

  @NotNull
  @Column(name = "type", nullable = false)
  private OrderItemChangeType type;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_edit_id", nullable = false)
  private OrderEdit orderEdit;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "original_line_item_id")
  private LineItem originalLineItem;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "line_item_id")
  private LineItem lineItem;

  @Override
  protected String getIdPrefix() {
    return "oic";
  }
}
