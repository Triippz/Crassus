package com.crassus.models.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discount_rule_products")
public class DiscountRuleProduct {

  @EmbeddedId
  private DiscountRuleProductId id;

  @MapsId("discountRuleId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "discount_rule_id", nullable = false)
  private DiscountRule discountRule;

  @MapsId("productId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
}
