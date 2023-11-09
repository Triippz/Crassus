package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.BaseEntity;
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
@Table(name = "store")
public class Store extends BaseEntity {

  @NotNull
  @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
  private String name;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "default_currency_code", nullable = false)
  private Currency defaultCurrencyCode;

  @Column(name = "swap_link_template", length = Integer.MAX_VALUE)
  private String swapLinkTemplate;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @Column(name = "payment_link_template", length = Integer.MAX_VALUE)
  private String paymentLinkTemplate;

  @Column(name = "invite_link_template", length = Integer.MAX_VALUE)
  private String inviteLinkTemplate;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "default_sales_channel_id")
  private SalesChannel defaultSalesChannel;

  @Column(name = "default_location_id", length = Integer.MAX_VALUE)
  private String defaultLocationId;

  @Override
  protected String getIdPrefix() {
    return "store";
  }
}
