-- SpringMemo > "script.sql"

drop table tblMemo;
drop sequence seqMemo;

-- 메모장 
create table tblMemo (
    seq number primary key,
    name varchar2(30) not null,
    memo varchar2(300) not null,
    regdate date default sysdate not null
);

create sequence seqMemo;


-- SELECT 
select * from tblMemo;

-- INSERT 구문
insert into tblMemo(seq, name, memo, regdate) values (seqMemo.nextVal, '홍길동', '메모입니다.', default);
insert into tblMemo(seq, name, memo, regdate) values (seqMemo.nextVal, '아무개', '스프링 설정 정리하기.', default);
insert into tblMemo(seq, name, memo, regdate) values (seqMemo.nextVal, '홍길동', 'STS 오류 잡고 세팅하기.', default);

-- UPDATE
update tblMemo set name = '홍길동', memo = '메모입니다.' where seq = 1;

-- DELETE
delete from tblMemo where seq = 1;




commit;


