-- 表锁（偏读）
USE mysql_advance;
DROP TABLE IF EXISTS test_myisam_lock;
CREATE TABLE test_myisam_lock (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(32)
) ENGINE MYISAM;

INSERT INTO  test_myisam_lock(`name`) VALUES
('a'),
('b'),
('c'),
('d'),
('e');

SELECT * FROM test_myisam_lock;

# 查看表上加过的锁，In_use > 0 表示被加锁
SHOW OPEN TABLES FROM mysql_advance;

# 手动增加表锁
# lock table 表名 read/write, 表名2 read/write, ...;
LOCK TABLE test_myisam_lock READ, book WRITE;

# 释放表锁
UNLOCK TABLES;


-- 1. 加读锁
LOCK TABLE test_myisam_lock READ;

# 1.1. 当前session可以查询该表记录；其他session也可以查询该表记录
SELECT * FROM test_myisam_lock;

# 1.2. 当前session查询其他未锁定的表会报错；其他session可以查询或更新未锁定的表
SELECT * FROM book;

# 1.3. 当前session中插入或更新锁定的表都会报错；其他session插入或更新锁定表会一直等待获得锁（阻塞）
UPDATE test_myisam_lock SET `name` = 'a2' WHERE id = 1;

# 1.4. 释放锁；其他session获得锁，插入或更新操作完成。
UNLOCK TABLES;

-- 2. 加写锁
LOCK TABLE test_myisam_lock WRITE;

# 2.1. 当前session可以查询该表记录；其他session也可以查询该表记录会一直等待获得锁（阻塞）
SELECT * FROM test_myisam_lock;

# 2.2. 当前session查询其他未锁定的表会报错；其他session可以查询或更新未锁定的表
SELECT * FROM book;

# 2.3. 当前session中可以插入或更新；其他session插入或更新锁定表会一直等待获得锁（阻塞）
UPDATE test_myisam_lock SET `name` = 'a3' WHERE id = 1;

# 2.3. 释放锁；其他session获得锁，查询、插入或更新操作完成
UNLOCK TABLES;




# 通过检查 Table_locks_waited 和 Table_locks_immediate 状态变量来分析系统上的表锁定；
SHOW STATUS LIKE 'table%'

# Table_locks_waited：产生表级锁定的次数，表示可以立即获取锁的查询次数，每立即获取锁值加1；
# Table_locks_immediate：出现表级锁定争用而发生等待的次数（不能立即获取锁的次数，每等待一次锁值加1），此值高则说明存在着比较严重的表级锁争用情况。

# 此外，MyISAM的读写锁调度是写优先，这也是MyISAM不适合做写为主表的引擎。因为写锁后，其他线程不能做任何操作，大量的更新会使查询很难得到锁，从而造成永远阻塞。