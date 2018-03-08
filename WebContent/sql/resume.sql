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
	PRIMARY KEY(RESUME_ID)
);			

select * from resume;
select pic, visitorpic, visitor_id from resume;
insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('kjm',sysdate, '저는 유능한 웹 개발자입니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false')

insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('kjk',sysdate, '저는 유능한 웹 개발자입니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false')

insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('kyy',sysdate, '저는 유능한 웹 개발자입니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false')

insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('pyj',sysdate, '저는 유능한 웹 개발자입니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false')

insert into resume (RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, PROJECT3, 
PROJECT4, PROJECT5, PROJECT6, PROJECT7, PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, 
DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, 
TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL, JAVASCRIPTVAL, CSHAPVAL, 
PHPVAL, OBJECTIVECVAL, SQLVAL, CPLUSVAL)
values('kcy',sysdate, '저는 유능한 웹 개발자입니다.', '미니프로젝트', '세미프로젝트', '파이널프로젝트', '파이썬프로젝트', '', '', '', '', '', '171023-171102', '171102-171130', 
'171201-171231', '180101-180131', '', '', '', '', '', '엄청 열시미했어요', '대충한 프로젝트 입니다.', '이거하느라 힘들었습니다', '파이썬은 좋은 언어입니다.', '', '',
'', '', '', 'true', 'true', 'true', 'false', 'true', 'false', 'false', 'false', 'false', 'false')