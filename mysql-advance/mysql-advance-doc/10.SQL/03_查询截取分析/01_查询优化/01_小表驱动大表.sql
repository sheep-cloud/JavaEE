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
