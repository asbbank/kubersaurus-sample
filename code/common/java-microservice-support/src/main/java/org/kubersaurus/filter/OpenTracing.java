package org.kubersaurus.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;

public class OpenTracing {
  private static final Logger LOGGER = LoggerFactory.getLogger(OpenTracing.class);

  Map<String, String> openTracingHeaders = new HashMap<>();
  List<String> headerFields = Arrays.asList(
    "x-request-id",
    "x-b3-traceid",
    "x-b3-spanid",
    "x-b3-parentspanid",
    "x-b3-sampled",
    "x-b3-flags",
    "x-ot-span-context"
  );

  public void extractRequestHeaders(MultivaluedMap<String, String> headers) {
    headerFields.forEach(header -> {
      List<String> v = headers.get(header);
      if(v!=null && !v.isEmpty()){
        openTracingHeaders.put(header, v.get(0));
      }
    });
  }

  public void populateRequestHeaders(ClientRequestContext clientRequestContext) {
    Foo f = create(Foo.class);
    headerFields.stream().filter(h -> openTracingHeaders.get(h)!=null).forEach(header -> {
      String v = openTracingHeaders.get(header);
      clientRequestContext.getHeaders().putSingle(header, v);
    });
  }

  private Foo create(Class<Foo> fooClass) {
    return new Foo();
  }

  class Foo{

  }
}


