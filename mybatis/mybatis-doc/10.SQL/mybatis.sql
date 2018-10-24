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

INSERT INTO
    tbl_employee(last_name, gender, email)
VALUES
    ('jack', '0', 'jack@colg.com');

-- 新建部门表，修改员工表结构
DROP TABLE IF EXISTS tbl_dept;
CREATE TABLE tbl_dept(
    id INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    dept_name VARCHAR(32)
);

INSERT INTO
    tbl_dept(dept_name)
VALUE
    ('开发部'),
    ('测试部');

ALTER TABLE tbl_employee ADD COLUMN dept_id INT(11);
-- 建立外键关系
ALTER TABLE tbl_employee ADD CONSTRAINT fk_emp_dept FOREIGN KEY(dept_id) REFERENCES tbl_dept(id);

-- 修改tbl_employee表
UPDATE tbl_employee SET dept_id = 1 WHERE id = 1;
UPDATE tbl_employee SET dept_id = 2 WHERE id = 2;



-- 创建用户
CREATE USER 'mybatis' IDENTIFIED BY '123456';

-- 用户授权
GRANT ALL ON mybatis.* TO 'mybatis';

-- 刷新权限
FLUSH PRIVILEGES;

-- /// ----------------------------------------------------------------------------------------------------

SELECT * FROM tbl_employee;
SELECT * FROM tbl_dept;

-- 根据id查询员工
SELECT
    te.id, te.last_name lastName, te.gender, te.email
FROM
    tbl_employee te
WHERE te.id = 1;

-- 根据姓名模糊查询员工列表，并封装到map里中；key：主键，value：员工
SELECT
    te.id, te.last_name lastName, te.gender, te.email
FROM
    tbl_employee te
WHERE te.last_name LIKE '%a%';

-- 根据id查询一条记录，并封装到map里；key：列名，value：值
SELECT
    te.id, te.last_name lastName, te.gender, te.email
FROM
    tbl_employee te
WHERE te.id = 1;

-- 根据id和姓名查询员工
SELECT
    te.id, te.last_name lastName, te.gender, te.email
FROM
    tbl_employee te
WHERE te.id = 1 AND te.last_name = 'jack';

-- 新增员工，返回主键
INSERT INTO
    tbl_employee (last_name, gender, email)
VALUES
    ('rose', '1', 'rose@colg.com');

-- 根据员工id集合查询名称集合    
SELECT
    te.last_name
FROM
    tbl_employee te
WHERE te.id IN (
    SELECT te.id FROM tbl_employee te 
);

-- 根据id查询员工，把部门也查出来；方式一：级联属性封装，OGNL表达式，对象.属性
SELECT
    te.id, te.last_name `lastName`, te.gender, te.email,
    td.id `dept.id`, td.dept_name `dept.departmentName`
FROM
    tbl_employee te
INNER JOIN tbl_dept td ON td.id = te.dept_id
WHERE te.id = 1;

-- 批量添加员工
INSERT INTO
    tbl_employee(last_name, gender, email, dept_id)
VALUES
    ('smith', '0', 'smith@colg.com', 1) , ('allen', '1', 'allen@colg.com', 2);