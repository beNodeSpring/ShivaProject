<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.shiva.resume.vo.*" %>
<%@ page import = "com.shiva.resume.service.*" %>
<%@ page import = "com.shiva.resume.dao.*" %>
<%@ page import = "com.shiva.resume.controller.*" %>

<%
	List<ResumeBean> resumeList = (ArrayList<ResumeBean>) request.getAttribute("resumelist");
	int resumecount = ((Integer)request.getAttribute("resumecount")).intValue(); 
	int nowpage = ((Integer)request.getAttribute("page")).intValue();
	int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer)request.getAttribute("startpage")).intValue();
	int endpage = ((Integer)request.getAttribute("endpage")).intValue();
%>

<!DOCTYPE>
<html>
<head>
<title>Resume List</title>
<link rel="stylesheet" type="text/css" href="/ShivaProject/css/resume.css"></link>
<link rel="stylesheet" type="text/css" href="/ShivaProject/css/style.css"></link>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/inc/header.jsp"></jsp:include>

<div class = "resume_wrap">
	<div class = "container">
		<div class = "row">
			<div class="modal-content pd60" id="resumecontainer">
			
				<table class="table table-striped" id="resumetable">
				<%
					if(resumecount > 0){ //등록된 글이 있는 경우
				%>
					<tr>
						<th width = 10%>ID</th>
						<th width = 10%>등록된 날짜</th>
						<th width = 40%>오늘의 한마디</th>
						<th width = 30%>최근 진행한 프로젝트 명</th>
						<th width = 10%>레주메 보기</th>
					</tr>
				<%
					for(int i = 0; i < resumeList.size(); i++){
						ResumeBean oneresume = (ResumeBean)resumeList.get(i);
				%>
					<tr>
						<td>
							<%=oneresume.getRESUME_ID() %>
						</td>
						<td>
							<%=oneresume.getRESUME_DATE() %>
						</td>
						<td>
							<%=oneresume.getTODAYFEELING() %>
						</td>
						<td>
							<%=oneresume.getPROJECT1() %>
						</td>
						<td>
							<a href = "./myResume.ro?want=<%=oneresume.getRESUME_ID()%>">레주메 보기</a>
						</td>
					</tr>
				<%
					}
					} else { //등록된 글이 없는 경우
				%>
					<tr>
						<th width = 10%>ID</th>
						<th width = 10%>등록된 날짜</th>
						<th width = 40%>오늘의 한마디</th>
						<th width = 26%>최근 진행한 프로젝트 명</th>
						<th width = 14%>레주메 보기</th>
					</tr>
					<tr>
						<td colspan = "5">등록된 글이 없습니다.</td>
					</tr>
				<%
					}
				%>
				</table>
				<%=session.getAttribute("id") %>으로 로그인 되었습니다.			
			
			</div>
		</div>
		<jsp:include page="/inc/footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>