SELECT * FROM tbl_user;

INSERT INTO tbl_user(id, username, age) VALUES(REPLACE(UUID(), '-', ''), 'jack', 20);