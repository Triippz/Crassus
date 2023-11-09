package com.crassus.core.serialization;

import com.crassus.core.models.PhoneNumber;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class PhoneNumberDeserializer extends JsonDeserializer<PhoneNumber> {

  @Override
  public PhoneNumber deserialize(
    JsonParser jsonParser,
    DeserializationContext deserializationContext
  ) throws IOException {
    String phoneString = jsonParser.getText();
    if (phoneString == null || !phoneString.startsWith("+")) {
      return null;
    }

    // Assuming locale is always one digit.
    int countryCode = Integer.parseInt(phoneString.substring(1, 2));
    long nationalNumber = Long.parseLong(phoneString.substring(2));

    return new PhoneNumber(nationalNumber, countryCode);
  }
}
