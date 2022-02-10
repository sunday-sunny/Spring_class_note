-- SpringFileTest > "script.sql"



-- 게시판 + 파일업로드(1개)
create table tblFileBoard(
    seq number primary key,                 -- PK
    subject VARCHAR2(100) not null,         -- 제목
    name VARCHAR2(30) not null,             -- 작성자
    regdate date default sysdate not null,  -- 작성일
    filename varchar2(100) null,             -- 파일명(저장)
    orgfilename VARCHAR2(100) null          -- 파일명(원본)
);

create sequence seqFileBoard;


-- SELECT 
select * from tblfileboard;

-- INSERT
insert into tblFileBoard(seq, name, subject, regdate, filename, orgfilename)
		 values (seqFileBoard.nextVal, '최선희', '테스트제목', default, '파일이름', 'org파일이름');

-- DELETE
delete from tblFileBoard where seq = 1;

-- DROP
drop table tblFileBoard;
drop sequence seqFileBoard;


commit;




-- 게시판 + 파일업로드(N개)
create table tblFileBoard(
    seq number primary key,                 -- PK
    subject VARCHAR2(100) not null,         -- 제목
    name VARCHAR2(30) not null,             -- 작성자
    regdate date default sysdate not null   -- 작성일
);


create table tblFile (
    seq number primary key,                 -- PK
    filename varchar2(100) null,            -- 파일명(저장)
    orgfilename VARCHAR2(100) null,          -- 파일명(원본)
    bseq number not null references tblFileBoard(seq)
);

create sequence seqFileBoard;
create sequence seqFile;



-- SELECT 
select * from tblFileBoard;
select * from tblFile;
select max(seq) from tblFileBoard;


-- INSERT
insert into tblFileBoard(seq, name, subject, regdate)
			values (seqFileBoard.nextVal, #{name}, #{subject}, default)

insert into tblFile(seq, orgfilename, filename, bseq)
			values(seqFile.nextVal, #{orgfilename}, #{filename}, #{bseq})

-- DELETE
delete from tblFileBoard where seq = 2;


commit;
