#!/bin/bash
set user 'root'@'%' identified with mysql_native_password by 'root'
mysql -uroot -p$MYSQL_ROOT_PASSWORD <<EOF
source $WORK_PATH/$FILE_0;