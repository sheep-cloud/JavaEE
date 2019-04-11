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

#### 2.2.3. 7种通用JOIN

```sql
/*
	7种通用JOIN：
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



结论：数据本身之外，数据库还维护着一个满足特定查找算法的数据结构，这些数据结构以某种方式指向数据，这样就可以在这些数据结构的基础上实现高级查找算法，这种数据结构就是索引。



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
  - `UNION RESULT`：从UNION表获取结果的SELECT

- `table：`显示这一行的数据是关于哪张表的

- `partitions`：匹配的分区信息

- `type`：访问类型

  - 访问类型排列

    - `system`：表只有一行记录（等于系统表），这是const类型的特例，平时不会出现，这个也可以忽略不计
    - `const`：表示通过索引一次就找到了，const用于比较primary key或者unique索引。因为只匹配一行数据，所以很快。如将主键置于where列表中，MySQL就能将该查询转换位一个常量
    - `eq_ref`：唯一性索引扫描，对于每个索引键，表中只有一条记录与之匹配。常见于主键或唯一索引扫描
    - `ref`：非唯一性索引扫描，返回匹配某个单独值的所有行，本质上也是一种索引访问，它返回所有匹配某个单独值的行。然而，它可能会找到多个符合条件的行，所以他应该属于查找和扫描的混合体
    - `range`：只检索给定范围的行，使用一个索引来选择行。key列显示使用了哪个索引。一般就是在where语句中出现了between, <, >, in等的查询。范围扫描索引比全表要好，因为它只需要开始于索引的某一点，而结束于另一点，不用扫描全部索引。
    - `index`：Full Index Scan，index与ALL区别为index类型只遍历索引树。这通常比ALL快，因为索引文件通常比数据文件小。（也就是说虽然ALL和Index都是读全表，但index是从索引中读取的，而ALL是从硬盘中读取的）
    - `ALL`：Full Table Sacn，将遍历全表以找到匹配的行

  - 访问类型从最好到最差的顺序

    ```ini
    # 一般来说，要保证查询至少达到 range 级别，最好能达到 ref 级别。
    type级别: system > const > eq_ref > ref > range > index > ALL
    ```

- `possible_keys`：显示可能应用在这张表中的索引，一个或多个。查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询实际使用。

- `key`：实际使用的索引。如果为NULL，则没有使用索引。查询中若使用了覆盖索引，则该索引和查询的SELECT字段重叠

- `key_len`：表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度。在不损失精确性的情况下，长度越短越好。ke_len显示的值为索引字段的最大可能长度，并非实际使用长度，即key_len是根据表定义计算而得，不是通过表内检索出的

- `ref`：显示索引的那一列被使用了，如果可能的话，是一个常数。哪些列或常量被用于查找索引列上的值

- `rows`：根据表统计信息及索引选用情况，大致估算出找到所需的记录所需要读取的行数

- `filtered`：`rows * filtered / 100` 表示该步骤最后得到的行数(估计值)。

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
  | `where c = 5 and b = 3 and a = 2`                     | √  使用到a, b, c；MySQL底层调优查询顺序 |

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

- 对于单值索引，尽量选择对当前query过滤性更好的索引
- 在选择组合索引的时候，当前query中过滤性最好的字段在索引字段顺序中，位置越靠前越好
- 在选择组合索引的时候，尽量选择可以能够包含当前query中的where子句中更多字段的索引
- 尽可能通过分析统计信息和调整query的写法达到选择合适索引的目的

## 3. 查询截取分析

### 3.1. 查询优化

#### 3.1.1. 永远小表驱动大表

```mysql
-- 小表驱动大表，类似嵌套循环Nested Loop

# 优化原则：小表驱动大表，即小的数据集驱动大的数据集

