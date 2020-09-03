package org.kubersaurus.api


import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import spock.lang.Shared
import spock.lang.Specification

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1) 
 */
class HealthResourceTest extends Specification {

  @Shared
  def jerseyTest = new JerseyTest() {

    @Override
    protected ResourceConfig configure() {
      return new ResourceConfig().register(HealthResource)
    }

  }

  def setupSpec() {
    jerseyTest.setUp()
  }

  def cleanupSpec() {
    jerseyTest.tearDown()
  }


  def 'should return 200 response'() {
    when: 'send a GET request to healthcheck endpoint'
      def response = jerseyTest.target("/healthcheck/light").request().get()

    then: 'assert the expected response'
      response.status == 200
      response.readEntity(String.class) == "OK"
  }


}
