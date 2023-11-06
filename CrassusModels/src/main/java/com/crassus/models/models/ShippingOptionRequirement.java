package com.crassus.models.models;

import com.crassus.models.enumerations.ShippingOptionRequirementType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_option_requirement")
public class ShippingOptionRequirement {

  @Id
  @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
  private String id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "shipping_option_id", nullable = false)
  private ShippingOption shippingOption;

  @NotNull
  @Column(name = "type", nullable = false)
  private ShippingOptionRequirementType type;

  @NotNull
  @Column(name = "amount", nullable = false)
  private Integer amount;

  @Column(name = "deleted_at")
  private OffsetDateTime deletedAt;
}
