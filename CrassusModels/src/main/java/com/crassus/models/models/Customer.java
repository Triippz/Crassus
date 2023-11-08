package com.crassus.models.models;

import com.crassus.core.converters.PhoneNumberConverter;
import com.crassus.core.models.PhoneNumber;
import com.crassus.models.SoftDeletableEntity;
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
@Table(name = "customer")
public class Customer extends SoftDeletableEntity {

  @NotNull
  @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
  private String email;

  @Column(name = "first_name", length = Integer.MAX_VALUE)
  private String firstName;

  @Column(name = "last_name", length = Integer.MAX_VALUE)
  private String lastName;

  @Column(name = "billing_address_id", insertable = false, updatable = false)
  private String billingAddressId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "billing_address_id")
  private Address billingAddress;

  @Column(name = "password_hash", length = Integer.MAX_VALUE)
  private String passwordHash;

  @Column(name = "phone", length = Integer.MAX_VALUE)
  @Convert(converter = PhoneNumberConverter.class)
  private PhoneNumber phone; // TODO add phoneNumber type

  @NotNull
  @Column(name = "has_account", nullable = false)
  private Boolean hasAccount = false;

  @NotNull
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @NotNull
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = "deleted_at")
  private OffsetDateTime deletedAt;

  @Column(name = "metadata")
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> metadata;

  @Override
  protected String getIdPrefix() {
    return "cus";
  }
}
