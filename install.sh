#!/bin/sh

export JAVA_VERSION=11
mvn clean archetype:create-from-project
cd target/generated-sources/archetype || exit
mvn install
