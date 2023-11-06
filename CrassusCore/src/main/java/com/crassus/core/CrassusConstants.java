package com.crassus.core;

public interface CrassusConstants {
  String SPRING_PROFILE_DEVELOPMENT = "dev";
  String SPRING_PROFILE_TEST = "test";
  String SPRING_PROFILE_E2E = "e2e";
  String SPRING_PROFILE_CLOUD = "cloud";

  String SPRING_PROFILE_PRODUCTION = "prod";
  String SPRING_PROFILE_API_DOCS = "api-docs";

  String LOGIN_REGEX =
    "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
}
