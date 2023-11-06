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
@Table(name = "price_list_customer_groups")
public class PriceListCustomerGroup {

  @EmbeddedId
  private PriceListCustomerGroupId id;

  @MapsId("priceListId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "price_list_id", nullable = false)
  private PriceList priceList;

  @MapsId("customerGroupId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "customer_group_id", nullable = false)
  private CustomerGroup customerGroup;
}
