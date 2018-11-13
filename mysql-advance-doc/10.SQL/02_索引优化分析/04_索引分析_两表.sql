-- 索引分析_两表
USE mysql_advance;
DROP TABLE IF EXISTS class;
CREATE TABLE class (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	card INT UNSIGNED NOT NULL
);

INSERT INTO class(card) VALUES
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20)));

DROP TABLE IF EXISTS book;
CREATE TABLE book (
	book_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	card INT UNSIGNED NOT NULL
);

INSERT INTO book(card) VALUES
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20))),
(FLOOR(1 + (RAND() * 20)));

SELECT * FROM class;
SELECT * FROM book;
SHOW INDEX FROM class;
SHOW INDEX FROM book;

# 开始 EXPLAIN 分析，左连接
EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card;
# 结论：type有ALL

# 添加索引优化
ALTER TABLE book ADD INDEX Y(card);

# 第2次EXPLAIN
EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card;
# 可以看到第二行的type变为ref, rows也变成了1，优化比较明显。
# 只是由于左连接特性决定的。LEFT JOIN 条件用于确定无论怎样从右表搜索行，左边一定都有。所有右边（从表）是我们的关键点，一定需要建立索引。

# 删除旧索引 + 新建 + 第3次EXPLAIN
DROP INDEX Y ON book;
ALTER TABLE class ADD INDEX X(card);
EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card;

# 开始 EXPLAIN 分析，右连接
EXPLAIN SELECT * FROM class RIGHT JOIN book ON class.card = book.card;
# 优化比较明显。这是因为RIGHT JOIN条件用于确定无论怎样从左表搜索行，右边一定都有，左右左边是我们的关键点，一定要建立索引。

DROP INDEX X ON class;
ALTER TABLE book ADD INDEX Y(card);