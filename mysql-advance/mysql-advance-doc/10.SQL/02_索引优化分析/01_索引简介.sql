/*
	1. 索引是什么？
		MySQL官方对索引的定义为：索引（Index）是帮助MySQL高效获取数据的数据结构。索引的本质：索引是数据结构。
		可以简单理解为：排好序的快速查找数据结构。
		一般来说索引本身也很大，不可能全部存储在内存中，因此索引往往以索引文件的形式存储在磁盘上。
		
		结论：数据本身之外，数据库还维护者一个满足特定查找算法的数据结构，这些数据结构以某种方式指向数据，
			这样就可以在这些数据结构的基础上实现高级查找算法，这种数据结构就是索引。
		
		平常所说的索引，如果没有特别指明，都是指B树（多路搜索树，并不一定是二叉的）结构组织的索引。
		其中聚集索引，次要索引，覆盖索引，复合索引，前缀索引，唯一索引默认都是使用B+树索引，统称索引。当然，除了B+树这种类型的索引之外，还有哈希索引等。
		
	2. 索引的优劣势？
		优势：
			类似大学图书馆建书目索引，提高数据检索的效率，降低数据库的IO成本
			通过索引列对数据进行排序，降低数据排序的成本，降低了CPU的消耗
		劣势：
			实际上索引也是一张表，该表保存了主键与索引字段，并指向实体表的记录，所以索引列也是要占用空间的
			虽然索引大大提高了查询速度，同时却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE。
			因为更新表时，MySQL不仅要保存数据，还要保存一下索引文件每次更新添加了索引列的字段，都会调整因为更新所带来的键值变化后的索引信息。
			
			索引只是提高效率的一个因素，如果MySQL有大数据量的表，就需要花时间研究建立最优秀的索引，或优化查询语句
			
	3. MySQL索引分类：
		3.1. 单值索引：即一个索引只包含单个列，一个表可以有多个单列索引
		3.2. 唯一索引：索引列的值必须唯一，但允许有空值
		3.3. 复合索引：即一个索引包含多个列
		3.4. 基本语法
			创建
				CREATE INDEX 索引名 ON 表名(字段列表);
				ALTER TABLE 表名 ADD INDEX 索引名(字段列表);
			删除
				DROP INDEX 索引名 ON 表名;
			查看
				SHOW INDEX FROM 表名;
				
	4. 哪些情况需要创建索引？
		4.1. 主键自动建立唯一索引
		4.2. 查询中与其他表关联的字段，外键关系建立索引
		4.3. 频繁作为查询条件的字段
		4.4. 查询中排序的字段，排序字段若通过索引去访问将大大提高排序速度
		4.5. 查询中统计或者分组字段
		
	5. 哪些情况下不要创建索引？
		5.1. 表记录太少
		5.2. 经常增删改的表：索引提高了查询速度，同时会降低更新表的速度，因为在更新表时，MySQL不仅要保存数据，还要保存一下索引文件
		5.3. 频繁更新的字段：每次更新不单单更新了记录还更新索引，加重了IO负担
		5.4. 数据重复且分布平均的字段，因此应该只为最经常查询和排序的数据列建立索引。
			注意：如果某个数据列包含许多重复的内容，为它建立索引就没有太大的实际效果。
*/

/*
	四种方式添加数据表的索引：
		ALTER TABLE 表名 ADD PRIMARY KEY(字段列表);			该语句添加一个主键，这意味着索引值必须是唯一的，且不能为NULL
		ALTER TABLE 表名 ADD UNIQUE 索引名(字段列表);			该语句创建索引的值必须是唯一的（除了NULL外，NULL可能会出现多次）
		ALTER TABLE 表名 ADD INDEX 索引名(字段列表);			添加普通索引，索引值可出现多次
		ALTER TABLE 表名 ADD FULLTEXT 索引名(字段列表);			该语句指定了索引为FULLTEXT，用于全文索引
*/
EXPLAIN SELECT * FROM tbl_emp;

SHOW INDEX FROM tbl_emp;
SHOW INDEX FROM tbl_dept;