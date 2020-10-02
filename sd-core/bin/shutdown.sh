#!/usr/bin/env bash
#获取到 sd-core的进程id 后直接杀死
SHELL_DIR=$( cd "$(dirname "${BASH_SOURCE[0]}")" && pwd);
source ${SHELL_DIR}/JAVA_ENV.sh

webName=""
if [[ ${1} == '' ]]
then
    webName=${APPLICATION_MAIN}
else
    webName=${1}
fi

echo "process name is ${webName}"

pid=""
function getPid() {
   pid=`ps -ef | grep ${webName} | grep -v grep | awk '{print $2}'`
}

getPid

if [[ ${pid} == '' ]]
then
    echo "process is not running "
else
    echo "process id is ${pid}"
    kill -9 ${pid}
fi



