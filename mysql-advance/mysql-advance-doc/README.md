# MySQL 高级

## 1. MySQL 架构介绍

### 1.1. MySQL 简介

#### 1.1.1. 概述

#### 1.1.2. 高级MySQL

- mysql内核
- sql优化攻城狮
- mysql服务器的优化
- 各种参数常量设定
- 查询语句优化
- 主从复制
- 软硬件升级
- 容灾备份
- sql编程

### 1.2. MySQL Linux版安装

#### 1.2.1. MySQL 5.5

### 1.3. MySQL 配置文件

#### 1.3.1. 主要配置文件

### 1.4. MySQL 逻辑架构介绍

#### 1.4.1. 总体概述

#### 1.4.2. 查询说明

### 1.5. MySQL 存储引擎

#### 1.5.1. 查看命令

- 查看当前安装的MySQL提供的存储引擎

  ```mysql
  SHOW ENGINES;
  ```

- 查看当前MySQL默认的存储引擎

  ```mysql
  SHOW VARIABLES LIKE '%storage_engine%';
  ```

#### 1.5.2. MyISAM和InnoDB

|          | MyISAM                                                   | InnoDB                                                       |
| -------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| 主外键   | 不支持                                                   | 支持                                                         |
| 事务     | 不支持                                                   | 支持                                                         |
| 行表锁   | 表锁，即使操作一条记录也会锁住整个表，不适合高并发的操作 | 行锁，操作时只锁某一行，不对其他行有影响，**适合高并发的操作** |
| 缓存     | 只缓存索引，不缓存真实数据                               | 不仅缓存索引还要缓存真实数据，对内存要求较高，而且内存大小对性能有决定性的影响 |
| 表空间   | 小                                                       | 大                                                           |
| 关注点   | 性能                                                     | 事务                                                         |
| 默认安装 | Y                                                        | Y                                                            |

#### 1.5.3. 阿里巴巴、淘宝用哪个

## 2. 索引优化分析

### 2.1. 性能下降 SQL慢

- 查询语句写的烂
- 索引失效
- 关联查询太多join（设计缺陷或不得已的需求）
- 服务器调优及各个参数设置（缓存、线程等等）

### 2.2. 常见通用的JOIN查询

#### 2.2.1. SQL执行顺序

- 手写
- 机读
- 总结

#### 2.2.2. JOIN图

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fx7d9td26tj30e80b7tcd.jpg)

#### 2.2.3. 7种JOIN

```sql
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
```

### 2.3. 索引简介

#### 2.3.1. 索引是什么？

MySQL官方对索引的定义为：索引（Index）是帮助MySQL提高获取数据的数据结构。索引的本质：索引是数据结构。

可以简单理解为：排好序的快速查找数据结构。

一般来说索引本身也很大，不可能全部存储在内存中，因此索引往往以索引文件的形式存储在磁盘上。



结论：数据本身之外，数据库还维护者一个满足特定查找算法的数据结构，这些数据结构以某种方式指向数据，这样就可以在这些数据结构的基础上实现高级查找算法，这种数据结构就是索引。



平常所说的索引，如果没有特别指明，都是指B树（多路搜索树，并不一定是二叉的）结构组织的索引。其中聚集索引，次要索引，覆盖索引，复合索引，前缀索引，唯一索引默认都是使用B+树索引，统称索引。当然，除了B+树这种类型的索引之外，还有哈希（hash index）索引等。

#### 2.3.2. 索引的优劣势？

- 优势
  - 类似大学图书馆建书目索引，提高数据检索的效率，降低数据库的IO成本
  - 通过索引列对数据进行排序，降低数据排序的成本，降低了CPU的消耗
- 劣势
  - 实际上索引也是一张表，该表保存了主键与索引字段，并指向实体表的记录，所以索引列也是要占用空间的
  - 虽然索引大大提高了查询速度，同时却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE。因为更新表时，MySQL不仅要保存数据，还要保存一下索引文件每次更新添加了索引列的字段，都会调整因为更新所带来的键值变化后的索引信息。
  - 索引只是提高效率的一个因素，如果MySQL有大数据量的表，就需要花时间研究建立最优秀的索引，或优化查询语句

#### 2.3.3. MySQL索引分类

- 单值索引：即一个索引只包含单个列，一个表可以有多个单列索引

- 唯一索引：索引列的值必须唯一，但允许有空值

