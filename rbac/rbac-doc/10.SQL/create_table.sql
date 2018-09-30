drop table if exists permission;

drop table if exists role;

drop table if exists user;

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   varchar(64) not null comment '主键',
   name                 varchar(128) comment '名称',
   url                  varchar(256) comment 'url',
   pid                  varchar(64) comment '上级id',
   icon                 varchar(256) comment '图标',
   primary key (id)
);

alter table permission comment '权限表';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   varchar(64) not null comment '主键',
   name                 varchar(128) comment '名称',
   primary key (id)
);

alter table role comment '角色表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   varchar(64) not null comment '主键',
   username             varchar(64) comment '用户名',
   loginacct            varchar(64) comment '登录帐号',
   password             varchar(32) comment '密码',
   email                varchar(64) comment '邮箱',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table user comment '用户表';

-- /// ----------------------------------------------------------------------------------------------------
-- 创建用户
CREATE USER 'rbac' IDENTIFIED BY '123456';

-- 为用户授权
GRANT ALL ON rbac.* TO 'rbac';

-- 刷新权限
FLUSH PRIVILEGES;

-- 查看host、用户、密码
SELECT Host, User, authentication_string FROM mysql.user;

-- 删除用户及权限
DROP USER 'colg';

-- 修改密码
UPDATE mysql.user SET authentication_string = PASSWORD('123456') WHERE User = 'colg';