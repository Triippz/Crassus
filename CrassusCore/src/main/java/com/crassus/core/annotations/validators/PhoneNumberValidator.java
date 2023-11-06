package com.crassus.core.annotations.validators;

import com.crassus.core.annotations.Phone;
import com.crassus.core.types.PhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, PhoneNumber> {

  @Override
  public void initialize(Phone constraintAnnotation) {}

  @Override
  public boolean isValid(PhoneNumber phoneNumber, ConstraintValidatorContext context) {
    try {
      PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
      String region = phoneNumberUtil.getRegionCodeForCountryCode(
        phoneNumber.getCountryCode()
      );
      Phonenumber.PhoneNumber parsedNumber = phoneNumberUtil.parse(
        String.valueOf(phoneNumber.getNationalNumber()),
        region
      );
      return phoneNumberUtil.isValidNumber(parsedNumber);
    } catch (NumberParseException e) {
      return false;
    }
  }
}