/*	
	当B表的数据集小于A表的数据集时，用IN优于EXISTS
	
	SELECT * FROM A WHERE id IN (
	    SELECT id FROM B
	);
	相当于：
	for SELECT id FROM B
	for SELECT * FROM A WHERE A.id = B.id
	
        for (int i = 0; i < (SELECT id FROM B).length; i++) {
            for (...) {
                ...
            }
        }
	
	
	当B表的数据集大于A表的数据集时，用EXISTS优于IN
	
	SELECT * FROM A WHERE EXISTS (
	    SELECT 1 FROM B WHERE B.id = A.id
	);
	相当于：
	for SELECT * FROM A
	for SELECT * FROM B WHERE B.id = A.id
	
        for (int i = 0; i < (SELECT id FROM A).length; i++) {
            for (...) {
                ...
            }
        }
	
	注意：A与B表的id字段应建立索引
	
	
	# EXISTS 公式：
	SELECT 查询列表 FROM 表名 WHERE EXISTS (
	    subquery
	);
	该语法可以理解为：将主查询的数据，放到子查询中做条件验证，根据验证结果(TRUE或FALSE)来决定主查询的数据结果是否得以保留。
	
	# 提示
	1. EXISTS (subquery) 只返回 TRUE或FALSE，因此子查询中的 SELECT * 也可以是 SELECT 1 或其他。官方说法是实际执行时会忽略 SELECT 清单，因此没有区别。
	2. EXISTS 子查询的实际执行过程可能经过了优化而不是我们理解上的逐条对比，如果担忧效率问题，可进行实际校验以确定是否有效率问题。
	3. EXISTS 子查询往往也可以用条件表达式、其他子查询或者JOIN来替代，何种最优需要具体问题具体分析。
*/

SELECT * FROM tbl_emp;
SELECT * FROM tbl_dept;

EXPLAIN
SELECT * FROM tbl_emp e WHERE e.dept_id IN (
    SELECT d.id FROM tbl_dept d
);

