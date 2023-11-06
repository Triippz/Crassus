package com.crassus.models.models;

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
@Table(name = "shipping_method")
public class ShippingMethod {

  @Id
  @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
  private String id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "shipping_option_id", nullable = false)
  private ShippingOption shippingOption;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "swap_id")
  private Swap swap;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "return_id")
  private Return returnField;

  @NotNull
  @Column(name = "price", nullable = false)
  private Integer price;

  @NotNull
  @Column(name = "data", nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> data;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "claim_order_id")
  private ClaimOrder claimOrder;
}
