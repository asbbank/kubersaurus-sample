package org.kubersaurus.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class OpenTracingHeaderFilter implements ContainerRequestFilter, ClientRequestFilter {

  public static final ThreadLocal<OpenTracing> openTracingHeaders = new ThreadLocal<>();
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    OpenTracing openTracing = new OpenTracing();
    openTracing.extractRequestHeaders(containerRequestContext.getHeaders());
    openTracingHeaders.set(openTracing);
  }

  @Override
  public void filter(ClientRequestContext clientRequestContext) throws IOException {
    openTracingHeaders.get().populateRequestHeaders(clientRequestContext);
  }
}
