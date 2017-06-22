#!/bin/bash
##进入项目根目录
pids=$(ps -ef|grep java|grep -v 'IDEA'|grep -v $$|awk '$8 != "grep"'|awk '{print $2}')
#所有的项目包
for pid in $pids
do
#带有slave的进程
pid=$(ps -ef|grep java|grep -v 'IDEA'|grep -v $$|awk '$8 != "grep"'|awk '{print $2}')
if [[ $pid == '' ]];then
echo '服务未启动'
else
kill -9 $pid
echo 'kill成功'
fi
done
