package org.kubersaurus.common.error;


import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * a top-parent generic edge exception
 */
public class EdgeException extends RuntimeException {

  /**
   * Http State code
   */
  protected int code;

  /**
   * Default constructor of {@link EdgeException}
   *
   * @param msg  is the error message
   * @param code is the HTTP state of this error
   */
  public EdgeException(String msg, int code) {
    super(msg);
    this.code = code;
  }

  /**
   * @return the HTTP state code of this error
   */
  public int getCode() {
    return code;
  }

  /**
   * @return an object of {@link Response}
   */
  public Response toHttpResponse() {
    return Response.status(code).entity(buildErrorEntity()).build();
  }

  /**
   * @return a {@link Error} represent this error details
   */
  public Map buildErrorEntity() {
    Map<String, Object> error = new HashMap<>();
    error.put("code", code);
    error.put("message", getMessage());
    return error;
  }

}
