select count(BOARD_COUNT) from IT_NOTICE_BOARD where BOARD_COUNT=0
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
delete IT_NOTICE_REPLY;
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
insert into IT_NOTICE_BOARD values(5,'관리자2호','SQLP2','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(6,'관리자2호','SQLD2','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(7,'관리자2호','SQLP3','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(8,'관리자2호','SQLD4','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(9,'관리자2호','SQLP5','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(10,'관리자2호','SQLD6','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(11,'관리자2호','SQLP7','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(12,'관리자2호','SQLD8','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(13,'관리자2호','SQLP9','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(14,'관리자2호','SQLD9','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(15,'관리자2호','SQLP10','뀨',0,sysdate);
insert into IT_NOTICE_BOARD values(16,'관리자2호','SQLD10','뀨',0,sysdate);
select * from IT_NOTICE_BOARD;
select count(*) from IT_NOTICE_BOARD where BOARD_SUBJECT like '%it%'

select * from (select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,
			BOARD_CONTENT,
			BOARD_COUNT,
			BOARD_DATE from
			(select * from IT_NOTICE_BOARD
			where BOARD_SUBJECT LIKE '%2%'
			order by BOARD_NUM desc))
			where rnum>=1 and rnum<=10  
			
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
select * from IT_COMUNITY_BOARD;

drop table IT_QNA_BOARD;
select * from IT_QNA_BOARD;
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

select * from IT_COMPLETE_BOARD;
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

create table IT_CALENDAR
(
	ITC_DATE DATE,
	ITC_NAME VARCHAR2(100)
);
drop table IT_COMUNITY_REPLY;

select count(REPLY_NUM) from IT_COMUNITY_REPLY where REPLY_NUM= 1
select * from IT_COMUNITY_REPLY where REPLY_NUM= 1 order by REPLY_LEVEL asc
insert into IT_COMUNITY_REPLY values(1,1,'람쥐','람쥐썬더1');
insert into IT_COMUNITY_REPLY values(1,2,'람쥐','람쥐썬더2');
insert into IT_COMUNITY_REPLY values(1,3,'람쥐','람쥐썬더3');

delete from IT_COMUNITY_REPLY where REPLY_NUM=41;


create table IT_COMUNITY_REPLY
(
REPLY_NUM NUMBER,
REPLY_LEVEL NUMBER,
REPLY_NAME VARCHAR2(200),
REPLY_CONTENT VARCHAR2(600)
);

create table IT_COMPLETE_REPLY
(
REPLY_NUM NUMBER,
REPLY_LEVEL NUMBER,
REPLY_NAME VARCHAR2(200),
REPLY_CONTENT VARCHAR2(600)
);

create table IT_QNA_REPLY
(
REPLY_NUM NUMBER,
REPLY_LEVEL NUMBER,
REPLY_NAME VARCHAR2(200),
REPLY_CONTENT VARCHAR2(600)
);

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
select * from IT_CALENDAR_SUBJECT where ITC_DATE=to_date('2018-02-14','yyyy-mm-dd')
select * from IT_CALENDAR_SUBJECT;

DROP TABLE IT_CALENDAR_CONTENT;

CREATE TABLE IT_CALENDAR_CONTENT
(
	ITC_DATE DATE,
	ITC_NAME VARCHAR2(200),
	ITC_LINK VARCHAR2(300)
);
delete IT_CALENDAR_CONTENT;
select * from IT_CALENDAR_CONTENT where ITC_DATE=to_date('2018-02-02','yyyy-mm-dd');
insert into IT_CALENDAR_CONTENT values(to_date('2018-02-02','yyyy-mm-dd'),'정보처리기사접수시작 ','http://www.q-net.or.kr');
insert into IT_CALENDAR_CONTENT values(to_date('2018-02-08','yyyy-mm-dd'),'정보처리기사접수마감','http://www.q-net.or.kr');
insert into IT_CALENDAR_CONTENT values(to_date('2018-02-14','yyyy-mm-dd'),'발렌타인 데이','none');
select * from IT_CALENDAR_CONTENT;
ALTER TABLE IT_CALENDAR_CONTENT MODIFY(ITC_LINK DEFAULT 'NONE');