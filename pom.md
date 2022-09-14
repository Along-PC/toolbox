### maven工程生成可执行jar包

*pom文件中添加*

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.1</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <!--这里写你的main函数所在的类的路径名，也就是Class.forName的那个字符串-->
                                <mainClass>org.example.sticker.StickerExtract</mainClass>
                            </transformer>
                        </transformers>
                        <!-- maven-shade-plugin默认生成两个jar包，指定生成可执行jar包的后缀-->
                        <shadedArtifactAttached>true</shadedArtifactAttached>
                        <shadedClassifierName>dragon</shadedClassifierName>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

> https://blog.csdn.net/u011624157/article/details/118077267