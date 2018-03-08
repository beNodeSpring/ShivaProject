<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import ="com.shiva.car.vo.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>보드쓰기</title>
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
	background-image: url("img/bg_main-body5.jpg");
}
</style>

</head>
<%
	BoardVO boarddata = (BoardVO)request.getAttribute("boarddata");
%>

<hr>
<body>
	<jsp:include page="/inc/header.jsp"></jsp:include>
	<div class="car_section">
		<div class="container">
			<div class="row">
				<div class="modal-content">

					<form class=board>
						<table class="table table-responsive mb50">
							<tr class="tr_th">
								<th colspan=5>자유 게시판</th>
							</tr>
							<tr>
								<td>작성자 :</td>
								<td colspan=4><%=boarddata.getBOARD_NAME() %></td>
							</tr>
							<tr>
								<td>글제목 :</td>
								<td colspan=4><%=boarddata.getBOARD_SUBJECT() %></td>
							</tr>

							<tr>
								<td>내용 :</td>
								<td colspan=4><textarea readonly rows="10" cols="60"><%=boarddata.getBOARD_CONTENT() %></textarea></td>
							</tr>

						</table>
						<div class="text-right">
							<input type=button value="목록" class="btn btn-info"
								onclick="location.href='./CarBoardList.co'">
							<a href="./CarBoardModifyView.co?BOARD_NO=<%= boarddata.getBOARD_NO() %>" class="btn btn btn-warning">수정하기</a>
							<a href="./CarBoardDelete.co?BOARD_NO=<%= boarddata.getBOARD_NO() %>" class="btn btn btn-danger">삭제하기</a>
						</div>
						<br>
					</form>

				</div>
			</div>
			<jsp:include page="/inc/footer.jsp"></jsp:include>
		</div>
	</div>
</body>

</html>