create database bbs1;

use bbs1;

create table author
(
	id int primary key auto_increment,
	sex varchar(10),
	slikes varchar(50),
	mlikes varchar(50),
	privince varchar(30),
	introduce TINYTEXT
);

create table voucher
(
	voucherid int,
	name varchar(50),
	password varchar(50),
	constraint voucher_author foreign key(voucherid) references author(id)
);

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
create table manager
(
	id int primary key auto_increment,
	name varchar(50) not null,
	password varchar(50) not null
);
insert into manager values (null,'admin','admin');