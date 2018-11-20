-- 行锁（偏写）
USE mysql_advance;
DROP TABLE IF EXISTS test_innodb_lock;
CREATE TABLE test_innodb_lock (
	a INT,
	b VARCHAR(32)
) ENGINE = INNODB;

INSERT INTO test_innodb_lock(a, b) VALUES
(1, 'b2'),
(3, '3'),
(4, '4000'),
(5, '5000'),
(6, '6000'),
(7, '7000'),
(8, '8000'),
(19, '9000'),
(1, 'b1');

SELECT * FROM test_innodb_lock;

ALTER TABLE test_innodb_lock ADD INDEX idx_test_innodb_lock_a(a);
ALTER TABLE test_innodb_lock ADD INDEX idx_test_innodb_lock_b(b);

SHOW INDEX FROM test_innodb_lock;

# 修改时，索引失效，行锁变表锁



# 如何分析行锁锁定
# 通过检查 innodb_row_lock 状态变量来分析系统上的行锁的争夺情况
SHOW STATUS LIKE 'innodb_row_lock%';

# Innodb_row_lock_current_waits			当前正在等待锁定的数量
# Innodb_row_lock_time				从系统启动到现在锁定总时间长度
# Innodb_row_lock_time_avg			每次等待所花平均时间
# Innodb_row_lock_time_max			从系统启动到现在等待最长的一次所花的时间
# Innodb_row_lock_waits				系统启动后到现在总共等待的次数