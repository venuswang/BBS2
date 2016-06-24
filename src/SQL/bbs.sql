#�������ݿ�bbs1
create database bbs1;

#ʹ�øոմ��������ݿ�bbs1
use bbs1;


#�����û���Ϣ��
create table author
(
	id int primary key auto_increment,
	sex varchar(10),
	slikes varchar(50),
	mlikes varchar(50),
	privince varchar(30),
	introduce TINYTEXT
);

#�����û����˻������
create table voucher
(
	voucherid int,
	name varchar(50),
	password varchar(50),
	constraint voucher_author foreign key(voucherid) references author(id)
);

#�������ӱ�
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

#������������Ա
create table manager
(
	id int primary key auto_increment,
	name varchar(50) not null,
	password varchar(50) not null
);

#���볬������Ա�˺ź�����
insert into manager values (null,'teacher','teacher');

#ע������˳������
#��ʼ���û�������Ϣ���˺ź�����
#source  author.sql
#source voucher.sql

#��ʼ����������
#source article.sql