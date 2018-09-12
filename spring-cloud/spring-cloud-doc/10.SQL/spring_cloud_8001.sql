DROP DATABASE IF EXISTS spring_cloud_8001;
CREATE DATABASE spring_cloud_8001 CHARACTER SET UTF8;
USE spring_cloud_8001;

CREATE TABLE dept(
	deptno BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	deptname VARCHAR(32) COMMENT '部门名称',
	dbsource VARCHAR(32) COMMENT '来自哪个数据库'
) COMMENT '部门表';

INSERT INTO
	dept(deptname, dbsource)
VALUES
	('开发部', DATABASE()),
	('财务部', DATABASE()),
	('市场部', DATABASE()),
	('运维部', DATABASE());
	
-- 创建用户，授权
CREATE USER 'spring_cloud' IDENTIFIED BY '123456';
GRANT ALL ON spring_cloud_8001.* TO 'spring_cloud';
FLUSH PRIVILEGES;

-- /// ----------------------------------------------------------------------------------------------------

SELECT d.deptno, d.deptname, d.dbsource FROM dept d;