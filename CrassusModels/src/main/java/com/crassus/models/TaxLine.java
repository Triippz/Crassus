package com.crassus.models;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class TaxLine extends BaseEntity {
    @Column(nullable = false)
    private Float rate; // 'real' in PostgreSQL is mapped to Java's Float

    @Column(nullable = false)
    private String name;

    @Column
    private String code;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;
}
