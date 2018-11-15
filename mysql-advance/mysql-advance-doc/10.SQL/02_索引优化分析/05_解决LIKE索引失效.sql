-- 如何解决 LIKE '%字符串%' 时索引不被使用的方法？？
USE mysql_advance;
DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(32),
	age INT,
	email VARCHAR(32)
);

INSERT INTO tbl_user(`name`, age, email) VALUES
('1aa1', 21, 'b@163.com'),
('2aa2', 222, 'a@163.com'),
('3aa3', 265, 'c@163.com'),
('4aa4', 21, 'd@163.com');

SELECT * FROM tbl_user;
SHOW INDEX FROM tbl_user;

-- 模糊查询
EXPLAIN SELECT id FROM tbl_user WHERE `name` LIKE '%aa%';
EXPLAIN SELECT `name` FROM tbl_user WHERE `name` LIKE '%aa%';
EXPLAIN SELECT age FROM tbl_user WHERE `name` LIKE '%aa%';

EXPLAIN SELECT id, `name` FROM tbl_user WHERE `name` LIKE '%aa%';
EXPLAIN SELECT id, `name`, age FROM tbl_user WHERE `name` LIKE '%aa%';
EXPLAIN SELECT id, age FROM tbl_user WHERE `name` LIKE '%aa%';
EXPLAIN SELECT `name`, age FROM tbl_user WHERE `name` LIKE '%aa%';

-- 索引失效
EXPLAIN SELECT email FROM tbl_user WHERE `name` LIKE '%aa%';
EXPLAIN SELECT * FROM tbl_user WHERE `name` LIKE '%aa%';
EXPLAIN SELECT id, `name`, age, email FROM tbl_user WHERE `name` LIKE '%aa%';

-- 创建索引
ALTER TABLE tbl_user ADD INDEX idx_user_nameAge(`name`, age);