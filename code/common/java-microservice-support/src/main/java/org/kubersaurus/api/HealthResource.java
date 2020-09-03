package org.kubersaurus.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Path("/healthcheck")
public class HealthResource {

  public HealthResource() {
    // TODO: inject some downstream dependencies, and make sure they are working properly
  }

  @GET
  @Path("/full")
  @Produces({ "application/json" })
  public Map<String, String> performFullHealthCheck() {
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("self", "health");
    return responseMap;
  }

  @GET
  @Path("/light")
  @Produces({ "text/html" })
  public Response performLightHealthCheck() {
    return Response.ok().entity("OK").build();
  }
}
