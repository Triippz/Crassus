package com.crassus.models.models;

import com.crassus.core.converters.JsonToMapConverter;
import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends SoftDeletableEntity {

  @NotNull
  @Column(name = "login", nullable = false, length = Integer.MAX_VALUE)
  private String login;

  @Column(name = "first_name", length = Integer.MAX_VALUE)
  private String firstName;

  @Column(name = "last_name", length = Integer.MAX_VALUE)
  private String lastName;

  @Column(name = "email", length = Integer.MAX_VALUE)
  private String email;

  @NotNull
  @Column(name = "activated", nullable = false)
  private Boolean activated = false;

  @Column(name = "lang_key", length = Integer.MAX_VALUE)
  private String langKey;

  @Column(name = "last_modified_date")
  private Instant lastModifiedDate;

  @jakarta.validation.constraints.Size(max = 256)
  @Column(name = "image_url")
  private String imageUrl;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "default_billing_address_id")
  private Address defaultBillingAddress;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "default_shipping_address_id")
  private Address defaultShippingAddress;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<ApiKey> apiKeys;

  @Column(name = "meta_data")
  @JdbcTypeCode(SqlTypes.JSON)
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> metaData;

  @Transient
  private Set<Authority> authorities;

  @Override
  protected String getIdPrefix() {
    return "usr";
  }
}
