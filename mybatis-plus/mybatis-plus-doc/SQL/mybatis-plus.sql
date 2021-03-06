DROP DATABASE IF EXISTS `mybatis-plus`;
CREATE DATABASE `mybatis-plus` CHARACTER SET UTF8;
USE `mybatis-plus`;

DROP TABLE IF EXISTS `table_emp`;

CREATE TABLE `table_emp` (
	`emp_id` VARCHAR(64) NOT NULL COMMENT '主键',
	`emp_name` VARCHAR(500) NULL COMMENT '员工姓名',
	`emp_salary` DOUBLE(15,5) NULL COMMENT '员工工资',
	`emp_age` INT NULL COMMENT '员工年龄',
	PRIMARY KEY (`emp_id`)
) COMMENT '员工表';

INSERT INTO
    table_emp(emp_id, emp_name, emp_salary, emp_age)
VALUES
    (REPLACE(UUID(), '-', ''), 'tom', '1254.37', '27'),
    (REPLACE(UUID(), '-', ''), 'jerry', '6635.42', '38'),
    (REPLACE(UUID(), '-', ''), 'bob', '5560.11', '40'),
    (REPLACE(UUID(), '-', ''), 'kate', '2209.11', '22'),
    (REPLACE(UUID(), '-', ''), 'justin', '4203.15', '30');
    
    
CREATE USER 'mybatis-plus' IDENTIFIED BY '123456';
GRANT ALL ON `mybatis-plus`.* TO 'mybatis-plus';
FLUSH PRIVILEGES;
SELECT `host`, `user`, authentication_string FROM mysql.user;

-- /// ----------------------------------------------------------------------------------------------------

SELECT * FROM table_emp;