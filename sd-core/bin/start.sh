#!/usr/bin/env bash

PROJECT_DIR=$( cd "$(dirname "${BASH_SOURCE[0]}")" && pwd | sed 's/\/bin//' )
SHELL_DIR=$( cd "$(dirname "${BASH_SOURCE[0]}")" && pwd )
source ${SHELL_DIR}/JAVA_ENV.sh
WEB_PATH=${PROJECT_DIR}/target/sd-core-1.0.0.jar
echo "PROJECT_DIR is ${PROJECT_DIR}"
echo "SHELL_DIR is ${SHELL_DIR}"

##配置
JAVA_OPT=""
JAVA_OPT="${JAVA_OPT} -XX:+UseG1GC"
JAVA_OPT="${JAVA_OPT} -XX:MaxGCPauseMillis=100"
JAVA_OPT="${JAVA_OPT} -XX:MaxMetaspaceSize=512m"
JAVA_OPT="${JAVA_OPT} -XX:MetaspaceSize=512m"



JAVA_FINAL_OPT="${JAVA_MEM_OPTS} ${JAVA_OPT}"

echo "${JAVA_FINAL_OPT}"

PID=""
function getPid() {
   PID=`ps -ef | grep ${APPLICATION_MAIN} | grep -v grep | awk '{print $2}'`
}

getPid

if [[ ${PID} != "" ]]; then
    echo "${APPLICATION_MAIN} already started (PID=${PID})"
else
    java -jar ${WEB_PATH} &
fi







