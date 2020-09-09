# tile-release-manifest


## How to use

```xml

<build>
  <plugins>
    <plugin>
      <groupId>io.repaint.maven</groupId>
      <artifactId>tiles-maven-plugin</artifactId>
      <version>2.15</version>
      <extensions>true</extensions>
        <configuration>
          <filtering>false</filtering>
          <tiles>
            <tile>nz.co.asb.engineering.tiles:tile-release-manifest:[1,2)</tile>
          </tiles>
        </configuration>
    </plugin>
  </plugins>
</build>


```

