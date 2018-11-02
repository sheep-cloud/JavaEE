SELECT * FROM department;
SELECT * FROM employee;
SELECT e.* FROM employee e WHERE e.id = ?;
INSERT INTO employee(lastName, email, gender, d_id) VALUES(?, ?, ?, ?);