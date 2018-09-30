```ini
# redis 单机
ps aux|grep redis
/usr/local/redis/bin/redis-server /usr/local/redis/bin/redis.conf
/usr/local/redis/bin/redis-cli -h 193.112.72.47 -p 6379
ping PONG 健康检查
/usr/local/redis/bin/redis-cli shutdown
```
