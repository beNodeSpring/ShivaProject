<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.shiva.resume.vo.*" %>
<!DOCTYPE>
<html>
<head>
<title>Resume Main Page</title>
<link rel="stylesheet" type="text/css" href="/ShivaProject/css/resume.css"></link>
<link rel="stylesheet" type="text/css" href="/ShivaProject/css/style.css"></link>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="/ShivaProject/js/jquery-3.3.1.min.js"></script>
<script src="/ShivaProject/js/resume.js"></script>
</head>
<body>
<jsp:include page="/resume/header.jsp"/> 

<div class = "resume_wrap">
	<div class = "container">
		<div class = "row">
			<div class="modal-content">
	
				<form action="ResumeUpdate.ro" method="post" enctype="multipart/form-data" name="resumeform">
					<!-- Page Container -->
					<div id="pagecontainer">
						<!-- Left Column -->
						<div id="leftcol">
							<!-- Profile Picture -->
							<div id="prof">
								<div id="profcont">
									<img src="./profilepic/<%=request.getAttribute("PIC")%>" id="profimg">
								</div>
								<input name="yourpic" type="file" id="profupload">
								<div>
									<input type="text" id="profid" name="tempid" value=<%=session.getAttribute("id") %>>가 나의 아이디입니다.</input><br>
								</div>
							</div>
							<!-- Interested In -->
							<div id="int">
								<div id="int_title">Interested In</div>
								<table id="inttable">
									<tr>
										<td><input id="Java" name="Java" value="Java" readonly><input type="hidden" id="JavaVal" name="JAVAVAL" value=<%=request.getAttribute("JAVAVAL")%>></td>
										<td><input id="Python" name="Python" value="Python" readonly><input type="hidden" id="PythonVal" name="PYTHONVAL" value=<%=request.getAttribute("PYTHONVAL")%>></td>
									</tr>
									<tr>
										<td><input id="C" name="C" value="C" readonly><input type="hidden" id="CVal" name="CVAL" value=<%=request.getAttribute("CVAL")%>></td>
										<td><input id="Ruby" name="Ruby" value="Ruby" readonly><input type="hidden" id="RubyVal" name="RUBYVAL" value=<%=request.getAttribute("RUBYVAL")%>></td>
									</tr>
									<tr>
										<td><input id="JavaScript" name="JavaScript" value="JavaScript" readonly><input type="hidden" id="JavaScriptVal" name="JAVASCRIPTVAL" value=<%=request.getAttribute("JAVASCRIPTVAL")%>></td>
										<td><input id="Cshap" name="Cshap" value="C#" readonly><input type="hidden" id="CshapVal" name="CSHAPVAL" value=<%=request.getAttribute("CSHAPVAL")%>></td>
									</tr>
									<tr>
										<td><input id="PHP" name="PHP" value="PHP" readonly><input type="hidden" id="PHPVal" name="PHPVAL" value=<%=request.getAttribute("PHPVAL")%>></td>
										<td><input id="Objective-C" name="Objective-C" value="Objective-C" readonly><input type="hidden" id="Objective-CVal" name="OBJECTIVECVAL" value=<%=request.getAttribute("OBJECTIVECVAL")%>></td>
									</tr>
									<tr>
										<td><input id="SQL" name="SQL" value="SQL" readonly><input type="hidden" id="SQLVal" name="SQLVAL" value=<%=request.getAttribute("SQLVAL")%>></td>
										<td><input id="Cplus" name="Cplus" value="C++" readonly><input type="hidden" id="CplusVal" name="CPLUSVAL" value=<%=request.getAttribute("CPLUSVAL")%>></td>
									</tr>
								</table>
							</div>
						</div>
						<!-- Middle Column -->
						<div id="midcol">
							<!-- Today's Feeling -->
							<div id="tf">
								<div id="tf_title">Today's Feeling</div>
								<div id="tf_button"></div>
								<input type="text" id="tf_text" name="TODAYFEELING" value=<%=request.getAttribute("TODAYFEELING") %>
									maxlength="19" disabled>
							</div>
							<!-- Work Experience -->
							<div id="work">
								<div id="work_title">Work Experience</div>
								<div id="work_remove"></div>
								<div id="work_add"></div>
								<div id="update_button"></div>
								<div id="board_button"></div>
								<div id="workpage1">
									<table id="worktable1">
										<thead>
											<tr>
												<th><input type='text' value='Project Name'></th>
												<th><input type='text' value='Duration'></th>
											</tr>
										</thead>
										<tbody>
										<%if(request.getAttribute("PROJECT9")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT9" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT9") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION9" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION9") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT8")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT8" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT8") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION8" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION8") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT7")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT7" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT7") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION7" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION7") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT6")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT6" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT6") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION6" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION6") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT5")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT5" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT5") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION5" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION5") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT4")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT4" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT4") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION4" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION4") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT3")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT3" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT3") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION3" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION3") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT2")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT2" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT2") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION2" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION2") %>></td>
											</tr>
											<%} %>
										<%if(request.getAttribute("PROJECT1")==null)
											{}
											else {%>
											<tr style ='height : 5vh;'>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="PROJECT1" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("PROJECT1") %>></td>
												<td style = 'border-bottom:1px solid skyblue'><input type='text' name="DURATION1" style = 'background-color:transparent; border:none;' value = <%=request.getAttribute("DURATION1") %>></td>
											</tr>
											<%} %>
										</tbody>
									</table>
								</div>
								<div id="workpage2">
									<table id="worktable2">
										<thead>
											<tr>
												<th><input type='text' value='Project Name'></th>
												<th><input type='text' value='Duration'></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="2"><textarea rows="7" name="TEXT1"><%=request.getAttribute("TEXT1") %></textarea>
													<textarea rows="7" name="TEXT2"><%=request.getAttribute("TEXT2") %></textarea> <textarea
														rows="7" name="TEXT3"><%=request.getAttribute("TEXT3") %></textarea> <textarea rows="7"
														name="TEXT4"><%=request.getAttribute("TEXT4") %></textarea> <textarea rows="7"
														name="TEXT5"><%=request.getAttribute("TEXT5") %></textarea> <textarea rows="7"
														name="TEXT6"><%=request.getAttribute("TEXT6") %></textarea> <textarea rows="7"
														name="TEXT7"><%=request.getAttribute("TEXT7") %></textarea> <textarea rows="7"
														name="TEXT8"><%=request.getAttribute("TEXT8") %></textarea> <textarea rows="7"
														name="TEXT9"><%=request.getAttribute("TEXT9") %></textarea></td>
											</tr>
										</tbody>
									</table>
									<div id="work_return"></div>
								</div>
							</div>
						</div>
						<!-- Right Column -->
						<div id="rightcol">
							<div id="interview"></div>
							<div id="ad"></div>
						</div>
					</div>
				</form>

			</div>
		</div>
		
		<jsp:include page="/resume/footer.jsp"/> 
	</div>
</div>
</body>
</html>