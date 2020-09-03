package org.kubersaurus.mapper;


import org.kubersaurus.error.EdgeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Exception mapper for edge service
 */
@Provider
public class EdgeExceptionMapper implements ExceptionMapper<Exception> {

  private static final Logger LOG = LoggerFactory.getLogger(EdgeExceptionMapper.class);

  @Override
  public Response toResponse(Exception exception) {

    // Do we really have Web application exception?
    if (exception instanceof WebApplicationException) {
      return ((WebApplicationException) exception).getResponse();
    }

    if (exception instanceof EdgeException) {
      return ((EdgeException) exception).toHttpResponse();
    }

    LOG.error("Failed jersey request", exception);
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(buildErrorEntity(exception)).build();
  }

  public Map buildErrorEntity(Exception ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("code", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    error.put("message", ex.getMessage());
    return error;
  }


}
