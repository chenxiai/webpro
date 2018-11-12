/*删除商城数据库,如果存在*/
drop database if exists demo;
create database demo default character set utf8;
use demo;
drop table if exists product;
/* 子表 */
create table product
(
   id                  int not null auto_increment,
   name                varchar(20),
   price               decimal(8,2),
   remark              longtext,
   date                timestamp default CURRENT_TIMESTAMP,
   cid				   int,
   primary key (id)
);
/* 主表 */
create table category
(
   cid                  int not null auto_increment,
   cname                varchar(20),
   primary key (cid)
);

insert into category (cname) values ('服装');
insert into category (cname) values ('家用电器');

/* 商品测试用例 */
insert into product (name,price,remark,cid) values ('圣得西服',3000.00,'这里是简单介绍',1);
insert into product (name,price,remark,cid) values ('衫衫西服',3000.00,'这里是简单介绍',1);
insert into product (name,price,remark,cid) values ('Iphone6',6000.00,'这里是简单介绍',2);

select * from product p join category c on p.cid = c.cid
--select * from category;

select p.*,c.cname as 'category.cname',c.cid as 'category.cid' from product p join category c on p.cid = c.cid where p.name like '%%'