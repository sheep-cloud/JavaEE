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

# 新建部门表，修改员工表结构
DROP TABLE IF EXISTS tbl_dept;
CREATE TABLE tbl_dept(
	id INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	dept_name VARCHAR(32)
);

INSERT INTO tbl_dept(dept_name) VALUE('开发部');
INSERT INTO tbl_dept(dept_name) VALUE('测试部');

ALTER TABLE tbl_employee ADD COLUMN dept_id INT(11);
# 建立外键关系
ALTER TABLE tbl_employee ADD CONSTRAINT fk_emp_dept FOREIGN KEY(dept_id) REFERENCES tbl_dept(id);

# /// ----------------------------------------------------------------------------------------------------

SELECT te.id, te.last_name, te.gender, te.email, te.dept_id
FROM tbl_employee te
WHERE te.id = ? AND te.last_name = ? AND te.gender = ? AND te.email = ?
;


SELECT td.id, td.dept_name
FROM tbl_dept td
WHERE td.id = 1;

SELECT te.id, te.last_name, te.gender, te.email, te.dept_id
FROM tbl_employee te
WHERE te.id = 1 AND te.last_name LIKE '%j%' AND te.gender = '1' AND te.email LIKE '%j%';


SELECT
    te.id, te.last_name lastName, te.gender, te.email, te.dept_id
FROM
    tbl_employee te
WHERE te.gender = '0'
    AND te.email LIKE '%j%';
    
SELECT
    te.id, te.last_name, te.gender, te.email, te.dept_id
FROM
    tbl_employee te
WHERE te.id IN (1, 22, 23);


INSERT INTO tbl_employee(last_name, gender, email) VALUES ('smith', '0', 'smith@colg.com') , ('allen', '1', 'allen@colg.com');