- 复合索引：即一个索引包含多个列

- 基本语法

  ```mysql
  # 创建
  CREATE INDEX 索引名 ON 表名(字段列表);
  ALTER TABLE 表名 ADD INDEX 索引名(字段列表);
  # 删除
  DROP INDEX 索引名 ON 表名;
  # 查看
  SHOW INDEX FROM 表名;
  ```

- 使用`ALTER`命令

  ```mysql
  # 四种添加索引的方式
  # 该语句添加一个主键，这意味着索引值必须是唯一的，且不能为NULL
  ALTER TABLE 表名 ADD PRIMARY KEY(字段列表);
  # 该语句创建索引的值必须是唯一的（除了NULL外，NULL可能会出现多次）
  ALTER TABLE 表名 ADD UNIQUE 索引名(字段列表);
  # 添加普通索引，索引值可出现多次
  ALTER TABLE 表名 ADD INDEX 索引名(字段列表);
  # 该语句指定了索引为FULLTEXT，用于全文索引
  ALTER TABLE 表名 ADD FULLTEXT 索引名(字段列表);
  ```

#### 2.3.4. MySQL索引结构

- BTree索引
- Hash索引
- full-text全文索引
- R-Tree索引

#### 2.3.5. 索引的使用场景

- 哪些情况需要创建索引？
  - 主键自动建立唯一索引
  - 查询中与其他表关联的字段，外键关系建立索引
  - 频繁作为查询条件的字段
  - 查询中排序的字段，排序字段若通过索引去访问将大大提高排序速度
  - 查询中统计或者分组字段
- 哪些情况下不要创建索引？
  - 表记录太少
  - 经常增删改的表：索引提高了查询速度，同时会降低更新表的速度，因为在更新表时，MySQL不仅要保存数据，还要保存一下索引文件
  - 频繁更新的字段：每次更新不单单更新了记录还更新索引，加重了IO负担
  - 数据重复且分布平均的字段，因此应该只为最经常查询和排序的数据列建立索引。注意：如果某个数据列包含许多重复的内容，为它建立索引就没有太大的实际效果。

### 2.4. 性能分析

#### 2.4.1. MySQL Query Optimizer

#### 2.4.2. MySQL常见瓶颈

- CPU：CPU在饱和的时候一般发生在数据装入内存或从磁盘上读取数据的时候
- IO：磁盘I/O瓶颈发生在装入数据远大于内存容量的时候
- 服务器硬件的性能瓶颈：top,free,iostat和vmstat来查看系统的性能状态

#### 2.4.3. `Explain`

##### 2.4.3.1. `Explain`是什么（查看执行计划）？

使用`EXLPAIN`关键字可以模拟优化器执行SQL查询语句，从而知道MySQL是如何处理SQL语句的。分析查询语句或是表结构的性能瓶颈。

##### 2.4.3.2. `Explain`能干嘛？

- 表的读取顺序
- 数据读取操作的操作类型
- 哪些索引可以使用
- 哪些索引被实际使用
- 表之间的引用
- 每张表有多少行被优化器查询

##### 2.4.3.3. `Explain`怎么用？

- EXPALAIN + SQL语句

  ```mysql
  EXPLAIN SQL语句;
  ```

- 执行计划包含的信息

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fx7egvsxchj30pq01bglh.jpg)

##### 2.4.3.4. `Explain`各字段解释

- `id`

  - `SELECT`查询的序列号，包含一组数字，表示查询中执行`SELECT`子句或操作表的顺序
  - 三种情况
    - `id`相同：执行顺序由上至下
    - `id`不同：如果是子查询，`id`的序号会递增，`id`值越大优先级越高，优先被执行
    - `id`相同又不同：同时存在

- `select_type`：查询的类型，主要是用于区别 普通查询、联合查询、子查询等的复杂查询

  - `SIMPLE`：简单的SELECT查询，查询中不包含子查询或者UNION
  - `PRIMARY`：查询中若包含任何复杂的子部分，最外层查询则被标记为PRIMARY
  - `SUBQUERY`：在SELECT或WHERE列表中包含了子查询
  - `DERIVED`：在FROM列表中包含的子查询被标记为DERIVED（衍生），MySQL会递归执行这些子查询，把结果放在临时表里。
  - `UNION`：若第二个SELECT出现在UNION之后，则被标记为UNION；若UNION包含在FROM子句的子查询中，外层SELECT将被标记为：DERIVED
  - `UNION RESULT`：从UNION表获取结果的SELECT

