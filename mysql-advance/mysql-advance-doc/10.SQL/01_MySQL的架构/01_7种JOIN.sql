/*
	7中通用JOIN：
		内连接、左连接、右连接、左独有连接、右独有连接、全连接、全连接去交集
*/
USE mysql_advance;
DROP TABLE IF EXISTS tbl_dept;
CREATE TABLE tbl_dept (
	id INT PRIMARY KEY AUTO_INCREMENT,
	dept_name VARCHAR(32),
	loc_add VARCHAR(64)
);

INSERT  INTO tbl_dept(id, dept_name, loc_add) VALUES 
(1, 'RD', '11'),
(2, 'HR', '12'),
(3, 'MK', '13'),
(4, 'MIS', '14'),
(5, 'FD', '15');


DROP TABLE IF EXISTS tbl_emp;
CREATE TABLE tbl_emp (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(32),
	dept_id INT(11)
);

INSERT  INTO tbl_emp(id, `name`, dept_id) VALUES 
(1, 'z3', 1),
(2, 'z4', 1),
(3, 'z5', 1),
(4, 'w5', 2),
(5, 'w6', 2),
(6, 's7', 3),
(7, 's8', 4),
(8, 's9', 51);


-- 1. 内连接
SELECT * FROM tbl_emp a
INNER JOIN tbl_dept b ON a.dept_id = b.id;

-- 2. 左连接
SELECT * FROM tbl_emp a
LEFT JOIN tbl_dept b ON a.dept_id = b.id;

-- 3. 右连接
SELECT * FROM tbl_emp a
RIGHT JOIN tbl_dept b ON a.dept_id = b.id;

-- 4. 左独有连接
SELECT * FROM tbl_emp a
LEFT JOIN tbl_dept b ON a.dept_id = b.id
WHERE b.id IS NULL;

-- 5. 右独有连接
SELECT * FROM tbl_emp a
RIGHT JOIN tbl_dept b ON a.dept_id = b.id
WHERE a.dept_id IS NULL;

-- 6. 全连接
SELECT * FROM tbl_emp a
LEFT JOIN tbl_dept b ON a.dept_id = b.id
UNION
SELECT * FROM tbl_emp a
RIGHT JOIN tbl_dept b ON a.dept_id = b.id;

-- 7. 全连接去交集
SELECT * FROM tbl_emp a
LEFT JOIN tbl_dept b ON a.dept_id = b.id
WHERE b.id IS NULL
UNION
SELECT * FROM tbl_emp a
RIGHT JOIN tbl_dept b ON a.dept_id = b.id
WHERE a.dept_id IS NULL;