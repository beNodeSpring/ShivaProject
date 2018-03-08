-- [김종민] 회원 명단
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	ID          VARCHAR2(30),
	PASSWD      VARCHAR2(30),
	NAME        VARCHAR2(30),
	GENDER      VARCHAR2(6),
	MAIL        VARCHAR2(30),
	MAILDomain  VARCHAR2(20),
	PHONE       VARCHAR2(15),
	PRIMARY KEY(ID)
);

INSERT INTO member values('admin', '123', '아연', '여', 'admin', 'nate.com', '01098765432');
INSERT INTO member values('pyj', '123', '박용준', '남', 'kjm', 'google.com', '01012341234');
INSERT INTO member values('kjm', '123', '김종민', '남', 'kjm', 'google.com', '01012341234');
INSERT INTO member values('kjk', '123', '김재겸', '남', 'kjk', 'naver.com', '01012341234');
INSERT INTO member values('kyy', '123', '김영렬', '남', 'kyy', 'naver.com', '01012341234');

-- [김종민] 공지사항
drop table MainNotice;
create table MainNotice(
   NUM         NUMBER,         -- 글번호
   ID          VARCHAR2(30),   -- 아이디
   SUBJECT     VARCHAR2(300),  -- 제목
   CONTENT     VARCHAR2(4000), -- 본문내용
   WRITEDATE  DATE,            -- 작성일자
   REF         NUMBER,         -- 답변 글의 참조 원문 글 번호
   STEP        NUMBER,         -- 답변 글의 출력 순서
   LEV         NUMBER,         -- 답변글의 들여쓰기 레벨
   READCNT    NUMBER,         -- 글 조회수
   CHILDCNT   NUMBER,         -- 글의 답변 글 수
   PRIMARY KEY(NUM)
);

