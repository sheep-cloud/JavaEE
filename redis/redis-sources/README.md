# Redis NoSql数据库

## 1、启动服务

```xml
redis 单机
ps aux|grep redis
/usr/local/redis3.0/bin/redis-server /usr/local/redis3.0/bin/redis.conf
/usr/local/redis3.0/bin/redis-cli -h 192.168.21.103 -p 6379
ping PONG 健康检查
/usr/local/redis3.0/bin/redis-cli shutdown

redis 集群
ps aux|grep cluster
/usr/local/redis3.0/redis-cluster/start-redis-cluster-all.sh
/usr/local/redis3.0/redis-cluster/stop-redis-cluster-all.sh
```