- `table：`显示这一行的数据是关于哪张表的

- `partitions`

- `type`：访问类型

  - 访问类型排列

    - `system`：表只有一行记录（等于系统表），这是const类型的特例，平时不会出现，这个也可以忽略不计
    - `const`：表示通过索引一次就招到了，const用于比较primary key或者unique索引。因为只匹配一行数据，所以很快。如将主键置于where列表中，MySQL就能将该查询转换位一个常量
    - `eq_ref`：唯一性索引扫描，对于每个索引键，表中只有一条记录与之匹配。常见于主键或唯一索引扫描
    - `ref`：非唯一性索引扫描，返回匹配某个单独值的所有行，本质上也是一种索引访问，它返回所有匹配某个单独值的行。然而，它可能会找到多个符合条件的行，所以他应该属于查找和扫描的混合体
    - `range`：只检索给定范围的行，使用一个索引来选择行。key列显示使用了哪个索引。一般就是在where语句中出现了between, <, >, in等的查询。最后只能怪范围扫描索引扫描比权标要好，因为它只需要开始于索引的某一点，而结束于另一点，不用扫描全部索引。
    - `index`：Full Index Scan，index与ALL区别为index类型只遍历索引树。这通常比ALL快，因为索引文件通常比数据文件小。（也就是说虽然ALL和Index都是读全表，但index是从索引中读取的，而ALL是从硬盘中读取的）
    - `ALL`：Full Table Sacn，将遍历全表以找到匹配的行

  - 访问类型从最好到最差的顺序

    ```ini
    # 一般来说，要保证查询至少达到 range 级别，最好能达到 ref 级别。
    system > const > eq_ref > ref > range > index > ALL
    ```

- `possible_keys`：显示可能应用在这张表中的索引，一个或多个。查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询实际使用。

- `key`：实际使用的索引。如果为NULL，则没有使用索引。查询中若使用了覆盖索引，则该索引和查询的SELECT字段重叠

- `key_len`：表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度。在不损失精确性的情况下，长度越短越好。ke_len显示的值为索引字段的最大可能长度，并非实际使用长度，即key_len是根据表定义计算而得，不是通过表内检索出的

- `ref`：显示索引的那一列被使用了，如果可能的话，是一个常数。哪些列或常量被用于查找索引列上的值

- `rows`：根据表统计信息及索引选用情况，大致估算出找到所需的记录所需要读取的行数

- `filtered`

- `Extra`：包含不适合在其他列中显示但十分重要的额外信息

  - `Using filesort`：说明MySQL会对数据使用一个外部的索引排序，而不是按照表内的索引顺序进行读取。MySQL中无法利用索引完成的排序操作称为"文件排序"
  - `Using temporary`：使用了临时表保存中间结果，MySQL在对查询结果排序时使用临时表。常见于排序order by和分组查询group by。
  - `Using index`：表示相应的select操作中使用了覆盖索引，避免访问了表的数据航，效率不错；如果同时出现Using where，表名索引被用来执行索引键值的查找；如果没有同时出现Using where，表名索引用来读取数据而非执行查找动作。
  - `Using where`：使用了where过滤
  - `Using join buffer`：使用了连接缓存
  - `impossible where`：whre子句的值总是false，不能用来获取任何元祖
  - `select tables optimized away`
  - `distinct`：优化distinct操作，在找到第一匹配的元组后即停止找同样值的动作

##### 2.4.3.5. 热身`Case`

### 2.5. 索引优化

#### 2.5.1. 索引分析

##### 2.5.1.1. 单表

