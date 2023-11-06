package com.crassus.service.config.locale;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;

public class AngularCookieLocaleResolver extends CookieLocaleResolver {

  private final Logger logger = LoggerFactory.getLogger(
    AngularCookieLocaleResolver.class
  );
  public static final String QUOTE = "%22";

  public AngularCookieLocaleResolver() {}

  public AngularCookieLocaleResolver(String cookieName) {
    super(cookieName);
  }

  public Locale resolveLocale(HttpServletRequest request) {
    this.parseAngularCookieIfNecessary(request);
    return (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
  }

  public LocaleContext resolveLocaleContext(final HttpServletRequest request) {
    this.parseAngularCookieIfNecessary(request);
    return new TimeZoneAwareLocaleContext() {
      public Locale getLocale() {
        return (Locale) request.getAttribute(
          CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME
        );
      }

      public TimeZone getTimeZone() {
        return (TimeZone) request.getAttribute(
          CookieLocaleResolver.TIME_ZONE_REQUEST_ATTRIBUTE_NAME
        );
      }
    };
  }

  private void parseAngularCookieIfNecessary(HttpServletRequest request) {
    if (request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME) == null) {
      Cookie cookie = WebUtils.getCookie(request, DEFAULT_COOKIE_NAME);
      Locale locale = null;
      TimeZone timeZone = null;
      if (cookie != null) {
        String value = cookie.getValue();
        value = StringUtils.replace(value, "%22", "");
        String localePart = value;
        String timeZonePart = null;
        int spaceIndex = value.indexOf(32);
        if (spaceIndex != -1) {
          localePart = value.substring(0, spaceIndex);
          timeZonePart = value.substring(spaceIndex + 1);
        }

        locale =
          !"-".equals(localePart)
            ? StringUtils.parseLocaleString(localePart.replace('-', '_'))
            : null;
        if (timeZonePart != null) {
          timeZone = StringUtils.parseTimeZoneString(timeZonePart);
        }

        if (this.logger.isTraceEnabled()) {
          Logger var10000 = this.logger;
          String var10001 = cookie.getValue();
          var10000.trace(
            "Parsed cookie value [" +
            var10001 +
            "] into locale '" +
            locale +
            "'" +
            (timeZone != null ? " and time zone '" + timeZone.getID() + "'" : "")
          );
        }
      }

      request.setAttribute(
        LOCALE_REQUEST_ATTRIBUTE_NAME,
        locale != null ? locale : this.determineDefaultLocale(request)
      );
      request.setAttribute(
        TIME_ZONE_REQUEST_ATTRIBUTE_NAME,
        timeZone != null ? timeZone : this.determineDefaultTimeZone(request)
      );
    }
  }

  String quote(String string) {
    return "%22" + string + "%22";
  }
}
