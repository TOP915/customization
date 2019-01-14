/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/1/14 15:34:55                           */
/*==============================================================*/


drop table if exists cus_order;

drop table if exists cus_task;

drop table if exists cus_task_file;

drop table if exists cus_user;

drop table if exists cus_writer;

/*==============================================================*/
/* Table: cus_order                                             */
/*==============================================================*/
create table cus_order
(
   order_id             varchar(64) not null,
   task_id              varchar(64) not null comment '任务id',
   writer_id            varchar(64) comment '写手id',
   writer_name          varchar(128) comment '写手姓名',
   order_price          decimal(8,2) comment '订单价格',
   create_by            varchar(64) not null comment '创建人',
   create_date          datetime not null comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   schedule             varchar(6) comment '对接schedule;标示任务进行状态;',
   del_flag             char(1) not null default '0' comment '删除标记',
   primary key (order_id)
);

alter table cus_order comment '订单表';

/*==============================================================*/
/* Table: cus_task                                              */
/*==============================================================*/
create table cus_task
(
   task_id              varchar(64) not null,
   task_name            varchar(128) comment '任务简称',
   owner_id             varchar(64) not null comment '归属人id',
   owner_name           varchar(128) not null comment '归属人名称',
   relevant_subject     varchar(128) not null comment '学科',
   site                 varchar(128) comment '地点、位置',
   task_type            varchar(6) comment '任务类型',
   detail_requirements  varchar(128) not null comment '详细需求',
   deadline             datetime comment '最后期限
            ',
   writer_level         varchar(64) comment '写手水平要求',
   create_by            varchar(64) not null comment '创建人',
   create_date          datetime not null comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   schedule             varchar(6) comment '对接schedule;标示任务进行状态;',
   del_flag             char(1) not null default '0' comment '删除标记',
   primary key (task_id)
);

alter table cus_task comment '任务表';

/*==============================================================*/
/* Table: cus_task_file                                         */
/*==============================================================*/
create table cus_task_file
(
   file_id              varchar(64) not null comment '文件id',
   file_oname           varchar(128) not null comment '原文件名称',
   file_name            varchar(128) not null comment '文件名称',
   file_size            bigint not null comment '文件大小(B)',
   file_path            varchar(128) not null comment '文件地址',
   file_md5             char(10) comment 'md5',
   file_format          varchar(16) not null comment '文件格式',
   task_id              varchar(64) not null comment '任务id',
   create_by            varchar(64) not null comment '创建人',
   create_date          datetime not null comment '创建时间',
   update_by            varchar(64) not null comment '更新人',
   update_date          datetime not null comment '更新时间',
   del_flag             char(1) not null default '0' comment '删除标记',
   primary key (file_id)
);

alter table cus_task_file comment '任务文件表';

/*==============================================================*/
/* Table: cus_user                                              */
/*==============================================================*/
create table cus_user
(
   user_id              varchar(64) not null comment '用户id',
   login_name           varchar(128) not null comment '登录账号',
   user_name            varchar(128) comment '用户姓名',
   user_email           varchar(64) comment '邮箱',
   user_phone           varchar(64) not null comment '电话',
   user_wechat          varchar(64) comment '用户微信',
   user_qq              varchar(64) comment '用户QQ',
   user_type            char(2) not null comment '用户类型 1:需求客户;2:写手客户;',
   login_ip             varchar(64) comment '最后登陆IP',
   login_date           datetime comment '最后登陆时间',
   login_flag           char(1) comment '是否可登录1:可登录；2:不可登录',
   create_by            varchar(64) not null comment '创建人',
   create_date          datetime not null comment '创建时间',
   update_by            varchar(64) not null comment '更新人',
   update_date          datetime not null comment '更新时间',
   del_flag             char(1) not null default '0' comment '删除标记',
   primary key (user_id)
);

alter table cus_user comment '用户信息表';

/*==============================================================*/
/* Table: cus_writer                                            */
/*==============================================================*/
create table cus_writer
(
   writer_id            varchar(64) not null comment '写手id',
   writer_record        varchar(64) not null comment '写手履历',
   user_id              varchar(64) not null comment '用户id',
   create_date          datetime not null comment '创建时间',
   update_by            varchar(64) not null comment '更新人',
   update_date          datetime not null comment '更新时间',
   del_flag             char(1) not null default '0' comment '删除标记',
   primary key (writer_id)
);

alter table cus_writer comment '写手信息表';

