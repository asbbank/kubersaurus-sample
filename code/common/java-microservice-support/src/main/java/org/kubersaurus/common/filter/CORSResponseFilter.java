package org.kubersaurus.common.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

/**
 * CORS Response Filter to mutate the response header
 */
public class CORSResponseFilter implements ContainerResponseFilter {
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
    MultivaluedMap<String, Object> headers = responseContext.getHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia, Authorization, cif");
    headers.add("Access-Control-Allow-Credentials", "true");
  }
}
