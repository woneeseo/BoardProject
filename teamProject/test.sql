create table tbl_member(
id varchar2(6) primary key,
name varchar2(15) not null,
age number(3) check(age <200)
)
insert into tbl_member values('m001','kim',15)
select * from tbl_member

create table menu(
sp varchar2(100) primary key,
fullname varchar2(100) not null)

insert into menu values ('/insertui.do','kr.co.command.InsertUICommand')
insert into menu values ('/insert.do','kr.co.command.InsertCommand')
insert into menu values ('/list.do','kr.co.command.ListCommand')
insert into menu values ('/read.do','kr.co.command.ReadCommand')
insert into menu values ('/updateui.do','kr.co.command.UpdateUICommand')
insert into menu values ('/update.do','kr.co.command.UpdateCommand')
insert into menu values ('/delete.do','kr.co.command.DeleteCommand')
insert into menu values ('/login.do','kr.co.command.LoginCommand')
INSERT INTO menu VALUES ('/loginui.do', 'kr.co.command.LoginUICommand')
INSERT INTO menu VALUES ('/logout.do', 'kr.co.command.LogoutCommand')


delete from menu
commit
select * from member
select * from menu
drop table mb_tbl
create table mb_tbl(
id varchar2(10) primary key,
name varchar2(20) not null,
email varchar2(40) not null,
pw varchar2(11) not null,
address varchar2(100) not null,
birth date not null,
tel number(11) not null
)
insert into mb_tbl values('rmh63','kim','navercom','123123','sysdate',0102632959)
select * from mb_tbl
select id from mb_tbl where name = 'kim' and email = 'navercom'
drop table bd_tbl

CREATE TABLE bd_tbl(
	id varchar2(10) not null,
	num NUMBER(4) PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	writeday DATE DEFAULT SYSDATE,

	readcnt NUMBER(6) DEFAULT 0,
	repRoot NUMBER(4),
	repStep NUMBER(4),
	repIndent NUMBER(2),
	constraint fk_bd_tbl_id foreign key(id) references mb_tbl(id)
)
select * from bd_tbl
select * from files



CREATE TABLE files(
	fileName VARCHAR2(200),
	fileRealName VARCHAR2(200)
)

SELECT id FROM bd_tbl WHERE num = 4

SELECT ROWNUM rnum, fileName, fileRealName FROM files

SELECT * FROM files