insert into MainNotice values(1,'kjm','공통단UI 담당 김종민 소개','팀시바의 architecture & UI를 담당하고 있는 김종민 입니다',sysdate,1,0,0,0,0);
insert into MainNotice values(2,'kyj','IT자격증 담당 박용준 소개','팀시바의 IT자격증 part를 담당하고 있는 박용준 입니다',sysdate,2,0,0,0,0);
insert into MainNotice values(3,'kyy','IT이력서 담당 김영렬 소개','팀시바의 IT이력서 part를 담당하고 있는 김영렬 입니다',sysdate,3,0,0,0,0);
insert into MainNotice values(4,'kjk','IT거래소 담당 김재겸 소개','팀시바의 IT거래소 part를 담당하고 있는 김재겸 입니다',sysdate,4,0,0,0,0);
insert into MainNotice values(5,'admin','첫번째 공지사항 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(6,'admin','두번째 공지사항 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(7,'admin','세번째 공지사항 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(8,'admin','네번째 공지사항 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(9,'admin','다섯번째 공지사항 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(10,'admin','여섯번째 공지사항 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(11,'admin','일곱번째 공지사항 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(12,'admin','여덜번째 공지사항 입니다',sysdate,5,0,0,0,0);

--[박용준] 공지사항
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

select * from IT_NOTICE_BOARD

insert into IT_NOTICE_BOARD values(1,'관리자','it자격증1','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(2,'관리자','정보처리기사','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(3,'관리자','SQLD','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(4,'관리자','SQLP','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(5,'관리자','SQLP2','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(6,'관리자','SQLD2','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(7,'관리자','SQLP3','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(8,'관리자','SQLD4','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(9,'관리자','SQLP5','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(10,'관리자','SQLD6','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(11,'관리자','SQLP7','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(12,'관리자','SQLD8','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(13,'관리자','SQLP9','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(14,'관리자','SQLD9','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(15,'관리자','SQLP10','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(16,'관리자','SQLD10','뀨',0,sysdate);

--[박용준] 자유게시판
drop table IT_COMUNITY_BOARD;
create table IT_COMUNITY_BOARD
(
	BOARD_NUM NUMBER,
	BOARD_NAME VARCHAR2(100),
	BOARD_SUBJECT VARCHAR2(200),
	BOARD_CONTENT VARCHAR2(400),
	BOARD_COUNT NUMBER,
	BOARD_DATE DATE,
	PRIMARY KEY(BOARD_NUM)
);
insert into IT_COMUNITY_BOARD values(1,'admin','내가 1빠다','뀨',0,sysdate);

--[박용준] Q&A게시판
drop table IT_QNA_BOARD;
create table IT_QNA_BOARD
(
	BOARD_NUM NUMBER,
	BOARD_NAME VARCHAR2(100),
	BOARD_SUBJECT VARCHAR2(200),
	BOARD_CONTENT VARCHAR2(400),
	BOARD_COUNT NUMBER,
	BOARD_DATE DATE,
	PRIMARY KEY(BOARD_NUM)
);

drop table IT_QNA_REPLY;
create table IT_QNA_REPLY
(
REPLY_NUM NUMBER,
REPLY_LEVEL NUMBER,
REPLY_NAME VARCHAR2(200),
REPLY_CONTENT VARCHAR2(600)
);

drop table IT_COMPLETE_BOARD;
create table IT_COMPLETE_BOARD
(
	BOARD_NUM NUMBER,
	BOARD_NAME VARCHAR2(100),
	BOARD_SUBJECT VARCHAR2(200),
	BOARD_CONTENT VARCHAR2(400),
	BOARD_COUNT NUMBER,
	BOARD_DATE DATE,
	PRIMARY KEY(BOARD_NUM)
);

--[박용준] 일정게시판
drop table IT_CALENDAR;
create table IT_CALENDAR
(
	ITC_DATE DATE,
	ITC_NAME VARCHAR2(100)
);

drop table IT_COMUNITY_REPLY;
create table IT_COMPLETE_REPLY
(
REPLY_NUM NUMBER,
REPLY_LEVEL NUMBER,
REPLY_NAME VARCHAR2(200),
REPLY_CONTENT VARCHAR2(600)
);

insert into IT_COMUNITY_REPLY values(1,1,'람쥐','람쥐썬더1');
insert into IT_COMUNITY_REPLY values(1,2,'람쥐','람쥐썬더2');
insert into IT_COMUNITY_REPLY values(1,3,'람쥐','람쥐썬더3');

CREATE TABLE IT_CALENDAR_SUBJECT
(
	ITC_DATE DATE,
	ITC_NAME VARCHAR2(100),
	ITC_TYPE NUMBER
);
insert into IT_CALENDAR_SUBJECT values(to_date('2018-02-14','yyyy-mm-dd'),'발렌타인 데이',0);
insert into IT_CALENDAR_SUBJECT values(to_date('2018-02-15','yyyy-mm-dd'),'연휴',1);
insert into IT_CALENDAR_SUBJECT values(to_date('2018-02-16','yyyy-mm-dd'),'설날',1);
insert into IT_CALENDAR_SUBJECT values(to_date('2018-02-17','yyyy-mm-dd'),'연휴',1);

ALTER TABLE IT_CALENDAR_SUBJECT MODIFY(ITC_TYPE DEFAULT 0);

DROP TABLE IT_CALENDAR_CONTENT;
CREATE TABLE IT_CALENDAR_CONTENT
(
	ITC_DATE DATE,
	ITC_NAME VARCHAR2(200),
	ITC_LINK VARCHAR2(300)
);

insert into IT_CALENDAR_CONTENT values(to_date('2018-02-02','yyyy-mm-dd'),'정보처리기사접수시작 ','http://www.q-net.or.kr');
insert into IT_CALENDAR_CONTENT values(to_date('2018-02-08','yyyy-mm-dd'),'정보처리기사접수마감','http://www.q-net.or.kr');
insert into IT_CALENDAR_CONTENT values(to_date('2018-02-14','yyyy-mm-dd'),'발렌타인 데이','none');

ALTER TABLE IT_CALENDAR_CONTENT MODIFY(ITC_LINK DEFAULT 'NONE');

--[김재겸] 구매 게시판
drop table used_buy;
create table used_buy(
	NUM_B	NUMBER,
	NAME_B	VARCHAR2(30),
	SUBJECT_B	VARCHAR2(300),
	CONTENT_B	VARCHAR2(4000),
	FILE_B	VARCHAR2(50),
	READCOUNT_B	NUMBER,
	DATE_B	DATE,
	PRIMARY KEY(NUM_B)
);

drop table used_buy_reply;
create table used_buy_reply(
	NUM_B	NUMBER,
	NAME_B	VARCHAR2(30),
	CONTENT_B	VARCHAR2(4000),
	DATE_B	DATE
);

--[김재겸] 판매 게시판
drop table used_sale;
create table used_sale(
	NUM_S	NUMBER,
	NAME_S	VARCHAR2(30),
	SUBJECT_S	VARCHAR2(300),
	CONTENT_S	VARCHAR2(4000),
	FILE_S	VARCHAR2(50),
	READCOUNT_S	NUMBER,
	DATE_S	DATE,
	PRIMARY KEY(NUM_S)
);

insert into USED_SALE values(1,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(2,'kjm','중고 아이폰 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(3,'kyr','중고 아이패드 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(4,'kcy','중고 마우스 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(5,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);

drop table used_sale_reply;
create table used_sale_reply(
	NUM_S	NUMBER,
	NAME_S	VARCHAR2(30),
	CONTENT_S	VARCHAR2(4000),
	DATE_S	DATE
);

-- [김영렬] 이력서
drop table resume;
create table resume(
	RESUME_ID			VARCHAR2(30),
	RESUME_DATE			DATE,
	TODAYFEELING		VARCHAR2(100),
	PROJECT1			VARCHAR2(200),
	PROJECT2			VARCHAR2(200),
	PROJECT3			VARCHAR2(200),
	PROJECT4			VARCHAR2(200),
	PROJECT5			VARCHAR2(200),
	PROJECT6			VARCHAR2(200),
	PROJECT7			VARCHAR2(200),
	PROJECT8			VARCHAR2(200),
	PROJECT9			VARCHAR2(200),
	DURATION1			VARCHAR2(200),
	DURATION2			VARCHAR2(200),
	DURATION3			VARCHAR2(200),
	DURATION4			VARCHAR2(200),
	DURATION5			VARCHAR2(200),
	DURATION6			VARCHAR2(200),
	DURATION7			VARCHAR2(200),
	DURATION8			VARCHAR2(200),
	DURATION9			VARCHAR2(200),
	TEXT1				VARCHAR2(4000),
	TEXT2				VARCHAR2(4000),
	TEXT3				VARCHAR2(4000),
	TEXT4				VARCHAR2(4000),
	TEXT5				VARCHAR2(4000),
	TEXT6				VARCHAR2(4000),
	TEXT7				VARCHAR2(4000),
	TEXT8				VARCHAR2(4000),
	TEXT9				VARCHAR2(4000),
	JAVAVAL				VARCHAR2(10),
	PYTHONVAL			VARCHAR2(10),
	CVAL				VARCHAR2(10),
	RUBYVAL				VARCHAR2(10),
	JAVASCRIPTVAL		VARCHAR2(10),
	CSHAPVAL			VARCHAR2(10),
	PHPVAL				VARCHAR2(10),
	OBJECTIVECVAL		VARCHAR2(10),
	SQLVAL				VARCHAR2(10),
	CPLUSVAL			VARCHAR2(10),
	PIC					VARCHAR2(50),
	VISITORPIC			VARCHAR2(50),
	VISITOR_ID			VARCHAR2(30),
	PRIMARY KEY(RESUME_ID)
);			

insert into resume values('kjm',sysdate, 'architecture 담당 김종민 입니다', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false', '', '', '');
insert into resume values('kjk',sysdate, 'IT 중고 담당 김재겸 입니다', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false', '', '', '');
insert into resume values('pyj',sysdate, 'IT 중고 담당 박용준입니다', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false', '', '', '');
insert into resume values('kyy',sysdate, 'IT 이력서 김영렬입니다', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false', '', '', '');


-- view 회원명단
SELECT * FROM member;
-- view 공지사항
SELECT * FROM MainNotice;
