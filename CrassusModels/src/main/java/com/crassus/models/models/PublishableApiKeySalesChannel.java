package com.crassus.models.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publishable_api_key_sales_channel")
public class PublishableApiKeySalesChannel {
    @EmbeddedId
    private PublishableApiKeySalesChannelId id;
}
