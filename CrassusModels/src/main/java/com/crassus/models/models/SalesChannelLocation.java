package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_channel_location")
public class SalesChannelLocation extends SoftDeletableEntity {

  @NotNull
  @Column(name = "sales_channel_id", nullable = false, length = Integer.MAX_VALUE)
  private String salesChannelId;

  @NotNull
  @Column(name = "location_id", nullable = false, length = Integer.MAX_VALUE)
  private String locationId;

  @Override
  protected String getIdPrefix() {
    return "scloc";
  }
}
