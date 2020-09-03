package org.kubersaurus;

import cd.connect.logging.JsonLogEnhancer;
import io.prometheus.client.Counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Richard Vowles - https://plus.google.com/+RichardVowles
 */
public class PriorityCounterLogEnhancer implements JsonLogEnhancer {
  private static final Map<String, Counter> logLevelCounters = new HashMap<>();

  private Counter add(String logLevel) {
    return logLevelCounters.computeIfAbsent(logLevel.toLowerCase(),
      k -> Counter.build(String.format("log_%s_counter", k.toLowerCase()),
        "Number of trace level log messages").register());
  }

  public PriorityCounterLogEnhancer() {
    add("TRACE");
    add("DEBUG");
    add("INFO");
    add("WARN");
    add("ERROR");
    add("GRPC");
    add("REST");
  }

  @Override
  public int getMapPriority() {
    return 100;
  }

  @Override
  public void map(Map<String, String> context, Map<String, Object> log, List<String> alreadyEncodedJsonObjects) {
    Counter counter = add((String)log.get("priority"));

    counter.inc();
  }

  @Override
  public void failed(Map<String, String> context, Map<String, Object> log, List<String> alreadyEncodedJsonObjects, Throwable e) {
  }
}
