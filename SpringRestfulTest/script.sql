-- SpringRestfulTest > "script.sql"

create table tblAddress(
    seq number primary key,
    name varchar2(30) not null,
    age number not null,
    tel VARCHAR2(15) not null,
    address VARCHAR2(100) not null
);

create sequence seqAddress;


-- SELECT
select * from tblAddress order by seq asc;


-- INSERT
insert into tbladdress (seq, name, age, tel, address)
values (seqAddress.nextVal, 'hong', 20, '111-111-1111', '성동구');


-- UPDATE
update tbladdress
set name = '',
age = '',
address = ''
where seq = '';


rollback;