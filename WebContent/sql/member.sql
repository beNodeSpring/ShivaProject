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
insert into MainNotice values(1,'kjm','공통단UI','팀시바의 architecture & UI를 담당하고 있는 김종민 입니다',sysdate,1,0,0,0,0);
insert into MainNotice values(2,'kyj','IT자격증','팀시바의 IT자격증 part를 담당하고 있는 김용준 입니다',sysdate,2,0,0,0,0);
insert into MainNotice values(3,'kyy','IT이력서','팀시바의 IT이력서 part를 담당하고 있는 김영렬 입니다',sysdate,3,0,0,0,0);
insert into MainNotice values(4,'kjk','IT거래소','팀시바의 IT거래소 part를 담당하고 있는 김재겸 입니다',sysdate,4,0,0,0,0);
insert into MainNotice values(5,'kjm','공통단UI','팀시바의 architecture & UI를 담당하고 있는 김종민 입니다',sysdate,5,0,0,0,0);
insert into MainNotice values(6,'kyj','IT자격증','팀시바의 IT자격증 part를 담당하고 있는 김용준 입니다',sysdate,6,0,0,0,0);
insert into MainNotice values(7,'kyy','IT이력서','팀시바의 IT이력서 part를 담당하고 있는 김영렬 입니다',sysdate,7,0,0,0,0);
insert into MainNotice values(8,'kjk','IT거래소','팀시바의 IT거래소 part를 담당하고 있는 김재겸 입니다',sysdate,8,0,0,0,0);
insert into MainNotice values(9,'kjm','공통단UI','팀시바의 architecture & UI를 담당하고 있는 김종민 입니다',sysdate,9,0,0,0,0);
insert into MainNotice values(10,'kyj','10번째 공지사항',sysdate,10,0,0,0,0);
insert into MainNotice values(11,'kyy','11번째 공지사항',sysdate,11,0,0,0,0);


select * from MainNotice;
select * from MainNotice order by num;
select * from MainNotice order by ref desc, step asc; -- 리스트 불러오기 쿼리문 : 다시 짜기
select count(*) as num from MainNotice; -- 현재 글 갯수
select nvl(max(num),0)+1 as num from MainNotice; -- 작성할 차례의 글번호 구하기
select * from MainNotice where subject like '%[공지사항]%' order by ref desc, step asc; --
select * from MainNotice where subject like '%[공지사항]%' order by num;


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
