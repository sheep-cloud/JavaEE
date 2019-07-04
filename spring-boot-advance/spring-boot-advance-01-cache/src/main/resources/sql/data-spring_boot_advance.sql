SELECT * FROM department;
SELECT * FROM employee;

INSERT INTO
    department(departmentName)
VALUES
    ('技术部'),
    ('测试部');
    
INSERT INTO 
	employee(lastName, email, gender, d_id)
	VALUES
		('jack', 'jack@qq.com', 0, 1),
		('rose', 'rose@qq.com', 1, 2);