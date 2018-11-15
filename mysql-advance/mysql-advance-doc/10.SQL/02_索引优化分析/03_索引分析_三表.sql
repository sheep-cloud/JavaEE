-- 索引分析_三表（联合两表）
USE mysql_advance;
DROP TABLE IF EXISTS phone;
CREATE TABLE phone (
	phone_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	card INT UNSIGNED NOT NULL
);

INSERT INTO phone(card) VALUES
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

SELECT * FROM phone;
SHOW INDEX FROM phone;

# 添加索引优化
ALTER TABLE phone ADD INDEX Z(card);
ALTER TABLE book ADD INDEX Y(card);

# 开始 EXPLAIN 分析，三表左连接
EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card LEFT JOIN phone ON book.card = phone.card;
# 后两行的 type 都是 ref，且总rows优化很好，效果不错。因此索引最好设置在从表里需要经常查询的字段中。

# 结论：
/*
	JOIN语句的优化：
		1. 尽可能减少Join语句中的NestedLoop（嵌套循环）的循环总次数：“永远用小结果集驱动大的结果集”。
		2. 优先优化NestedLoop的内层循环。
		3. 保证Join语句被驱动表上Join条件字段已经被索引。
		4. 当无法保证被驱动表的Join条件字段被索引且内存资源充足的前提下，不要太吝惜JoinBuffer的设置。
*/