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

select * from used_buy;