```mysql
-- 索引分析_单表
USE mysql_advance;
DROP TABLE IF EXISTS article;
CREATE TABLE article (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	author_id INT UNSIGNED NOT NULL,
	category_id INT UNSIGNED NOT NULL,
	views INT UNSIGNED NOT NULL,
	comments INT UNSIGNED NOT NULL,
	title VARBINARY(256) NOT NULL,
	content TEXT NOT NULL
);

INSERT INTO article(author_id, category_id, views, comments, title, content) VALUES
(1, 1, 1, 1, '1', '1'),
(2, 2, 2, 2, '2', '2'),
(1, 1, 3, 3, '3', '3');

SELECT * FROM article;
SHOW INDEX FROM article;

# 查询 category_id = 1 且 comments > 1 的情况下，views 最多的 id, author_id
EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;

# 结论：很显然type是ALL，即最坏的情况。Extra中还出现了Using filesort，也是最坏的情况，优化是必须的。

# 开始优化
# 1.1. 新建索引
# ALTER TABLE article ADD INDEX idx_article_ccv(category_id, comments, views);
CREATE INDEX idx_article_ccv ON article(category_id, comments, views);

# 1.2. 第2次EXPLAIN
EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;
EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments = 3 ORDER BY views DESC LIMIT 1;
# 结论：
# type变成了range，这是可以忍受的。但是Extra里使用Using filesort仍是无法接受的。
# 但是我们已经建立了索引，为啥没用呢？
# 这是因为按照BTree索引的工作原理
# 先排序 category_id
# 如果遇到相同的 category_id 则再排序 comments，如果遇到相同的 comments 则再排序 views
# 当 comments 字段在联合索引里处于中间位置时
# 因 comments > 1 条件是一个范围值（所谓range）
# MySQL 无法利用索引再对后面的 views 部分进行检索，即 range 类型查询字段后面的索引无效。

# 1.3. 删除第一次建立的索引
DROP INDEX idx_article_ccv ON article;

# 1.4. 第2次新建索引
CREATE INDEX idx_article_cv ON article(category_id, views);

# 1.5. 第3次EXPLAIN
EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;
# 结论：可以看到，type 变为了ref，Extra中的Using filesort也消失了，结果非常理想
DROP INDEX idx_article_cv ON article;
```

##### 2.5.1.2. 两表

```mysql
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
# 这是由于左连接特性决定的。LEFT JOIN 条件用于如何从右表搜索行，左边一定都有。所以右边（从表）是我们的关键点，一定需要建立索引。

# 删除旧索引 + 新建 + 第3次EXPLAIN
DROP INDEX Y ON book;
ALTER TABLE class ADD INDEX X(card);
EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card;

# 开始 EXPLAIN 分析，右连接
EXPLAIN SELECT * FROM class RIGHT JOIN book ON class.card = book.card;
# 优化比较明显。这是因为RIGHT JOIN条件用于确定如何从左表搜索行，右边一定都有，所以左边（从表）是我们的关键点，一定要建立索引。
```

##### 2.5.1.3. 三表

```mysql
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
```

##### 2.5.1.3. JOIN语句的优化总结

- 尽可能减少join语句中的NestedLoop（嵌套循环）的循环总次数：永远用小结果集驱动大的结果集
- 优先优化NestedLoop的内层循环
- 保证join语句的被驱动表上join条件字段已经被索引
- 当无法保证被驱动表的join条件字段被索引且内存资源充足的前提下，不要太吝惜JoinBuffer的设置

#### 2.5.2. 索引失效（应该避免）

##### 2.5.2.1. 索引失效案例

