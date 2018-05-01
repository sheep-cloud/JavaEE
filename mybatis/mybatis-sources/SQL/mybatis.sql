DROP DATABASE IF EXISTS mybatis;
CREATE DATABASE mybatis CHARACTER SET UTF8;
USE mybatis;

DROP TABLE IF EXISTS tbl_employee;
CREATE TABLE tbl_employee(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	last_name VARCHAR(32) COMMENT '姓名',
	gender CHAR(1) COMMENT '性别（0：男，1：女）',
	email VARCHAR(32) COMMENT '邮箱'
);

INSERT INTO tbl_employee(last_name, gender, email) VALUES('jack', '0', 'jack@colg.com');

SELECT te.id, te.last_name, te.gender, te.email FROM tbl_employee te;