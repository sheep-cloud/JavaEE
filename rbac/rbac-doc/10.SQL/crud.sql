-- 初始数据（基础）
SELECT * FROM `user`;
SELECT * FROM role;
SELECT * FROM user_role;
SELECT * FROM permission;
SELECT * FROM role_permission;

INSERT INTO `user` (id, username, loginacct, `password`, email)
VALUES
    (REPLACE(UUID(), '-', ''), '克烈儿辣舞', 'jack', '123456', 'jack@qq.com'),
    (REPLACE(UUID(), '-', ''), '接Q辣舞', 'jakey', '123456', 'jakey@qq.com');

INSERT INTO role (id, name)
VALUES
    (replace(uuid(), '-', ''), 'PM - 项目经理'),
    (replace(uuid(), '-', ''), 'SE - 软件工程师'),
    (replace(uuid(), '-', ''), 'PG - 程序员'),
    (replace(uuid(), '-', ''), 'TL - 组长'),
    (replace(uuid(), '-', ''), 'GL - 组长'),
    (replace(uuid(), '-', ''), 'QC - 品质控制'),
    (replace(uuid(), '-', ''), 'SA - 软件架构师'),
    (replace(uuid(), '-', ''), 'SYSTEM - 系统管理');

INSERT INTO permission (id, `name`, pid)
VALUES
    (REPLACE(UUID(), '-', ''), '系统菜单', NULL);

INSERT INTO permission (id, `name`, pid)
VALUES
    (REPLACE(UUID(), '-', ''), '控制面板', 'ee7add86910c11e8bc4454ee75c6aeb0'),
    (REPLACE(UUID(), '-', ''), '权限管理', 'ee7add86910c11e8bc4454ee75c6aeb0');

INSERT INTO permission (id, `name`, pid)
VALUES
    (REPLACE(UUID(), '-', ''), '用户维护', '01d79253910d11e8bc4454ee75c6aeb0'),
    (REPLACE(UUID(), '-', ''), '角色维护', '01d79253910d11e8bc4454ee75c6aeb0'),
    (REPLACE(UUID(), '-', ''), '许可维护', '01d79253910d11e8bc4454ee75c6aeb0');


SELECT *
FROM
    permission p
WHERE
    p.id IN (
        SELECT rp.permission_id
        FROM
            role_permission rp
        WHERE
            rp.role_id IN (
                SELECT ur.role_id
                FROM
                    user_role ur
                WHERE
                    ur.user_id = 'b22a30ea8dc111e8807854ee75c6aeb0'
            )
    );