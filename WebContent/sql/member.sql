DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	ID          VARCHAR2(15),
	PASSWD      VARCHAR2(15),
	NAME        VARCHAR2(15),
	GENDER      VARCHAR2(6),
	MAIL        VARCHAR2(20),
	MAILDomain  VARCHAR2(10),
	PHONE       VARCHAR2(15),
	PRIMARY KEY(ID)
);
--삽입
INSERT INTO member values('kjk', '123', '김재겸', '남자', 'kjk', 'naver.com', '01012341234');
INSERT INTO member values('kjm', '123', '김종민', '남자', 'kjm', 'google.com', '01012341234');
INSERT INTO member values('admin', '123', '김종민', '남자', 'admin', 'nate.com', '01098765432');

--선택
SELECT * FROM member;
SELECT id FROM member WHERE id='kjk' AND passwd='123';

--수정
update member set passwd='123' where id='kjk'

--[추가]메인 공지게시판
create table MainNotice(
   bdNum     NUMBER,         --글번호
   bdId      VARCHAR2(30),   --아이디
   bdSubject VARCHAR2(300),  --제목
   bdContent VARCHAR2(4000), --본문내용
   bdFile    VARCHAR2(50),   --파일경로
   bdHits    NUMBER,         --조회수
   bdDate    DATE,           --시간
   PRIMARY KEY(bdNum)
);
insert into MainNotice values(1,'kjm','공지사항입니다','안녕하세요 팀시바의 architecture 및 UI develop를 담당하고 있는 김종민 입니다' ,null,0,sysdate);
select * from MainNotice;

--용준 공지사항
drop table IT_NOTICE_BOARD;
create table IT_NOTICE_BOARD
(
   BOARD_NUM NUMBER,
   BOARD_NAME VARCHAR2(100),
   BOARD_SUBJECT VARCHAR2(200),
   BOARD_CONTENT VARCHAR2(400),
   BOARD_COUNT NUMBER,
   BOARD_DATE DATE,
   PRIMARY KEY(BOARD_NUM)
);
drop table IT_NOTICE_REPLY;
create table IT_NOTICE_REPLY
(
REPLY_NUM NUMBER,
REPLY_LEVEL NUMBER,
REPLY_NAME VARCHAR2(200),
REPLY_CONTENT VARCHAR2(600)
);
insert into IT_NOTICE_BOARD values(1,'관리자','it자격증1','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(2,'관리자','정보처리기사','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(3,'관리자','SQLD','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(4,'관리자','SQLP','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(5,'관리자2호','SQLP2','뀨',0,sysdate);


-- 중고판매
drop table used_sale;

create table used_sale(
   NUM_S   NUMBER,             --글번호
   NAME_S   VARCHAR2(30),      --아이디
   SUBJECT_S   VARCHAR2(300),  --제목
   CONTENT_S   VARCHAR2(4000), --본문내용
   FILE_S   VARCHAR2(50),      --파일경로
   READCOUNT_S   NUMBER,       --조회수
   DATE_S   DATE,              --시간
   PRIMARY KEY(NUM_S)
);

insert into USED_SALE values(1,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(2,'kjm','중고 아이폰 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(3,'kyr','중고 아이패드 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(4,'kcy','중고 마우스 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(5,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);


