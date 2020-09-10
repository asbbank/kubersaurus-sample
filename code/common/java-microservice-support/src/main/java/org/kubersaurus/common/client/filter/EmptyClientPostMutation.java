package org.kubersaurus.common.client.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 *
 * This mutation aims to fill in an empty object by default to avoid Http 411 error
 */
public class EmptyClientPostMutation implements ClientRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmptyClientPostMutation.class);

  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    LOGGER.debug("Sending [{}] client request to [{}]", requestContext.getMethod(), requestContext.getUri().getPath());
    if (isPostingEmptyEntity(requestContext)) {
      // Filling an empty object by default
      requestContext.setEntity("{}");
    }
  }

  private boolean isPostingEmptyEntity(ClientRequestContext requestContext) {
    return HttpMethod.POST.equals(requestContext.getMethod()) && requestContext.getEntity() == null;
  }

}
