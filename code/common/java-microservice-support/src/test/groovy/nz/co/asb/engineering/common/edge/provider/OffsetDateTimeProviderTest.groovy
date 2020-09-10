package org.kubersaurus.common.provider


import spock.lang.Specification
import spock.lang.Unroll

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Unroll
class OffsetDateTimeProviderTest extends Specification {

  def 'shouldGetConverterForOffsetDateTime'() {
    given:
      OffsetDateTimeProvider offsetDateTimeProvider = new OffsetDateTimeProvider();

    and:
      def exampleOffsetDateTimeString = "2016-10-02T20:15:30+01:00"
      def exampleOffsetDateTime = OffsetDateTime.parse(exampleOffsetDateTimeString, DateTimeFormatter.ISO_DATE_TIME)

    when:
      def returned = offsetDateTimeProvider.getConverter(OffsetDateTime.class, null, null)

    then:
      returned != null
      returned.fromString(exampleOffsetDateTimeString) == exampleOffsetDateTime
      returned.toString(exampleOffsetDateTime) == exampleOffsetDateTimeString
  }

  def 'shouldNotGetConverterForAnUnsupportedType'() {
    given:
      OffsetDateTimeProvider offsetDateTimeProvider = new OffsetDateTimeProvider();

    when:
      def returned = offsetDateTimeProvider.getConverter(String.class, null, null)

    then:
      returned == null
  }
}
