DROP DATABASE IF EXISTS spring_boot;
CREATE DATABASE spring_boot CHARACTER SET UTF8;
USE spring_boot;

-- 创建用户
CREATE USER 'spring_boot' IDENTIFIED BY '123456';
GRANT ALL ON spring_boot.* TO 'spring_boot';
FLUSH PRIVILEGES;

-- 查看用户
SELECT `host`, `user`, authentication_string FROM mysql.user;