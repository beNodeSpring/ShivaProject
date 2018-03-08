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

delete from used_sale where NUM_S = 6;
select * from USED_SALE;
insert into USED_SALE values(1,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(2,'kjm','중고 아이폰 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(3,'kyr','중고 아이패드 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(4,'kcy','중고 마우스 판매합니다','중고노트북 판매 본문','',0,sysdate);
insert into USED_SALE values(5,'kjk','중고 노트북 판매합니다','중고노트북 판매 본문','',0,sysdate);

select * from (select rownum  rnum,NUM_S,NAME_S,SUBJECT_S,CONTENT_S,FILE_S,READCOUNT_S,DATE_S  from (SELECT * FROM USED_SALE
where NAME_S like '%searchkey%'or SUBJECT_S like '%노트북%'
ORDER BY NUM_S DESC)) where rnum >= 1 and rnum <= 10;
