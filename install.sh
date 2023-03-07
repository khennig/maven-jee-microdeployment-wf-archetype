#!/bin/sh

export JAVA_VERSION=11
mvn clean org.apache.maven.plugins:maven-archetype-plugin:create-from-project
cd target/generated-sources/archetype || exit
mvn install
