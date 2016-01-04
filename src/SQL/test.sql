select * from voucher;
select * from author;
select * from article;
select * from manager;

select Id,Pid,Rootid,Title,Cont,Pdate,Isleaf,Scan,Authorid,Reply,Latestreply from article where pid = 0 order by pdate desc limit 0,6;

update article set reply = 0 where id = 2


#delete from author where id = 5;
#delete from voucher where voucherid = 5;