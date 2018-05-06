DROP DATABASE IF EXISTS spring_cloud_8003;
CREATE DATABASE spring_cloud_8003 CHARACTER SET UTF8;
USE spring_cloud_8003;

CREATE TABLE dept(
	deptno BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	deptname VARCHAR(32) COMMENT '部门名称',
	dbsource VARCHAR(32) COMMENT '来自哪个数据库'
) COMMENT '部门表';

INSERT INTO dept(deptname, dbsource) VALUES('开发部', DATABASE());
INSERT INTO dept(deptname, dbsource) VALUES('人事部', DATABASE());
INSERT INTO dept(deptname, dbsource) VALUES('财务部', DATABASE());
INSERT INTO dept(deptname, dbsource) VALUES('市场部', DATABASE());
INSERT INTO dept(deptname, dbsource) VALUES('运维部', DATABASE());


SELECT d.deptno, d.deptname, d.dbsource FROM dept d;