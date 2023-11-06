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
@Table(name = "region_payment_providers")
public class RegionPaymentProvider {

  @EmbeddedId
  private RegionPaymentProviderId id;

  @MapsId("regionId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;

  @MapsId("providerId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "provider_id", nullable = false)
  private PaymentProvider provider;
}
