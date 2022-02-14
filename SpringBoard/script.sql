-- SpringBoard > "script.sql"

create table tblSpringBoard(
    seq number primary key,                             -- 글번호(PK)
    id varchar2(30) not null REFERENCES tblUser(id),    -- 작성자(FK)
    subject VARCHAR2(500) not null,                     -- 제목
    content varchar2(2000) not null,                    -- 내용
    regdate date default sysdate not null,              -- 작성시간
    orgfilename VARCHAR2(100) null,                     -- 첨부파일
    filename VARCHAR2(100) null                         -- 첨부파일
);

create sequence seqSpringBoard;


-- select
select * from tblspringboard;
select * from tbluser;

-- insert
insert into tblSpringBoard (seq, id, subject, content, regdate, orgfilename, filename)
        values (seqSpringBoard.nextVal, 'hong', '스프링 게시판입니다.', '내용입니다.', default, null, null);
        

-- update
update tblSpringBoard set subject = '스프링 게시판입니다.', content = '내용입니다.', orgfilename = '', filename = '' where seq = 1;


-- delete
delete from tblspringboard where seq = 1;


-- inner join
select
    seq, id,
    (select name from tblUser where id = tblspringBoard.id) as name,
    subject,
    regdate,
    filename
from tblspringboard;



commit;











