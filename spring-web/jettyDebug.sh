#! /bin/bash
export GRADLE_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=9090,server=y,suspend=n"
./gradlew clean jettyRun