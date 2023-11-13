# JReloj

A simple clock application developed in Java, providing an intuitive graphical interface for displaying the current time. This minimalist clock offers a clear digital representation of the time, ensuring an easy and efficient user experience.

##  Run

To run the application, use the following Maven command:

```shell
mvn compile exec:java -Dexec.mainClass="cl.jreloj.App"
```

##  Generate jar with dependencies

To generate a JAR file with dependencies, execute the following Maven commands:

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
                        <mainClass>cl.jayclock.Appcl.jreloj.App</mainClass>
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

Explore jreloj and feel free to contribute, report issues, or make suggestions for further improvements.