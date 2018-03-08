<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shiva.license.dao.*"%>
<%@ page import="com.shiva.license.vo.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">  
<title>IT관리자 페이지</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShivaProject/css/style.css" rel="stylesheet">
<link href="/ShivaProject/css/sub.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<style>
.table-responsive *{text-align: center;}
.table-responsive .tit{text-align: left;}
.form-control{display: inline-block; max-width: calc(100% - 60px);}
.tr_th th{background: #337ab7; color: #fff; font-size: 14px;}
.mb30{margin-bottom: 30px;}
.mb40{margin-bottom: 40px;}
.mb50{margin-bottom: 50px;}
.btn-gray{background:gray}
body {
	background-image: url(images/bg_main-body6.jpg)
}
.modal-content{
	  background: -webkit-linear-gradient(left, #603cba 0%,#694bb7 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to right, #603cba 0%,#694bb7 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
*{color:white}
h3{color:white}
.btn-menu{background:#492b97; font-size:20pt}
.btn-gray{color:white; background:#492b97;}
.th_color{background:#492b97}
.textinput{color:black}
font{font-size:30pt; font-weight:bold}
.calendar tr th{background-color:#492b97; width:6% ;height:3%}
.calendar tr td{background-color:#795bc7; height:80px}
.calendar tr th:nth-child(7n+1){color:red; background-color:#492b97}
.calendar tr td:nth-child(7n+1){color:red; background-color:#795bc7}
.calendar tr th:nth-child(7n){background-color:#492b97}
.calendar tr td:nth-child(7n){background-color:#795bc7}
.calendar{border-color:white}
.size_20{font-size:15pt}
.size_10{font-size:10pt}
.red{color:red}
.modal-content{margin-top:70px;}
</style>
<%LicenseDAO ldao = new  LicenseDAO(); LicenseBoardVO board= new  LicenseBoardVO();
String action="";
if(request.getParameter("action")==null)
	action="board";
else
{
	action=request.getParameter("action");
}

%>

<%boolean start=true; boolean middle=true; int number=0;//캘랜더 그리기사용변수들%>
<%Calendar calendar; calendar=Calendar.getInstance(); int startmonth=calendar.get(Calendar.MONTH); %>
<%int year=0;int month=0; int startday=0;int endday=0; %>
<%int[] dayvalue=new int[35]; %>

<%if(request.getParameter("year")==null||request.getParameter("year").equals("null")){year=calendar.get(Calendar.YEAR);} //리퀘스트에 데이타가 없는 경우 현재날짜 표시
else{year=Integer.parseInt(request.getParameter("year"));}
				if(request.getParameter("month")==null||request.getParameter("month").equals("null")){month=calendar.get(Calendar.MONTH);}
				else{month=Integer.parseInt(request.getParameter("month"));}
				
				calendar.set(year, month, 1); //1일 기준으로 다시 세팅
				startday=calendar.get(Calendar.DAY_OF_WEEK);
				endday=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int day=1; 
				int num=0; //일수 출력할때 쓸 변수
				int num2=0; //일수 체크할때 쓸 변수 위에것과 동일하게 돌아가되 db내용호출시 사용
				int pagenum=0;
				if(request.getParameter("pagenum")!=null)
				pagenum=Integer.parseInt(request.getParameter("pagenum"));
				
				%>
<script>
//캘린더 처리
var pnum=<%=pagenum%>;

function nextmonth()
{
	var ingmonth=<%=month%>;
	
		if(ingmonth==11) //12월에서 누른경우 전년도 1월로
		{
			location.href="./AdminPage.lo?action=calendar&year="+<%=year+1%>+"&month="+<%=0%>+"&pagenum="+<%=pagenum+1%>;
		}
		else
		location.href="./AdminPage.lo?action=calendar&year="+<%=year%>+"&month="+<%=month+1%>+"&pagenum="+<%=pagenum+1%>;
		
	
	}
function prevmonth()
{
	var ingmonth=<%=month%>;
	
		if(ingmonth==0) //1월에서 누른경우 전년도 12월로
		{
			location.href="./AdminPage.lo?action=calendar&year="+<%=year-1%>+"&month="+<%=11%>+"&pagenum="+<%=pagenum-1%>;
		}
		else
		location.href="./AdminPage.lo?action=calendar&year="+<%=year%>+"&month="+<%=month-1%>+"&pagenum="+<%=pagenum-1%>;
	
	
}
//유효성 검사
function holidaysubmit(){
	if($("#ITC_DATE1").val()=="")
	{
		alert("기념일 날짜를 선택해주세요");
		return false;
	}
	else if($("#ITC_NAME1").val()=="내용"||$("#ITC_NAME1").val()==""){
		alert("기념일 내용을 입력해주세요");
		return false;
	}
	else{
		holiday_insert.submit();
	}
	
}

function workdaysubmit(){
	if($("#ITC_DATE2").val()=="")
	{
		alert("일정 날짜를 선택해주세요");
		return false;
	}
	else if($("#ITC_NAME2").val()=="내용"||$("#ITC_NAME2").val()==""){
		alert("일정의 내용을 입력해주세요");
		return false;
	}
	else{
		if($("#ITC_LINK").val()=="(링크)"||$("#ITC_LINK").val()=="")
			{
			$("#ITC_LINK").val("none");
			}
		workday_insert.submit();
	}
	
}

function holidaydelsubmit(){
	
	if($("#ITC_NAME3").val()=="이름"||$("#ITC_NAME3").val()==""){
		alert("삭제할 기념일 이름을 입력해주세요");
		return false;
	}
	else{
		holiday_delete.submit();
	}
	
}

function workdaydelsubmit(){
	
	if($("#ITC_NAME4").val()=="이름"||$("#ITC_NAME4").val()==""){
		alert("삭제할 일정의 이름을 입력해주세요");
		return false;
	}
	else{
		
		workday_delete.submit();
	}
	
}
</script>
<% 
if(action.equals("delsuecess"))
{
	%>
	<script>
	alert("삭제를 성공하셨습니다.");
	</script>
	<% 
}
else if(action.equals("delfail"))
{
	%>
	<script>
	alert("삭제를 실패하셨습니다.(일치하는 이름의 일정이 없습니다. )");
	</script>
	<% 
}
if(action.equals("addsuecess"))
{
  %>
  <script>
  alert("캘린더 내용을 입력했습니다.");
  </script>
  <% 
}
else if(action.equals("addfail"))
{
  %>
  <script>
  alert("캘린더 내용을 입력실패했습니다.");
  </script>
  <% 
}
%>
</head>
<body>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<%-- Notice
---- 테이블의 td의 칸 너비를 조절하고 싶을때 
---- colgroup 안에 col width값 변경(모두 합쳤을때 100%가 되게 할것)
 --%>
 <div class="license_section">
    
   		<div class="container">
		<div class="row">
        <div class="modal-content">
	<h3>관리자 페이지</h3>
	
		
		<div class="text-left">
			<input type=button value="게시판관리" class="btn btn-menu" onclick="location.href='./AdminPage.lo?action=board'">
			<input type=button value="캘린더관리" class="btn btn-menu" onclick="location.href='./AdminPage.lo?action=calendar'">
		</div>
		<hr>
		<div class="view_sec">
		
		<%if(action.equals("board")){ %>
		<h3>게시판 현황</h3>
		<table class="table table-responsive mb50" border=1>
		<caption class="sr-only">조회수 조사</caption>
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr class=" th_color">
					<th scope="col">번호</th>
					<th scope="col">공지게시판</th>
					<th scope="col">자유게시판</th>
					<th scope="col">QNA게시판</th>
					<th scope="col">합격후기게시판</th>
				</tr>													
			</thead>
			<tbody>
			<tr>
			<th class="th_color">총게시글수</th>
			<td><%=ldao.getboardMaxCount("IT_NOTICE_BOARD") %></td>
			<td><%=ldao.getboardMaxCount("IT_COMUNITY_BOARD") %></td>
			<td><%=ldao.getboardMaxCount("IT_QNA_BOARD") %></td>
			<td><%=ldao.getboardMaxCount("IT_COMPLETE_BOARD") %></td>
			
			</tr>
			<tr>
			<th class="th_color">총조회수</th>
			<td><%=ldao.getReadSumCount("IT_NOTICE_BOARD") %></td>
			<td><%=ldao.getReadSumCount("IT_COMUNITY_BOARD") %></td>
			<td><%=ldao.getReadSumCount("IT_QNA_BOARD") %></td>
			<td><%=ldao.getReadSumCount("IT_COMPLETE_BOARD") %></td>
			</tr>
			<tr>
			<th class="th_color">평균조회수</th>
			<td><%=ldao.getReadAvgCount("IT_NOTICE_BOARD") %></td>
			<td><%=ldao.getReadAvgCount("IT_COMUNITY_BOARD") %></td>
			<td><%=ldao.getReadAvgCount("IT_QNA_BOARD") %></td>
			<td><%=ldao.getReadAvgCount("IT_COMPLETE_BOARD") %></td>
			</tr>
			<tr>
			<th class="th_color">가장높은조회수</th>
			<td><%=ldao.getReadMaxCount("IT_NOTICE_BOARD") %></td>
			<td><%=ldao.getReadMaxCount("IT_COMUNITY_BOARD") %></td>
			<td><%=ldao.getReadMaxCount("IT_QNA_BOARD") %></td>
			<td><%=ldao.getReadMaxCount("IT_COMPLETE_BOARD") %></td>
			</tr>
			<tr>
			<th class="th_color">읽지않은게시물수</th>
			<td><%=ldao.getReadZeroCount("IT_NOTICE_BOARD") %></td>
			<td><%=ldao.getReadZeroCount("IT_COMUNITY_BOARD") %></td>
			<td><%=ldao.getReadZeroCount("IT_QNA_BOARD") %></td>
			<td><%=ldao.getReadZeroCount("IT_COMPLETE_BOARD") %></td>
			</tr>
			
			</tbody>
		</table>
		<h3>현재 최고 인기글</h3>
		<table class="table table-responsive mb50" border=1>
		<caption class="sr-only">인기글</caption>
			<colgroup>
				<col width="20%">
				<col width="40%">
				<col width="10%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr class=" th_color">
					<th scope="col">게시판분류</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>													
			</thead>
			<tbody>
			
				<tr>
				<% board=ldao.getDetail(ldao.getReadMaxBoardNum("IT_NOTICE_BOARD"), "IT_NOTICE_BOARD");%>
			        <th class="th_color">일정게시판</th>
			        <td class="tit"><a target="_blank" href="./View.lo?num=<%=board.getBOARD_NUM()%>&board_type=notice"><%=board.getBOARD_SUBJECT() %> </a></td>
			        <td><%=board.getBOARD_NAME() %> </td> 
			        <td><%=board.getBOARD_DATE() %></td>
			        <td><%=board.getBOARD_COUNT() %></td>
		        </tr>
		        <tr>
				<% board=ldao.getDetail(ldao.getReadMaxBoardNum("IT_COMUNITY_BOARD"), "IT_COMUNITY_BOARD");%>
			        <th class="th_color">자유게시판</th>
			        <td class="tit"><a target="_blank" href="./View.lo?num=<%=board.getBOARD_NUM()%>&board_type=free"><%=board.getBOARD_SUBJECT() %> </a></td>
			        <td><%=board.getBOARD_NAME() %> </td> 
			        <td><%=board.getBOARD_DATE() %></td>
			        <td><%=board.getBOARD_COUNT() %></td>
		        </tr>
		        <tr>
				<% board=ldao.getDetail(ldao.getReadMaxBoardNum("IT_QNA_BOARD"), "IT_QNA_BOARD");%>
			        <th class="th_color">질문게시판</th>
			        <td class="tit"><a target="_blank" href="./View.lo?num=<%=board.getBOARD_NUM()%>&board_type=qna"><%=board.getBOARD_SUBJECT() %> </a></td>
			        <td><%=board.getBOARD_NAME() %> </td> 
			        <td><%=board.getBOARD_DATE() %></td>
			        <td><%=board.getBOARD_COUNT() %></td>
		        </tr>
		        <tr>
				<% board=ldao.getDetail(ldao.getReadMaxBoardNum("IT_COMPLETE_BOARD"), "IT_COMPLETE_BOARD");%>
			        <th class="th_color">합격후기게시판</th>
			        <td class="tit"><a target="_blank" href="./View.lo?num=<%=board.getBOARD_NUM()%>&board_type=complete"><%=board.getBOARD_SUBJECT() %> </a></td>
			        <td><%=board.getBOARD_NAME() %> </td> 
			        <td><%=board.getBOARD_DATE() %></td>
			        <td><%=board.getBOARD_COUNT() %></td>
		        </tr>
		    
			</tbody>
			</table>
			<%} else{ %>
			
			
			<form name ="holiday_insert" action="./CalendarSubAdd.lo" >
			<h3>기념일 추가 </h3>
			<input type=hidden name=pagenum value=<%=pagenum %>>
			<input type=hidden name=year value=<%=year %>>
			<input type=hidden name=month value=<%=month %>>
			<input type=hidden name=action value=<%=action %>>
			<input type=date id="ITC_DATE1" name=ITC_DATE min="<%=year-1 %>-01-01" max="<%=year+1 %>-12-31" class="textinput"> 
			휴일체크<input id="ITC_TYPE" name=ITC_TYPE type = text size=2 class=textinput value=0>(0(평일),1(휴일))
			<input id="ITC_NAME1" name=ITC_NAME type = text size=10 value="내용" class=textinput>
			&nbsp;<input type = button value="기념일추가" class="btn btn-gray" onclick="javascript:holidaysubmit()">
			</form>
			
			<form name ="workday_insert" action="./CalendarConAdd.lo">
			<h3>일정 추가 </h3>
			<input type=hidden name=pagenum value=<%=pagenum %>>
			<input type=hidden name=year value=<%=year %>>
			<input type=hidden name=month value=<%=month %>>
			<input type=hidden name=action value=<%=action %>>
			<input type=date  id="ITC_DATE2" name=ITC_DATE min="<%=year-1 %>-01-01" max="<%=year+1 %>-12-31" class="textinput"> 
			<input name="ITC_NAME" id="ITC_NAME2" type = text size=10 value="내용" class=textinput>  
			<input name="ITC_LINK" id="ITC_LINK" type = text size=10 value="(링크)" class=textinput>
			&nbsp;<input type = button value="일정추가" class="btn btn-gray" onclick="javascript:workdaysubmit()">
			</form> 
			
			<form name ="holiday_delete" action="./CalendarSubDel.lo" >
			<h3>기념일 삭제 </h3>
			<input type=hidden name=pagenum value=<%=pagenum %>>
			<input type=hidden name=year value=<%=year %>>
			<input type=hidden name=month value=<%=month %>>
			<input type=hidden name=action value=<%=action %>>
			삭제하려는 기념일 이름<input id="ITC_NAME3" name=ITC_NAME type = text size=10 value="이름" class=textinput>
			&nbsp;<input type = button value="기념일삭제" class="btn btn-gray" onclick="javascript:holidaydelsubmit()">
			</form>
			
			<form name ="workday_delete" action="./CalendarConDel.lo">
			<h3>일정 삭제 </h3>
			삭제하려는 일정 이름 
			<input type=hidden name=pagenum value=<%=pagenum %>>
			<input type=hidden name=year value=<%=year %>>
			<input type=hidden name=month value=<%=month %>>
			<input type=hidden name=action value=<%=action %>>
			<input name="ITC_NAME" id="ITC_NAME4" type = text size=10 value="이름" class=textinput>  
			&nbsp;<input type = button value="일정삭제" class="btn btn-gray" onclick="javascript:workdaydelsubmit()">
			</form> 
			
			<div class="calendarview">
				<div class="text-center">
				<input type=button value="이전" class="btn btn-success" onclick="javascript:prevmonth()"><font>일정캘린더(<%=year %>년 <%=month+1 %>월)</font> 
				<input type=button value="다음" class="btn btn-success" onclick="javascript:nextmonth()">
				</div>
				
				<table class="table calendar" border=1>
				
				
				<%
				for(int i=0;i<35;i++){
					if((i+1)>=startday&&(i+1)<endday+startday){
						dayvalue[i]=day;
						day++;
					}
					else
					dayvalue[i]=0;
					System.out.println(dayvalue[i]);
				}
				
				%>
				
				<%for(int row=1;row<6;row++){%>
				<%for(int i=1;i<8;i++){%>
				<%if(start){start=false;%><tr><%} %><th>
                  <%if(dayvalue[num]!=0){ 
                      if(ldao.getCalendarDaytype(year, month+1, dayvalue[num])==0){%>
                      <font class="size_20"><%=dayvalue[num]%></font>&nbsp;
                      <font class="size_10"><%=ldao.getCalendarDayname(year, month+1, dayvalue[num])%></font>
                      <%}else {%>
                      <font class="size_20 red"><%=dayvalue[num]%></font>&nbsp;
                      <font class="size_10 red"><%=ldao.getCalendarDayname(year, month+1, dayvalue[num])%></font>
                      <% }%>
                      <%} %></th><%if(i%7==0){ %></tr><%start=true; } %>
				<%num++; } %><!-- thfor end -->
				
				<%for(int i=1;i<8;i++){%>
				<%if(middle){middle=false;%><tr><%} %><td>
                <%if(dayvalue[num2]!=0){ %>
                  <%LicenseCalendar_ConVO calendarvo = new LicenseCalendar_ConVO(); %>
                  <%calendarvo = ldao.getCalendarContent(year, month+1, dayvalue[num2]); %>
                  <%if(calendarvo!=null){ %>
                  <%if(calendarvo.getITC_LINK().equals("none")){ %> <!-- 링크가 없는경우 -->
                  <%= calendarvo.getITC_NAME() %>
                  <%} else {%>
                  <a href=<%=calendarvo.getITC_LINK() %>><%= calendarvo.getITC_NAME() %>(<%=month+1 %>.<%=dayvalue[num2]%>)</a>
                  <%} }%><!-- 널체크 닫기 -->
                <%} %></td>
                <%if(i%7==0){ %></tr><%middle=true; } %>
				<%num2++; } %><!-- tdfor end -->
				<%} %><!--  row for end -->
				</table>
				</div>
				<%} %>
		</div>
	
	</div>
	</div>
  <jsp:include page="/inc/footer.jsp" flush="false"></jsp:include>
</div>
</div>
</body>
</html>