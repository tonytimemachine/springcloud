#!/bin/bash
#所有的项目包
Jar_names=$(find /Users/tony/Documents/WS/springcloud -name *SNAPSHOT.jar)
##进入项目根目录
cd /Users/tony/Documents/WS/springcloud
#打包(忽略测试)
mvn clean package -Dmaven.test.skip=true
##暂停十秒
sleep 10
for jar_name in $Jar_names
do
#带有master的进程
master=$(ps -ef|grep ${jar_name}|grep 'master'|grep -v $$|awk '$8 != "grep"'|awk '{print $2}')
#带有slave的进程
slave=$(ps -ef|grep ${jar_name}|grep 'slave'|grep -v $$|awk '$8 != "grep"'|awk '{print $2}')
#带有master的应用
node1='/Users/tony/Documents/WS/springcloud/springcloud-eureka-server-ha/target/springcloud-eureka-server-ha-2.0.0-SNAPSHOT.jar'
node2='/Users/tony/Documents/WS/springcloud/springcloud-provider-user-service/target/springcloud-provider-user-service-2.0.0-SNAPSHOT.jar'

if [[ $jar_name == $node1 ]] || [[ $jar_name == $node2 ]];then
if [[ $master == '' ]];then
java -jar $jar_name --spring.profiles.active=master &
echo $jar_name'启动成功'
else
kill -9 $master
java -jar $jar_name --spring.profiles.active=master &
echo $jar_name'启动成功'
fi
fi
if [[ $slave == '' ]];then
java -jar $jar_name --spring.profiles.active=slave &
echo $jar_name'启动成功'
else
kill -9 $slave
java -jar $jar_name --spring.profiles.active=slave &
echo $jar_name'启动成功'
fi
done
