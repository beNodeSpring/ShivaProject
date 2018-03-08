<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shiva.license.dao.*"%>
<%@ page import="com.shiva.license.vo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일정캘린더</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/ShivaProject/css/style.css" rel="stylesheet">
<link href="/ShivaProject/css/sub.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
* {
	margin: 0;
	padding: 0
}
body {
	background-image: url(images/bg_main-body6.jpg)
}

font{font-size:30pt; font-weight:bold}
.calendar tr th{background-color:lightblue; width:13% ;height:5%}
.calendar tr td{background-color:#add8e6ba; height:100px}
.calendar tr th:nth-child(7n+1){color:red; background-color:pink}
.calendar tr td:nth-child(7n+1){color:red; background-color:#ffc0cb94}
.calendar tr th:nth-child(7n){background-color:#6666ff}
.calendar tr td:nth-child(7n){background-color:#6666ff9e}
.calendar{border-color:white}
.size_20{font-size:15pt}
.size_10{font-size:10pt}
.red{color:red}
.modal-content{margin-top:70px; padding: 60px}
</style>

</head>
<%boolean start=true; boolean middle=true; int number=0;//캘랜더 그리기사용변수들%>
<%Calendar calendar; calendar=Calendar.getInstance(); int startmonth=calendar.get(Calendar.MONTH); %>
<%int year=0;int month=0; int startday=0;int endday=0; %>
<%int[] dayvalue=new int[35]; %>
<%LicenseDAO ldao= new LicenseDAO(); %>
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
var pnum=<%=pagenum%>;

function nextmonth()
{
	var ingmonth=<%=month%>;
	if(pnum<3)
		{
		pnum++;
		if(ingmonth==11) //12월에서 누른경우 전년도 1월로
		{
			location.href="./Calendar.lo?year="+<%=year+1%>+"&month="+<%=0%>+"&pagenum="+<%=pagenum+1%>;
		}
		else
		location.href="./Calendar.lo?year="+<%=year%>+"&month="+<%=month+1%>+"&pagenum="+<%=pagenum+1%>;
		}
	
	}
function prevmonth()
{
	var ingmonth=<%=month%>;
	if(pnum>-3)
		{
		pnum--;
		if(ingmonth==0) //1월에서 누른경우 전년도 12월로
		{
			location.href="./Calendar.lo?year="+<%=year-1%>+"&month="+<%=11%>+"&pagenum="+<%=pagenum-1%>;
		}
		else
		location.href="./Calendar.lo?year="+<%=year%>+"&month="+<%=month-1%>+"&pagenum="+<%=pagenum-1%>;
		}
	
	}
</script>


<body>
  <jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
	<div class="license_section">
		<div class="container">
			<div class="row">
				<div class="modal-content">
				<div class="text-center">
				<input type=button value="이전" class="btn btn-success" onclick="javascript:prevmonth()"><font>일정캘린더(<%=year %>년 <%=month+1 %>월)</font> 
				<input type=button value="다음" class="btn btn-success" onclick="javascript:nextmonth()">
				</div>
				<table class="table calendar" border=1>
				
				
			
				<%
				//여기서 배열에 세팅!
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
				<div class="text-right">
					<input type=button value="IT자격증 메인으로" class="btn btn-primary" onclick="location.href='./main.lo?board_type=notice'">
				</div>
							
				</div>
			</div>

       <jsp:include page="/inc/footer.jsp" flush="false"></jsp:include>
		</div>
	</div>

</body>

</html>