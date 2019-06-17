#!bin/bash

jar_path="/usr/local/java/sd/sd-core/target"
webName="sd-core"
jarName="sd-core-1.0.0.jar"
webId=`ps -ef | grep ${webName} | grep -v grep | awk '{print $2}'`

array=${webId}

if [[ ${#array[*]} > 0 && ${array[0]} != ""  ]]
then 
     for i in ${array} 
     do kill -9 ${i}
     echo "杀死的进程id为${i}"
     done
     java -jar ${jar_path}/${jarName} &
else 
     java -jar ${jar_path}/${jarName} &
fi