```mysql
-- 索引失效案例
USE mysql_advance;
DROP TABLE IF EXISTS staffs;
CREATE TABLE staffs (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '姓名',
	age INT NOT NULL DEFAULT 0 COMMENT '年龄',
	pos VARCHAR(32) NOT NULL DEFAULT '' COMMENT '职位',
	add_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间'
) COMMENT '员工记录表';

INSERT INTO staffs(`name`, age, pos, add_time) VALUES
('z3', 22, 'manager', NOW()),
('July', 23, 'dev', NOW()),
('2000', 23, 'dev', NOW());

SELECT * FROM staffs;
SHOW INDEX FROM staffs;

# 添加复合索引
ALTER TABLE staffs ADD INDEX idx_staffs_nameAgePos(`name`, age, pos);

-- 1. 全值匹配我最爱
EXPLAIN SELECT * FROM staffs WHERE `name` = 'July';
EXPLAIN SELECT * FROM staffs WHERE `name` = 'July' AND age = 25;
EXPLAIN SELECT * FROM staffs WHERE `name` = 'July' AND age = 25 AND pos = 'dev';

-- 2. 最佳左前缀法则：如果索引了多列，要遵守最左前缀法则，指的是查询从索引的最左前列开始并且 不跳过索引中的列 。
EXPLAIN SELECT * FROM staffs WHERE pos = 'dev';
EXPLAIN SELECT * FROM staffs WHERE `name` = 'July' AND pos = 'dev';
-- 2.1. MySQL 底层调优查询顺序
EXPLAIN SELECT * FROM staffs WHERE pos = 'dev' AND age = 25 AND `name` = 'July';

-- 3. 不在索引列上做任何操作（计算、函数、（自动or手动）类型转换），会导致索引失效而转向全表扫描
EXPLAIN SELECT * FROM staffs WHERE LEFT(`name`, 4) = 'July';

-- 4. 存储引擎不能使用索引中范围条件右边的列
EXPLAIN SELECT * FROM staffs WHERE `name` = 'July' AND age > 25 AND pos = 'dev';

-- 5. 尽量使用覆盖索引（只访问索引的查询（索引列和查询列一致）），减少 SELECT *
EXPLAIN SELECT `name`, age, pos FROM staffs WHERE `name` = 'July' AND age = 25 AND pos = 'dev';
EXPLAIN SELECT `name`, age, pos FROM staffs WHERE `name` = 'July' AND age > 25 AND pos = 'dev';

-- 6. MySQL 在使用不等于（!= 或 <>）的时候无法使用索引会导致全表扫描
EXPLAIN SELECT * FROM staffs WHERE `name` != 'July';
EXPLAIN SELECT * FROM staffs WHERE `name` <> 'July';

-- 7. IS NULL, IS NOT NULL也无法使用索引
EXPLAIN SELECT * FROM staffs WHERE `name` IS NULL;
EXPLAIN SELECT * FROM staffs WHERE `name` IS NOT NULL;

-- 8. LIKE 以通配符开头（'%abc...'）MySQL索引失效会变成全表扫描的操作
EXPLAIN SELECT * FROM staffs WHERE `name` LIKE '%July%';
EXPLAIN SELECT * FROM staffs WHERE `name` LIKE '%July';
EXPLAIN SELECT * FROM staffs WHERE `name` LIKE 'July%';

-- 9. 字符串不加单引号会导致索引失效
EXPLAIN SELECT * FROM staffs WHERE `name` = 2000;

-- 10. 少用or，用它来连接时会导致索引失效
EXPLAIN SELECT * FROM staffs WHERE `name` = 'July' OR `name` = 'z3';
```

##### 2.5.2.2. 索引失效小结

- 假设`index(a, b, c)`

  | where 语句                                            | 索引是否被使用                          |
  | ----------------------------------------------------- | --------------------------------------- |
  | `where a = 3`                                         | √  使用到a                              |
  | `where a = 3 and b = 5`                               | √  使用到a, b                           |
  | `where a = 3 and b = 5 and c = 4`                     | √  使用到a, b, c                        |
  | `where b = 3 或 where b = 3 and c = 4 或 where c = 4` | ×                                       |
  | `where a = 3 and c = 5`                               | √  使用到a；但是c不可以，b中间断了      |
  | `where a = 3 and b > 4 and c = 5`                     | √  使用到a, b；c不能用在范围之后，b断了 |
  | `where 3 = 3 and b like 'kk%' and c = 4`              | √  使用到a, b；c不能用在范围之后，b断了 |

##### 2.5.2.3. 解决Like索引失效

```mysql
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
```

##### ==2.5.2.4. 索引优化口诀==

```ini
全值匹配我最爱，最左前缀要遵守；
带头大哥不能死，中间兄弟不能断；
索引列上少计算，范围之后全失效；
LIKE百分写最右，覆盖索引不写星；
不等空值还有OR，索引失效要少用；
字符引号不可丢，SQL高级也不难。
```

#### 2.5.3. 一般性建议

- 对于单键索引，尽量选择对当前query过滤性更好的索引
- 在选择组合索引的时候，当前query中过滤性最好的字段在索引字段顺序中，位置越靠前越好
- 在选择组合索引的时候，尽量选择可以能够包含当前query中的where子句中更多字段的索引
- 尽可能通过分析统计信息和调整query的写法达到选择合适索引的目的

## 3. 查询截取分析

### 3.1. 查询优化

### 3.2. 慢查询日志

### 3.3. 批量数据脚本

### 3.4. Show profile

### 3.5. 全局日志查询

## 4. MySQL 锁机制

### 4.1. 概述

### 4.2. 三锁

## 5. 主从复制

### 5.1. 复制的基本原理

### 5.2. 复制的基本原则

### 5.3. 复制的最大问题

### 5.4. 一主一从常见配置