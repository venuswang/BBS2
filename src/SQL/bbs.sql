#创建bbs1
create database bbs1;

#使用bbs1
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

#创建用户账户密码表
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

#创建系统超级管理员表
create table manager
(
	id int primary key auto_increment,
	name varchar(50) not null,
	password varchar(50) not null
);

#插入管理员初始信息
insert into manager values (null,'adminer','adminer');

#以下文件的执行顺序有一定的要求
#对表author和Voucher进行初始化
#source  author.sql
#source voucher.sql

#对帖子表进行数据填充
#source article.sql