EXPLAIN
SELECT * FROM tbl_emp e WHERE EXISTS (
    SELECT 1 FROM tbl_dept d WHERE d.id = e.dept_id
);
```

#### 3.1.2. `ORDER BY`关键字优化

- ORDER BY子句，尽量使用index方式排序，避免使用FileSort方式排序

  - MySQL支持两种方式的排序，FileSort和Index，Index效率高。它指MySQL扫描索引本身完成排序。FileSort方式效率较低。
  - **ORDER BY满足两种情况，会使用Index方式排序**
    - **ORDER BY语句使用索引最左前列**
    - **使用WHERE子句与ORDER BY子句条件组合满足索引最左前列**

- 尽可能在索引列上完成排序操作，遵照索引建的最佳左前缀

- 如果不在索引列上，filesort有两种算法，mysql就要启动双路排序和单路排序

  - 双路排序：MySQL4.1之前使用，扫描两次磁盘，最终得到数据。
  - 单路排序：使用更多的空间，因为把每一行都保存在内存中了。

- 优化策略

  - ORDER BY 时 `SELECT *` 是一个大忌。
  - 增大sort_buffer_size参数的设置
  - 增大max_length_for_sort_data参数的设置

- ORDER BY 产生 Using filesort 案例

  ```mysql
  -- order by 关键字优化
  USE mysql_advance;
  DROP TABLE IF EXISTS tbl_A;
  CREATE TABLE tbl_A (
  	id INT PRIMARY KEY AUTO_INCREMENT,
  	age INT,
  	birth TIMESTAMP NOT NULL
  );
  
  INSERT INTO tbl_A(age, birth) VALUES
  (22, NOW()),
  (23, NOW()),
  (24, NOW());
  
  # 添加索引
  ALTER TABLE tbl_A ADD INDEX idx_A_ageBirth(age, birth);
  
  SELECT * FROM tbl_A;
  SHOW INDEX FROM tbl_A;
  
  # 以下方式会产生 Using filesort
  EXPLAIN
  SELECT * FROM tbl_A WHERE age > 20 ORDER BY birth;
  
  EXPLAIN
  SELECT * FROM tbl_A WHERE age > 20 ORDER BY birth, age;
  
  EXPLAIN
  SELECT * FROM tbl_A ORDER BY birth;
  
  EXPLAIN
  SELECT * FROM tbl_A WHERE birth > '2017-11-15 22:26:33' ORDER BY birth;
  
  EXPLAIN
  SELECT * FROM tbl_A ORDER BY age ASC, birth DESC;
  ```

#### 3.1.3. `GROUP BY`关键字优化

- GROUP BY 实质是先排序后进行分组，遵照索引建的最左做前缀
- 当无法使用索引列，增大`max_length_sort_data`参数的设置+增大`sort_buffer_size`参数的设置
- WHERE高于HAVING，能写在WHERE限定的条件就不要去HAVING限定了

### 3.2. 慢查询日志

#### 3.2.1. 慢查询日志是什么？

- MySQL的慢查询日志是MySQL提供的一种日志记录，它用来记录在MySQL中响应事件超过阈值的语句，具体指运行事件超过`long_query_time`值的SQL，则会被记录到慢查询日志中。
- 具体指运行事件超过`long_query_time`值的SQL，则会被记录到慢查询日志中。`long_query_time`的默认值为10，意思是运行10秒以上的语句。
- 由慢查询日志来查看哪些SQL超出了最大忍耐时间值，比如一条sql执行超过5秒钟，就算慢SQL，希望能收集超过5秒的sql，结合之前explain进行全面分析。

#### 3.2.2. 慢查询日志怎么用？

- 默认情况下，MySQL数据库没有开启慢查询日志，需要手动来设置这个参数。如果不是调优需要的话，一般不建议启动该参数。

- 慢查询日志的开启

  ```mysql
  -- 慢查询日志的开启
  # 查看慢查询日志是否开启；默认情况下slow_query_log的值为OFF，表示慢查询日志是禁用的。
  SHOW VARIABLES LIKE '%slow_query_log%';
  
  # 开启慢查询日志
  SET GLOBAL slow_query_log = 1;
  
  # 关闭慢查询日志
  SET GLOBAL slow_query_log = 0;
  
  # 如果要永久生效，就必须修改配置文件my.cnf（其他系统变量也是如此）
  
  # 修改my.cnf文件，[mysqld]下增加或修改参数
  # slow_query_log和slow_query_log_file后，然后重启MySQL服务器。也追加如下两行配置进行my.cnf文件
  
  # slow_query_log=1
  # linux： 	slow_query_log_file=/var/lib/mysql/xxx-slow.log
  # windiows：	slow_query_log_file=D:\Sybase\mysql-5.7.19-winx64\data\Black-Cloud-slow.log
  
  # 关于慢查询的参数slow_query_log_file，它指定慢查询日志文件的存放路径，系统默认会给一个缺省的文件host_name-slow.log（如果没有指定参数slow_query_log_file的话）
  ```

- 什么样的SQL会记录到慢查询日志里？

  ```mysql
  # 什么样的SQL会记录到慢查询日志里？
  /*
  	这个是由参数long_query_time控制，默认情况下long_query_time的值为10秒。保存大于10秒的，而不是大于等于
  	命令：SHOW VARIABLES LIKE '%long_query_time%';
  	
  	可以使用命令修改，也可以在my.cnf参数里面修改。 
  	
  	如果要永久生效，就必须修改配置文件my.cnf
  	long_query_time=3
  	log_output=FILE
  */
  # 查看当前多少秒算慢
  SHOW VARIABLES LIKE '%long_query_time%';
  
  # 设置慢查询的阈值时间，设置以后需要重新连接或重新开一个回话才能看到修改值
  SET GLOBAL long_query_time = 3;
  
  # 回复慢查询的阈值时间
  SET GLOBAL long_query_time = 10;
  
  # 使用新的命令查看慢查询的阈值时间
  SHOW GLOBAL VARIABLES LIKE '%long_query_time%';
  
  # 测试是否记录到日志
  SELECT SLEEP(4);
  
  # 查看慢查询日志记录的条数
  SHOW GLOBAL STATUS LIKE '%Slow_queries%';
  ```

#### 3.2.3. 日志分析工具`mysqldumpslow`

在生产环境中，如果要手工分析日志，查找、分析SQL，显然是个体力活，MySQL提供了日志分析工具`mysqldumpslow`

- 查看`mysqldumpslow`的帮助信息
- 工作常用参考

### 3.3. 批量数据脚本

```mysql
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

