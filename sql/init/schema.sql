
create database if not exists testdb;
CREATE TABLE if not exists testdb.users  (
 user_id  varchar(20) NOT NULL ,
 name varchar(50),
 age  int(3),
 department_no varchar(10),
 PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into users values('001','user001',25,'d01');