package com.crassus.models.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Column(name = "iso_2", nullable = false, length = Integer.MAX_VALUE)
    private String iso2;

    @NotNull
    @Column(name = "iso_3", nullable = false, length = Integer.MAX_VALUE)
    private String iso3;

    @NotNull
    @Column(name = "num_code", nullable = false)
    private Integer numCode;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @NotNull
    @Column(name = "display_name", nullable = false, length = Integer.MAX_VALUE)
    private String displayName;

    @Column(name = "region_id", insertable = false, updatable = false)
    private String regionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

}