# 2. 设置参数log_big_trust_function_creators
/*
	创建函数，假如报错：This function has none of DETERMINISTIC......
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
# 5.1. 往dept表添加10条数据
TRUNCATE TABLE dept;
CALL insert_dept(100, 10);
SELECT * FROM dept;

# 5.2. 往emp表添加50W条数据
TRUNCATE TABLE emp;
CALL insert_emp(100001, 500000);
SELECT * FROM emp;
```

### 3.4. Show profile

### 3.5. 全局查询日志

## 4. MySQL 锁机制

### 4.1. 概述

#### 4.1.1. 定义

- 锁是计算机协调多个进程或线程并发访问某一资源的机制。
  - 在数据库中，除传统的计算资源（如CPU、RAM、I/O等）的争用意外，数据也是一种供许多用户共享的资源。如何保证数据并发访问的一致性、有效性是所有数据库必须解决的一个问题，锁冲突也是影响数据库并发访问性能的一个重要因素。从这角度来说，锁对数据库而言显得尤其重要，也更加复杂。
- 举例：生活购物
  - 商品只有一件库存，这个时候如果还有另一个人买，如何解决是自己买到还是另一个人买到？这里肯定要用到事务，先从库存表中取出物品数量，然后插入订单，付款后插入付款表信息，然后更新商品数量。在这个过程中，使用锁可以对有限的资源进行保护，解决隔离和并发的矛盾。

#### 4.1.2. 锁的分类

- 从对数据操作的类型（读/写）分
  - 读锁（共享锁）：针对同一份数据，多个读操作可以同时进行而不会互相影响。
  - 写锁（排它锁）：当前写操作没有完成前，它会阻断其它写锁和读锁。
- 从对数据操作的粒度分
  - 表锁
  - 行锁

### 4.2. 三锁

#### 4.2.1. 表锁（偏读）

##### 4.2.1.1. 特点

偏向MyISAM存储引擎，开销小、加锁快；无死锁；锁定粒度大，发生锁冲突的概率最高，并发度最低。

##### 4.2.1.2. 案例分析

```mysql
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
```

##### 4.2.1.3. 案例结论

MyISAM在执行查询语句（SELECT）前，会自动给涉及的所有表加读锁，在执行增删改操作前，会自动给涉及的表加写锁。

MySQL的表锁有两种模式：

- 表共享读锁（Table Read Lock）

- 表独占写锁（Table Write Lock）

| 锁类型 | 可否兼容 | 读锁 | 写锁 |
| ------ | -------- | ---- | ---- |
| 读锁   | 是       | 是   | 否   |
| 写锁   | 是       | 否   | 否   |

- 结论
  - 对MyISAM表的读操作（加读锁），不会阻塞其他进程对同一表的读请求，但会阻塞对同一表的写请求。只有当读锁释放后，才会执行其他进程的写操作。
  - 对MyISAM表的写操作（加写锁），会阻塞其他进程对同一表的读和写操作，只有当写锁释放后，才会执行其他进程的读写操作。
- **简而言之，就是读锁会阻塞写，但是不会阻塞读，而写锁则会把读和写都堵塞。**

##### 4.2.1.4. 表锁分析

- 查看哪些表被加锁

  ```mysql
  # 查看表上加过的锁，In_use > 0 表示被加锁
  SHOW OPEN TABLES FROM mysql_advance;
  ```

- 如何分析表锁定

  ```mysql
  # 通过检查 Table_locks_waited 和 Table_locks_immediate 状态变量来分析系统上的表锁定；
  SHOW STATUS LIKE 'table%'
  
  # Table_locks_waited：产生表级锁定的次数，表示可以立即获取锁的查询次数，每立即获取锁值加1；
  # Table_locks_immediate：出现表级锁定争用而发生等待的次数（不能立即获取锁的次数，每等待一次锁值加1），此值高则说明存在着比较严重的表级锁争用情况。
  ```

- **MyISAM的读写锁调度是写优先，这也是MyISAM不适合做写为主表的引擎。因为写锁后，其他线程不能做任何操作，大量的更新会使查询很难得到锁，从而造成永远阻塞。**

#### 4.2.2. 行锁（偏写）

##### 4.2.2.1. 特点

偏向InnoDB存储引擎，开销大，加锁慢；会出现死锁；锁定粒度最小，发生锁冲突的概率最低，并发度也很高。

InnoDB与MyISAM的最大不同点：一是支持事务（TRANSACTION）；二是采用了行级锁。

##### 4.2.2.2. 案例分析

###### 4.2.2.2.1. SQL

```mysql
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
```

###### 4.2.2.2.2. 无索引行锁升级为表锁

- 行锁实现方式
  - **InnoDB行锁是通过给索引上的索引项加锁来实现的**，这一点MySQL与Oracle不同，后者是**通过在数据库中对相应数据行加锁来实现的。InnoDB这种行锁实现特点意味着：只有通过索引条件检索数据，InnoDB才使用行级锁，否则，InnoDB将使用表锁！**

###### 4.2.2.2.3. 间隙锁

- 什么是间隙锁
  - 当使用范围条件而不是相等条件检索数据，并请求共享或排他锁时，InnoDB会给符合条件的已有数据记录的索引项加锁；对于键值在条件范围内但并不存在的记录，叫做“间隙（GAP）”，InnoDB也会对这个“间隙”加锁，这种锁机制就是所谓的间隙锁（Next-Key锁）。
- 间隙锁危害
  - 因为Query执行过程中通过范围查找的话，他会锁定整个范围内所有的索引键值，即使这个键值并不存在。间隙锁有一个比较致命的弱点，就是当锁定一个范围键值之后，即使某些不存在的键值也会被无辜的锁定，而造成锁定的时候无法插入锁定键值范围内的任何数据。在某些场景下这可能会对性能造成很大的危害。

###### 4.2.2.2.4. 如何锁定一行

##### 4.2.2.3. 案例结论

##### 4.2.2.4. 行锁分析

##### 4.2.2.5. 优化建议

- 尽可能让所有数据检索都通过索引来完成，避免无索引行锁升级为表锁
- 合理设计索引，尽量缩小锁的范围
- 尽可能减少检索条件，避免间隙锁
- 尽量控制事务大小，减少锁定资源量和时间长度
- 尽可能低级别事务隔离

#### 4.2.3. 页锁

开销和加锁时间界于表锁和行锁之间；会出现死锁；锁定粒度界于表锁和行锁之间，并发度一般

## 5. 主从复制

### 5.1. 复制的基本原理

- slave会从master读取binlog来进行数据同步
- MySQL复制过程分为三步
  - master将改变记录到二进制日志（binary log）。这些记录过程叫做二进制日志事件。binary log events
  - slave将master的binary log events拷贝到它的中继日志（relay log）
  - slave重做中继日志中的事件，将改变应用到自己的数据库中。MySQL复制是异步的且串行化的

### 5.2. 复制的基本原则

- 每个slave只有一个master
- 每个slave只能有一个唯一的服务器id
- 每个master可以有多个salve

### 5.3. 复制的最大问题

### 5.4. 一主一从常见配置