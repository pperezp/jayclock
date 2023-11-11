# Run
```shell
mvn compile exec:java -Dexec.mainClass="cl.jreloj.Aplicacion"
```

# Generate jar with dependencies
```shell
mvn clean package
```
```shell
java -jar target/jreloj-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Necessary add this to pom.xml to generate jar with dependencies
```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-assembly-plugin</artifactId>
            <version>3.3.0</version>
            <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
                <archive>
                    <manifest>
                        <mainClass>cl.jreloj.Aplicacion</mainClass>
                    </manifest>
                </archive>
            </configuration>
            <executions>
                <execution>
                    <id>make-assembly</id>
                    <phase>package</phase>
                    <goals>
                        <goal>single</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
