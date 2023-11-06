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
public class RegionFulfillmentProviderId implements Serializable {

  private static final long serialVersionUID = -3515211945605998706L;

  @NotNull
  @Column(name = "region_id", nullable = false, length = Integer.MAX_VALUE)
  private String regionId;

  @NotNull
  @Column(name = "provider_id", nullable = false, length = Integer.MAX_VALUE)
  private String providerId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RegionFulfillmentProviderId entity = (RegionFulfillmentProviderId) o;
    return (
      Objects.equals(this.regionId, entity.regionId) &&
      Objects.equals(this.providerId, entity.providerId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(regionId, providerId);
  }
}
