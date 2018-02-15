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
INSERT INTO member values('kjk', '123', '김재겸', '남', 'kjk', 'naver.com', '01012341234');
INSERT INTO member values('kjm', '123', '김종민', '남', 'kjm', 'google.com', '01012341234');
INSERT INTO member values('admin', '123', '서현숙', '여', 'admin', 'nate.com', '01098765432');

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

insert into IT_NOTICE_BOARD values(1,'관리자','게시물제목1','게시물내용1입니다',1,sysdate);
insert into IT_NOTICE_BOARD values(2,'관리자','게시물제목2','게시물내용2입니다',2,sysdate);
insert into IT_NOTICE_BOARD values(3,'관리자','게시물제목3','게시물내용3입니다',3,sysdate);
insert into IT_NOTICE_BOARD values(4,'관리자','일부로 글 제목 길게 쓰는중 쩜쩜쩜으로 제대로 나오나 확인하려고 그러는 거니깐 신경쓰지 말것!!! 이정도면 어느정도 길이지??','게시물내용4입니다',4,sysdate);

select * from IT_NOTICE_BOARD;
select BOARD_SUBJECT from (select rownum rnum, BOARD_SUBJECT 
				from (SELECT * FROM IT_NOTICE_BOARD ORDER BY BOARD_COUNT DESC)) 
				where rnum = 1;

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
select * from USED_SALE;

--영렬이 게시판
drop table resume;

create table resume(
   RESUME_ID         VARCHAR2(30),
   RESUME_DATE         DATE,
   TODAYFEELING      VARCHAR2(100),
   PROJECT1         VARCHAR2(200),
   PROJECT2         VARCHAR2(200),
   PROJECT3         VARCHAR2(200),
   PROJECT4         VARCHAR2(200),
   PROJECT5         VARCHAR2(200),
   PROJECT6         VARCHAR2(200),
   PROJECT7         VARCHAR2(200),
   PROJECT8         VARCHAR2(200),
   PROJECT9         VARCHAR2(200),
   DURATION1         VARCHAR2(200),
   DURATION2         VARCHAR2(200),
   DURATION3         VARCHAR2(200),
   DURATION4         VARCHAR2(200),
   DURATION5         VARCHAR2(200),
   DURATION6         VARCHAR2(200),
   DURATION7         VARCHAR2(200),
   DURATION8         VARCHAR2(200),
   DURATION9         VARCHAR2(200),
   TEXT1            VARCHAR2(4000),
   TEXT2            VARCHAR2(4000),
   TEXT3            VARCHAR2(4000),
   TEXT4            VARCHAR2(4000),
   TEXT5            VARCHAR2(4000),
   TEXT6            VARCHAR2(4000),
   TEXT7            VARCHAR2(4000),
   TEXT8            VARCHAR2(4000),
   TEXT9            VARCHAR2(4000),
   JAVAVAL            VARCHAR2(10),
   PYTHONVAL         VARCHAR2(10),
   CVAL            VARCHAR2(10),
   RUBYVAL            VARCHAR2(10),
   JAVASCRIPTVAL      VARCHAR2(10),
   CSHAPVAL         VARCHAR2(10),
   PHPVAL            VARCHAR2(10),
   OBJECTIVECVAL      VARCHAR2(10),
   SQLVAL            VARCHAR2(10),
   CPLUSVAL         VARCHAR2(10),
   PRIMARY KEY(RESUME_ID)
);         

select * from resume;
insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('kjm1',sysdate, '저는 유능한 웹 개발자입니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false');

insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('kjm2',sysdate, '아키텍쳐 + 디자인 + 프런트엔드 다 할 수 있습니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false');

insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('kjm3',sysdate, '저는 유능한 웹 개발자입니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false');


select RESUME_ID from (select rownum rnum, RESUME_ID from (SELECT * FROM resume ORDER BY RESUME_DATE DESC)) where rnum = 1;
