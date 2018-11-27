
create database if not exists testdb;
CREATE TABLE if not exists testdb.users  (
 user_id  varchar(20) NOT NULL ,
 name varchar(50),
 age  int(3),
 department varchar(50),
 PRIMARY KEY (user_id)
);
insert into users values('001','user001',25,'システム開発部');
insert into users values('002','user002',25,'システム開発部');
insert into users values('003','user003',25,'営業部');