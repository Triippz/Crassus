package com.crassus.core.converters;

import com.crassus.core.models.PhoneNumber;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

  @Override
  public String convertToDatabaseColumn(PhoneNumber phoneNumber) {
    if (phoneNumber == null) {
      return null;
    }

    return PhoneNumberUtil
      .getInstance()
      .format(phoneNumber.toPhoneNumber(), PhoneNumberUtil.PhoneNumberFormat.E164);
  }

  @Override
  public PhoneNumber convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }

    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    try {
      return new PhoneNumber(
        phoneNumberUtil.parse(dbData, null).getNationalNumber(),
        phoneNumberUtil.parse(dbData, null).getCountryCode()
      );
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid phone number format: " + dbData);
    }
  }
}
