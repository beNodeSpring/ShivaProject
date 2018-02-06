DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	ID          VARCHAR2(15),
	PASSWD      VARCHAR2(15),
	NAME        VARCHAR2(15),
	GENDER      VARCHAR2(6),
	MAIL        VARCHAR2(30),
	PHONE       VARCHAR2(30),
	PRIMARY KEY(ID)
);
SELECT * FROM MEMBER;


DROP TABLE BOARD
CREATE TABLE BOARD(
	BOARD_NUM           NUMBER,         -- 글번호
	BOARD_NAME          VARCHAR2(30),   -- 작성자
	BOARD_PASS          VARCHAR2(30),   -- 비밀번호
	BOARD_SUBJECT       VARCHAR2(300),  -- 제목
	BOARD_CONTENT       VARCHAR2(4000), -- 내용
	BOARD_FILE          VARCHAR2(50),   -- 첨부파일명
	BOARD_RE_REF        NUMBER,         -- 답변 글 참조 글 번호
	BOARD_RE_LEV        NUMBER,         -- 답변 글 뎁스
	BOARD_RE_SEQ        NUMBER,         -- 답변 글 순서
	BOARD_READCOUNT     NUMBER,         -- 글 조회수
	BOARD_DATE          DATE,           -- 글 작성날짜
	PRIMARY KEY(BOARD_NUM)
);
CREATE TABLE MEMBERBOARD(
	BOARD_NUM           NUMBER,         -- 글번호
	BOARD_ID            VARCHAR2(20),   -- 작성자
	BOARD_SUBJECT       VARCHAR2(50),  -- 제목
	BOARD_CONTENT       VARCHAR2(2000), -- 내용
	BOARD_FILE          VARCHAR2(50),   -- 첨부파일명
	BOARD_RE_REF        NUMBER,         -- 답변 글 참조 글 번호
	BOARD_RE_LEV        NUMBER,         -- 답변 글 뎁스
	BOARD_RE_SEQ        NUMBER,         -- 답변 글 순서
	BOARD_READCOUNT     NUMBER,         -- 글 조회수
	BOARD_DATE          DATE,           -- 글 작성날짜
	PRIMARY KEY(BOARD_NUM)
);

ALTER TABLE MEMBERBOARD
ADD CONSTRAINT pk_board_id
FOREIGN KEY(board_id) REFERENCES member2(member_id);

SELECT * FROM BOARD;

select max(board_num) from board;

select count(*) from board

SELECT * FROM BOARD WHERE BOARD_NUM = 1;

select * from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc;

delete from board where BOARD_NUM=5;	