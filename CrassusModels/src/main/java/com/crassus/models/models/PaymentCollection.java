package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import com.crassus.models.enumerations.PaymentCollectionStatusType;
import com.crassus.models.enumerations.PaymentCollectionType;
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
@Table(name = "payment_collection")
public class PaymentCollection extends SoftDeletableEntity {

  @NotNull
  @Column(name = "type", nullable = false)
  private PaymentCollectionType type;

  @NotNull
  @Column(name = "status", nullable = false)
  private PaymentCollectionStatusType status;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @NotNull
  @Column(name = "amount", nullable = false)
  private Integer amount;

  @Column(name = "authorized_amount")
  private Integer authorizedAmount;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;

  @NotNull
  @Column(name = "currency_code", nullable = false, length = Integer.MAX_VALUE)
  private String currencyCode;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @NotNull
  @Column(name = "created_by", nullable = false, length = Integer.MAX_VALUE)
  private String createdBy;

  @Override
  protected String getIdPrefix() {
    return "paycol";
  }
}
