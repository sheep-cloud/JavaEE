-- 慢查询日志的开启
# 查看慢查询日志是否开启；默认情况下slow_query_log的值为OFF，表示慢查询日志是禁用的。
SHOW VARIABLES LIKE '%slow_query_log%';

# 开启慢查询日志
SET GLOBAL slow_query_log = 1;

# 关闭慢查询日志
SET GLOBAL slow_query_log = 0;

# 如果要永久生效，就必须修改配置文件my.cnf（其他系统变量也是如此）

# 修改my.cnf文件，[mysqld]下增加或修改参数
# slow_query_log和slow_query_log_file后，然后重启MySQL服务器。也追加如下两行配置进行my.cnf文件

# slow_query_log=1
# linux： 	slow_query_log_file=/var/lib/mysql/xxx-slow.log
# windiows：	slow_query_log_file=D:\Sybase\mysql-5.7.19-winx64\data\Black-Cloud-slow.log

# 关于慢查询的参数slow_query_log_file，它指定慢查询日志文件的存放路径，系统默认会给一个缺省的文件host_name-slow.log（如果没有指定参数slow_query_log_file的话）


# 什么样的SQL会记录到慢查询日志里？
/*
	这个是由参数long_query_time控制，默认情况下long_query_time的值为10秒。保存大于10秒的，而不是大于等于
	命令：SHOW VARIABLES LIKE '%long_query_time%';
	
	可以使用命令修改，也可以在my.cnf参数里面修改。 
	
	如果要永久生效，就必须修改配置文件my.cnf
	long_query_time=3
	log_output=FILE
*/
# 查看当前多少秒算慢
SHOW VARIABLES LIKE '%long_query_time%';

# 设置慢查询的阈值时间，设置以后需要重新连接或重新开一个回话才能看到修改值
SET GLOBAL long_query_time = 3;

# 回复慢查询的阈值时间
SET GLOBAL long_query_time = 10;

# 使用新的命令查看慢查询的阈值时间
SHOW GLOBAL VARIABLES LIKE '%long_query_time%';

# 测试是否记录到日志
SELECT SLEEP(4);

# 查看慢查询日志记录的条数
SHOW GLOBAL STATUS LIKE '%Slow_queries%';
