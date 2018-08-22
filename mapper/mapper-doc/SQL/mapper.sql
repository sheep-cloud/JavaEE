DROP DATABASE IF EXISTS mapper;
CREATE DATABASE mapper CHARACTER SET UTF8;
USE mapper;

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

-- /// ----------------------------------------------------------------------------------------------------

SELECT * FROM table_emp;

SELECT
    emp_id, emp_name, emp_salary, emp_age
FROM table_emp
WHERE emp_name = 'bob' AND emp_salary = 5560.11;

SELECT
    emp_id, emp_name, emp_salary, emp_age
FROM table_emp
WHERE emp_id = 3;

SELECT
    CASE
        WHEN COUNT(emp_id) > 0
        THEN 1
        ELSE 0
    END AS result
FROM table_emp
WHERE emp_id = 3;

SELECT REPLACE(UUID(), '-', '')