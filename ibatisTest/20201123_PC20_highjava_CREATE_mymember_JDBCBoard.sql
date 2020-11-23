CREATE TABLE mymember (

    mem_id     VARCHAR2(15) NOT NULL,
    mem_name   VARCHAR2(30) NOT NULL,
    mem_tel    VARCHAR2(14) NOT NULL,
    mem_addr   VARCHAR2(90) NOT NULL,
    CONSTRAINT pk_mymember PRIMARY KEY ( mem_id )
);

COMMIT;

SELECT * FROM mymember;


--테이블 삭제
DROP TABLE mymember;

DELETE FROM lprod WHERE lprod_id = 10;


--20201117 MVC 활용 게시판 DB sql 추가.
create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   	-- 작성날짜
    board_cnt number default 0, -- 조회수
    board_content clob,     	-- 내용
    constraint pk_jdbc_board primary key (board_no)
);

create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
    
commit;