package com.crassus.models.models;

import com.crassus.models.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends SoftDeletableEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "company", length = Integer.MAX_VALUE)
    private String company;

    @Column(name = "first_name", length = Integer.MAX_VALUE)
    private String firstName;

    @Column(name = "last_name", length = Integer.MAX_VALUE)
    private String lastName;

    @Column(name = "address_1", length = Integer.MAX_VALUE)
    private String address1;

    @Column(name = "address_2", length = Integer.MAX_VALUE)
    private String address2;

    @Column(name = "city", length = Integer.MAX_VALUE)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
    private Country countryCode;

    @Column(name = "province", length = Integer.MAX_VALUE)
    private String province;

    @Column(name = "postal_code", length = Integer.MAX_VALUE)
    private String postalCode;

    @Column(name = "phone", length = Integer.MAX_VALUE)
    private String phone;

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
        return "addr";
    }
}
