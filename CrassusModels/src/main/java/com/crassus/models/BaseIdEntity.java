package com.crassus.models;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseIdEntity implements Serializable {

  @Id
  private String id;

  @PrePersist
  protected void onPrePersist() {
    if (this.id == null || this.id.trim().isEmpty()) {
      this.id = generateEntityId();
    }
  }

  // This method generates a unique ID with an optional prefix.
  // Override this method in subclasses if you need to provide a specific prefix.
  protected String generateEntityId() {
    String ulid = UlidCreator.getUlid().toString();
    String prefix = getIdPrefix();
    return prefix != null && !prefix.isEmpty() ? prefix + "_" + ulid : ulid;
  }

  protected abstract String getIdPrefix();
}
