# jib-docker-tile

A tile based on `jib-maven-plugin` to build the docker image.

## How to use

```xml

...

<properties>
  <!-- default -->
  <docker.image.from>openjdk:11.0.3-jdk-slim-stretch</docker.image.from.image>
  <docker.image.to>{{organisation}}/${project.artifactId}:${project.version}</docker.image.to>
  <docker.image.port>8080</docker.image.port>
</properties>

...

<build>
  <plugins>
    <plugin>
      <groupId>io.repaint.maven</groupId>
      <artifactId>tiles-maven-plugin</artifactId>
      <version>2.14</version>
      <extensions>true</extensions>
        <configuration>
          <filtering>false</filtering>
          <tiles>
            <tile>org.kubersaurus:jib-docker-tile:[1,2)</tile>
          </tiles>
        </configuration>
    </plugin>
  </plugins>
</build>


```

