package com.crassus.core.types;

import com.crassus.core.serialization.PhoneNumberDeserializer;
import com.crassus.core.serialization.PhoneNumberSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@JsonSerialize(using = PhoneNumberSerializer.class)
@JsonDeserialize(using = PhoneNumberDeserializer.class)
public class PhoneNumber {

  @NotEmpty
  private long nationalNumber;

  @NotEmpty
  private int countryCode;

  public Phonenumber.PhoneNumber toPhoneNumber() {
    Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
    phoneNumber.setCountryCode(countryCode);
    phoneNumber.setNationalNumber(nationalNumber);
    return phoneNumber;
  }

  public String toE164() {
    return PhoneNumberUtil
      .getInstance()
      .format(toPhoneNumber(), PhoneNumberUtil.PhoneNumberFormat.E164);
  }
}
