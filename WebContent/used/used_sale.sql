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

delete from used_sale where NUM_S = 0;
select * from USED_SALE;
select rownum,SUBJECT_S from USED_SALE where rownum = 1 order by NUM_S asc;

--중고 게시판 가장 최근글
select SUBJECT_S from (select rownum rnum,NUM_S,NAME_S,SUBJECT_S
 from (SELECT * FROM USED_SALE ORDER BY NUM_S DESC))
 where rnum = 1;

insert into USED_SALE values(1,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(2,'kjm','중고 아이폰 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(3,'kyr','중고 아이패드 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(4,'kcy','중고 마우스 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(5,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);


