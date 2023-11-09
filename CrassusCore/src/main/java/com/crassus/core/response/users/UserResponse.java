package com.crassus.core.response.users;

import com.crassus.core.CrassusConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String id;

  @NotBlank
  @Pattern(regexp = CrassusConstants.LOGIN_REGEX)
  @Size(min = 1, max = 50)
  private String login;

  @Size(max = 50)
  private String firstName;

  @Size(max = 50)
  private String lastName;

  @Email
  @Size(min = 5, max = 254)
  private String email;

  @Size(max = 256)
  private String imageUrl;

  @Builder.Default
  private boolean activated = false;

  @Size(min = 2, max = 10)
  private String langKey;

  private String createdBy;

  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;

  private OffsetDateTime deletedAt;

  private String lastModifiedBy;

  private Instant lastModifiedDate;

  private Set<String> authorities;
}
