DROP DATABASE IF EXISTS spring_boot;
CREATE DATABASE spring_boot CHARACTER SET UTF8;
USE spring_boot;

-- 创建用户
CREATE USER 'spring_boot' IDENTIFIED BY '123456';
GRANT ALL ON spring_boot.* TO 'spring_boot';
FLUSH PRIVILEGES;

-- 查看用户
SELECT `host`, `user`, authentication_string FROM mysql.user;

SELECT * FROM department;
SHOW FULL COLUMNS FROM department;

SELECT * FROM employee;
SHOW FULL COLUMNS FROM employee;

SELECT NOW() FROM DUAL;

SELECT d.* FROM department d WHERE d.id = ?;
INSERT INTO department(departmentName) VALUES(?);
DELETE FROM department WHERE id = ?;
UPDATE department SET departmentName = ? WHERE id = ?;

SELECT * FROM employee;
SELECT e.* FROM employee e WHERE e.id = ?;
INSERT INTO employee(lastName, email, gender, d_id) VALUES(?, ?, ?, ?);

SELECT * FROM tbl_user;
SHOW FULL COLUMNS FROM tbl_user;