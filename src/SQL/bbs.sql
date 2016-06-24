#创建数据库bbs1
create database bbs1;

#使用刚刚创建的数据库bbs1
use bbs1;


#创建用户信息表
create table author
(
	id int primary key auto_increment,
	sex varchar(10),
	slikes varchar(50),
	mlikes varchar(50),
	privince varchar(30),
	introduce TINYTEXT
);

#创建用户的账户密码表
create table voucher
(
	voucherid int,
	name varchar(50),
	password varchar(50),
	constraint voucher_author foreign key(voucherid) references author(id)
);

#创建帖子表
create table article 
(
id int primary key auto_increment,
pid int,
rootid int,
title varchar(255),
cont text,
pdate datetime,
isleaf int,
scan int,
authorid int,
reply int,
latestreply int,
constraint text_author foreign key(authorid) references author(id)
);

use bbs1;

#创建超级管理员
create table manager
(
	id int primary key auto_increment,
	name varchar(50) not null,
	password varchar(50) not null
);

#插入超级管理员账号和密码
insert into manager values (null,'teacher','teacher');

#注意以下顺序不能乱
#初始化用户个人信息、账号和密码
#source  author.sql
#source voucher.sql

#初始化帖子数据
#source article.sql