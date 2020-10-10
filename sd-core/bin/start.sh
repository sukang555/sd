#!/usr/bin/env bash

PROJECT_DIR=$( cd "$(dirname "${BASH_SOURCE[0]}")" && pwd | sed 's/\/bin//' )
SHELL_DIR=$( cd "$(dirname "${BASH_SOURCE[0]}")" && pwd )
source ${SHELL_DIR}/JAVA_ENV.sh
WEB_PATH=${PROJECT_DIR}/target/sd-core-1.0.0.jar
echo "PROJECT_DIR is ${PROJECT_DIR}"
echo "SHELL_DIR is ${SHELL_DIR}"

LOG_DIR=${PROJECT_DIR}/logs

JAVA_OPT=""
if [[ $1 == "" ]]; then
    JAVA_OPT="-Dspring.profiles.active=dev"
else
    JAVA_OPT="-Dspring.profiles.active=${1}"
fi

## jvm配置

JAVA_OPT="${JAVA_OPT} -XX:+UseG1GC"
JAVA_OPT="${JAVA_OPT} -XX:MaxGCPauseMillis=100"
JAVA_OPT="${JAVA_OPT} -XX:MaxMetaspaceSize=512m"
JAVA_OPT="${JAVA_OPT} -XX:MetaspaceSize=512m"
JAVA_OPT="${JAVA_OPT} -Duser.timezone=GMT+8 -XX:+PrintCommandLineFlags"
JAVA_OPT="${JAVA_OPT} -Xloggc:${PROJECT_DIR}/logs/gc.log"
JAVA_OPT="${JAVA_OPT} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${PROJECT_DIR}/logs"
JAVA_OPT="${JAVA_OPT} -Dlogging.config.location=${PROJECT_DIR}/config/logback.xml"
JAVA_OPT="${JAVA_OPT} -Dspring.config.location=${PROJECT_DIR}/config/application.properties"

JAVA_FINAL_OPT="${JAVA_MEM_OPTS} ${JAVA_OPT}"

CLASSPATH="${CLASSPATH}:${PROJECT_DIR}/config:${PROJECT_DIR}/lib/*"

echo "当前启动项目的环境为： ${1}"
echo "项目启动的参数为： ${JAVA_FINAL_OPT}"
echo "classpath路径为：${CLASSPATH}"


PID=""
function getPid() {
   PID=`ps -ef | grep ${APPLICATION_MAIN} | grep -v grep | awk '{print $2}'`
}

getPid


## 执行主命令  java -cp [classPath] [mainClass]
if [[ ${PID} != "" ]]; then
    echo "${APPLICATION_MAIN} already started (PID=${PID})"
else
    nohup ${JAVA_HOME}/bin/java ${JAVA_FINAL_OPT} -cp ${CLASSPATH} ${APPLICATION_MAIN} > ${LOG_DIR}/start.out 2>&1 &
    tail -f "${LOG_DIR}/start.out"
fi







