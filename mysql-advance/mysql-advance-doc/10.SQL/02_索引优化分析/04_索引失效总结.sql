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


-- 索引失效小结
/*
	假设index(a, b, c)
	
	where 语句						索引是否被使用
	where a = 3						√	使用到a
	where a = 3 and b = 5					√	使用到a, b
	where a = 3 and b = 5 and c = 4				√	使用到a, b, c
	where b = 3 或 where b = 3 and c = 4 或 where c = 4	×
	where a = 3 and c = 5					√	使用到a；但是c不可以，b中间断了
	where a = 3 and b > 4 and c = 5				√	使用到a, b；c不能用在范围之后，b断了
	where a = 3 and b like 'kk%' and c = 4			√	使用到a, b；c不能用在范围之后，b断了
	where c = 5 and b = 3 and a = 2				√	使用到a, b, c；MySQL底层调优查询顺序
*/

/*
		【优化总结口诀】
	全值匹配我最爱，最左前缀要遵守；
	带头大哥不能死，中间兄弟不能断；
	索引列上少计算，范围之后全失效；
	LIKE百分写最右，覆盖索引不写星；
	不等空值还有OR，索引失效要少用；
	字符引号不可丢，SQL高级也不难。
*/