drop table board;

select * from board;

create table board(
	BOARD_NO      number primary key,
	BOARD_NAME   varchar2(50) not null,
	BOARD_SUBJECT varchar2(1000) not null,
	BOARD_CONTENT varchar2(1000) not null,
	BOARD_COUNT   number,
	BOARD_DATE    DATE   
	);
	
	insert into board values(1,'김', '제목','내용', 0, sysdate);
	insert into board values(2,'김1', '제목2','내용2', 0, sysdate);
	insert into board values(3,'김2', '제목1','내용3', 0, sysdate);
	insert into board values(4,'김3', '제목3','내용4', 0, sysdate);
	insert into board values(5,'김4', '제목4','내용5', 0, sysdate);
	insert into board values(6,'김1', '제목2','내용2', 0, sysdate);
	insert into board values(7,'김2', '제목1','내용3', 0, sysdate);
	insert into board values(8,'김3', '제목3','내용4', 0, sysdate);
	insert into board values(9,'김4', '제목4','내용5', 0, sysdate);
	insert into board values(10,'김1', '제목2','내용2', 0, sysdate);
	insert into board values(11,'김2', '제목1','내용3', 0, sysdate);
	insert into board values(12,'김3', '제목3','내용4', 0, sysdate);
	insert into board values(13,'김4', '제목4','내용5', 0, sysdate);
	
	
	

