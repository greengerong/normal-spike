doc:

(http://mojo.codehaus.org/versions-maven-plugin/usage.html)[http://mojo.codehaus.org/versions-maven-plugin/usage.html]

plugin:

        <reporting>
            <plugins>
                <plugin>
                    <groupId>org.codehaus.mojo</groupId>
                    <artifactId>versions-maven-plugin</artifactId>
                    <version>${versions-maven-plugin.version}</version>
                    <reportSets>
                        <reportSet>
                            <reports>
                                <report>dependency-updates-report</report>
                                <report>plugin-updates-report</report>
                                <report>property-updates-report</report>
                            </reports>
                        </reportSet>
                    </reportSets>
                </plugin>
            </plugins>
        </reporting>


升级版本：

    mvn versions:set -DnewVersion=1.1-SNAPSHOT

回滚：

    mvn versions:revert

提交版本改变：

    mvn versions:commit

