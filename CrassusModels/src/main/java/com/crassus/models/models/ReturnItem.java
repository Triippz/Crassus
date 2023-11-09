package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
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
@Table(name = "return_item")
public class ReturnItem {

  @EmbeddedId
  private ReturnItemId id;

  @MapsId("returnId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "return_id", nullable = false)
  private Return returnField;

  @MapsId("itemId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "item_id", nullable = false)
  private LineItem item;

  @NotNull
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @NotNull
  @Column(name = "is_requested", nullable = false)
  private Boolean isRequested = false;

  @Column(name = "requested_quantity")
  private Integer requestedQuantity;

  @Column(name = "received_quantity")
  private Integer receivedQuantity;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metadata;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reason_id")
  private ReturnReason reason;

  @Column(name = "note", length = Integer.MAX_VALUE)
  private String note;
}
