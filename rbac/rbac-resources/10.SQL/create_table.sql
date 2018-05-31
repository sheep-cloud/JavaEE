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
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table user comment '用户表';
