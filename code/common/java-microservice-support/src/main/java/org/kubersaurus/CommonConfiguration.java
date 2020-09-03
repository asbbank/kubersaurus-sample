package org.kubersaurus;

import cd.connect.jersey.common.JacksonContextProvider;
import cd.connect.jersey.common.JerseyExceptionMapper;
import cd.connect.jersey.common.JerseyPrometheusResource;
import cd.connect.jersey.prometheus.PrometheusDynamicFeature;
import org.kubersaurus.api.HealthResource;
import org.kubersaurus.client.filter.EmptyClientPostMutation;
import org.kubersaurus.filter.OpenTracingHeaderFilter;
import org.kubersaurus.mapper.EdgeExceptionMapper;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.JerseyClientLogger;
import org.glassfish.jersey.logging.JerseyServerLogger;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.servlet.ServletProperties;

import javax.ws.rs.core.Configurable;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 * <p>
 * Default common configuration to register all edge related resources
 */
public class CommonConfiguration extends cd.connect.jersey.common.CommonConfiguration {

  public static void applyBasicConfigs(Configurable<? extends Configurable> config) {
    config.property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
    config.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
    config.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    config.property(ServletProperties.PROVIDER_WEB_APP, false); // do not scan!

    // increase the outbound content length buffer to be 250k rather then the default 8k
    config.property(CommonProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 250000);

    config.register(JacksonFeature.class);
    config.register(MultiPartFeature.class);
    config.register(GZipEncoder.class);
    config.register(JacksonContextProvider.class);
  }

  public static void applyBasicServerConfigs(Configurable<? extends Configurable> config) {
    applyBasicConfigs(config);
    config.register(JerseyServerLogger.class);
    config.register(EdgeExceptionMapper.class);
    config.register(HealthResource.class);
    config.register(JerseyPrometheusResource.class);
//    config.register(PrometheusDynamicFeature.class);
    config.register(OpenTracingHeaderFilter.class);
  }

  public static void applyBasicClientConfigs(Configurable<? extends Configurable> config) {
    applyBasicConfigs(config);
    config.register(JerseyClientLogger.class);
    config.register(EmptyClientPostMutation.class);
    config.register(JerseyExceptionMapper.class);
    config.register(OpenTracingHeaderFilter.class);
  }

}
