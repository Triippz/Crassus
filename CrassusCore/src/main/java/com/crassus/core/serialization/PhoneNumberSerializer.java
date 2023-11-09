package com.crassus.core.serialization;

import com.crassus.core.models.PhoneNumber;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import java.io.IOException;

public class PhoneNumberSerializer extends JsonSerializer<PhoneNumber> {

  @Override
  public void serialize(
    PhoneNumber phoneNumber,
    JsonGenerator jsonGenerator,
    SerializerProvider serializerProvider
  ) throws IOException {
    jsonGenerator.writeString(
      PhoneNumberUtil
        .getInstance()
        .format(phoneNumber.toPhoneNumber(), PhoneNumberUtil.PhoneNumberFormat.E164)
    );
  }
}
