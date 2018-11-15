-- 往表里插入1000W数据
# 1. 建表
USE mysql_advance;
DROP TABLE IF EXISTS dept;
CREATE TABLE dept (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '部门id',
	dept_no MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '部门编号',
	dept_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '部门名称',
	loc VARCHAR(32) NOT NULL DEFAULT '' COMMENT '楼层'
) COMMENT '部门表';

DROP TABLE IF EXISTS emp;
CREATE TABLE emp (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '员工id',
	emp_no MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '员工编号',
	emp_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '员工名称',
	job VARCHAR(32) NOT NULL DEFAULT '' COMMENT '工作',
	mgr MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级编号',
	hiredate DATE NOT NULL COMMENT '入职时间',
	salary DECIMAL(7, 2) NOT NULL COMMENT '薪水',
	comm DECIMAL(7, 2) NOT NULL COMMENT '红利',
	dept_no MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '部门编号'
) COMMENT '员工表';

SELECT * FROM dept;
SELECT * FROM emp;

# 2. 设置参数log_big_trust_function_creators
/*
	创建函数，加入报错：This function has none of DETERMINISTIC......
	# 由于开启慢查询日志，因为开启了bin-log，就必须为funciton指定一个参数。
	
	SHOW VARIABLES LIKE 'log_bin_trust_function_creators';
	SET GLOBAL log_bin_trust_function_creators = 1;
	
	# 这样添加了参数以后，如果mysqld重启，上述参数又会消失，永久方法：
	window 下 my.ini [mysqld] 加上 log_bin_trust_function_creators=1
	linux 下 /etc/my.cnf [mysqld] 加上 log_bin_trust_function_creators=1
	
*/


# 3. 创建函数，保证每条数据都不同
# 3.1. 获取指定范围内的随机数，用于随机产生部门编号
DROP FUNCTION IF EXISTS random_int;
DELIMITER $
CREATE FUNCTION random_int(start_num INT, end_num INT) RETURNS INT
BEGIN
/*
	获得指定范围内的随机数
	
	@params start_num 最小数（包含）
	@params end_num 最大数（包含）
	@return 随机数
*/
	DECLARE i INT DEFAULT 0;
	# 若要在i ≤ R ≤ j 这个范围得到一个随机整数R ，需要用到表达式 FLOOR(i + RAND() * (j – i + 1))。
	SET i = FLOOR(start_num + RAND() * (end_num - start_num + 1));
	RETURN i;
END $;

SELECT random_int(1, 52);

# 3.2. 获得一个随机的字符串（a-z，A-Z）
DROP FUNCTION IF EXISTS random_string;
DELIMITER $
CREATE FUNCTION random_string(len INT) RETURNS VARCHAR(256)
BEGIN
/*
	获得一个随机的字符串（a-z，A-Z）
	
	@params len 字符串的长度
	@return 随机字符串
*/
	DECLARE base_char VARCHAR(32) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(256) DEFAULT '';
	DECLARE base_length INT DEFAULT LENGTH(base_char);
	DECLARE i INT DEFAULT 0;
	WHILE i < len DO
		SET return_str = CONCAT(return_str, SUBSTR(base_char, random_int(1, base_length), 1));
		SET i = i + 1;
	END WHILE;
	RETURN return_str;
END $;

SELECT random_string(10);

# 3.3. 获取一个随机日期
DROP FUNCTION IF EXISTS random_date;
DELIMITER $
CREATE FUNCTION random_date(start_date VARCHAR(32), end_date VARCHAR(32)) RETURNS DATE
BEGIN
/*
	获取随机日期，范围 start_date ~ end_date
*/
	DECLARE return_date DATE DEFAULT NULL;
	SET return_date = DATE(FROM_UNIXTIME(random_int(UNIX_TIMESTAMP(start_date), UNIX_TIMESTAMP(end_date))));
	RETURN return_date;
END $;

SELECT random_date('1963-01-01', '2000-01-01');


# 4. 创建存储过程
# 4.1. 创建往dept表中插入数据的存储过程
DROP PROCEDURE IF EXISTS insert_dept;
DELIMITER $
CREATE PROCEDURE insert_dept(IN start_no INT, IN max_num INT)
BEGIN
/*
	往dept表中插入数据
	
	@params start_no 部门起始编号
	@params max_num 插入数量
*/
DECLARE i INT DEFAULT 0;
START TRANSACTION;
	WHILE i < max_num DO
		INSERT INTO dept(dept_no, dept_name, loc) VALUES((start_no + i), random_string(5), random_string(8));
		SET i = i + 1;
	END WHILE;
COMMIT;
END $;

# 4.2. 创建往emp表中插入数据的存储过程
DROP PROCEDURE IF EXISTS insert_emp;
DELIMITER $
CREATE PROCEDURE insert_emp(IN start_no INT, IN max_num INT)
BEGIN
/*
	往emp表中插入数据
	
	@params start_no 员工起始编号
	@params max_num 插入数量
*/
DECLARE i INT DEFAULT 0;
START TRANSACTION;
	WHILE i < max_num DO
		INSERT INTO emp(emp_no, emp_name, job, mgr, hiredate, salary, comm, dept_no) VALUES
		((start_no + i), random_string(6), 'SALESMAN', 0001, random_date('1963-01-01', '2000-01-01'), random_int(2000, 4000), random_int(200, 400), random_int(100, 109));
		SET i = i + 1;
	END WHILE;
COMMIT;
END $;

# 5. 调用存储过程
TRUNCATE TABLE dept;
CALL insert_dept(100, 10);
SELECT * FROM dept;

TRUNCATE TABLE emp;
CALL insert_emp(10, 1000000);
SELECT * FROM emp;


# 每个月入职的员工数
EXPLAIN
SELECT DATE_FORMAT(e.hiredate, '%Y年%m月') 入职日期, COUNT(*)
FROM emp e
GROUP BY 入职日期;

# 统计姓名相同的员工
EXPLAIN
SELECT e.id FROM emp e
INNER JOIN dept d ON e.dept_no = d.dept_no
WHERE e.emp_name IN (
	SELECT emp_name FROM emp
	GROUP BY emp_name
	HAVING COUNT(*) > 1
);

ALTER TABLE emp ADD INDEX adx_emp_empName(emp_name);
ALTER TABLE emp ADD INDEX adx_emp_empNoEmpName(emp_no, emp_name);
ALTER TABLE dept ADD INDEX idx_dept_deptNo(dept_no);
ALTER TABLE dept ADD INDEX idx_dept_deptName(dept_name);
ALTER TABLE dept ADD INDEX idx_dept_deptNoDeptName(dept_no, dept_name);

DROP INDEX adx_emp_empName ON emp;
DROP INDEX adx_emp_empNoEmpName ON emp;
DROP INDEX idx_dept_deptNo ON dept;
DROP INDEX idx_dept_deptName ON dept;
DROP INDEX idx_dept_deptNoDeptName ON dept;


# 查询姓名相同员工的最小id
SELECT * FROM emp e
WHERE e.emp_name IN (
	SELECT emp_name FROM emp
	GROUP BY emp_name
	HAVING COUNT(*) > 1
) AND id NOT IN (
	SELECT MIN(id) FROM emp
	GROUP BY emp_name
	HAVING COUNT(*) > 1
);
