#!/usr/bin/env bash

export JAVA_MEM_OPTS="-Xms512m -Xmx1g -Xss256k -server "

export APPLICATION_MAIN="com.SdCoreApplication"

echo "JAVA_HOME: ${JAVA_HOME}"

export JAVA_HOME
CLASSPATH="${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/dt.jar"
export CLASSPATH

echo "CLASSPATH: ${CLASSPATH}"
