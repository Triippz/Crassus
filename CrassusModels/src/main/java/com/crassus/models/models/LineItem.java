package com.crassus.models.models;

import com.crassus.models.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "line_item")
public class LineItem extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "swap_id")
  private Swap swap;

  @NotNull
  @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
  private String title;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @Column(name = "thumbnail", length = Integer.MAX_VALUE)
  private String thumbnail;

  @NotNull
  @Column(name = "is_giftcard", nullable = false)
  private Boolean isGiftcard = false;

  @NotNull
  @Column(name = "should_merge", nullable = false)
  private Boolean shouldMerge = false;

  @NotNull
  @Column(name = "allow_discounts", nullable = false)
  private Boolean allowDiscounts = false;

  @Column(name = "has_shipping")
  private Boolean hasShipping;

  @NotNull
  @Column(name = "unit_price", nullable = false)
  private Integer unitPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "variant_id")
  private ProductVariant variant;

  @NotNull
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "fulfilled_quantity")
  private Integer fulfilledQuantity;

  @Column(name = "returned_quantity")
  private Integer returnedQuantity;

  @Column(name = "shipped_quantity")
  private Integer shippedQuantity;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "claim_order_id")
  private ClaimOrder claimOrder;

  @NotNull
  @Column(name = "is_return", nullable = false)
  private Boolean isReturn = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "original_item_id")
  private LineItem originalItem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_edit_id")
  private OrderEdit orderEdit;

  @Override
  protected String getIdPrefix() {
    return "item";
  }
}
