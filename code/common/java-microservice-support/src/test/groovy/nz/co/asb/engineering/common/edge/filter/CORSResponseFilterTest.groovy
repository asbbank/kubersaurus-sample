package org.kubersaurus.filter

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.ws.rs.HttpMethod
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.core.Request
import javax.ws.rs.core.UriInfo

@Unroll
class CORSResponseFilterTest extends Specification {

  @Shared
  CORSResponseFilter underTest

  @Shared
  ContainerResponseContext containerResponseContextMock

  def 'shouldAddCORSHeaders'() {
    given:
      containerResponseContextMock = Mock()

    and:
      MultivaluedMap<String, Object> headersFake = new MultivaluedStringMap()
      containerResponseContextMock.headers >> headersFake

    and:
      ContainerRequestContext containerRequestContextMock = [
        getRequest: {
          return [
            getMethod: {
              return httpMethod
            }
          ] as Request
        },
        getUriInfo: {
          return [
            getRequestUri: {
              return [
                host: httpOrigin
              ] as URI
            }
          ] as UriInfo
        }
      ] as ContainerRequestContext

    and:
      underTest = new CORSResponseFilter()

    when:
      underTest.filter(containerRequestContextMock, containerResponseContextMock)

    then:
      headersFake.size() == 4
      headersFake.get("Access-Control-Allow-Origin").get(0) == expectedAllowOrigin
      headersFake.get("Access-Control-Allow-Methods").get(0) == "GET, POST, DELETE, PUT"
      headersFake.get("Access-Control-Allow-Headers").get(0) == "X-Requested-With, Content-Type, X-Codingpedia, Authorization, cif"
      headersFake.get("Access-Control-Allow-Credentials").get(0) == "true"

    where:
      httpMethod         | httpOrigin              | expectedAllowOrigin
      HttpMethod.GET     | "http://localhost:8080" | "*"
      HttpMethod.POST    | "http://localhost:8080" | "*"
      HttpMethod.OPTIONS | "http://localhost:8080" | "*"
  }

}
