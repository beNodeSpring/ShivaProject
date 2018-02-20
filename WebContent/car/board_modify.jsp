<%-- 글 수정 폼 페이지 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.shiva.car.vo.*"%>
<%
	//저장되어 있던 글 내용 데이터를 화면에 보여주기 위해 boarddata 속성의 데이터를  가져옵니다.
	BoardVO board = (BoardVO) request.getAttribute("board");
%>
<!Doctype html>
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
<title>자유 게시판</title>
<script>
	function modifyboard() {
		modifyform.submit();
	}
</script>
</head>
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
<body>
	<jsp:include page="/inc/header.jsp"></jsp:include>
	<div class="car_section">
		<div class="container">
			<div class="row">
				<div class="modal-content">

					<form action="CarBoardModify.co" method="post" name="modifyform">
						<input type="hidden" name="BOARD_NO"
							value=<%=board.getBOARD_NO()%>>
						<div>
							<form class=board>
								<table class="table table-responsive mb50">
									<tr class="tr_th">
										<th colspan=5>자유게시판 수정하기</th>
									</tr>
									<tr>
										<td>
											<div>글쓴이</div>
										</td>
										<td><%=board.getBOARD_NAME()%></td>
									</tr>
									<tr>
										<td>
											<div>제 목</div>
										</td>
										<td><input name="BOARD_SUBJECT" size="50" maxlength="100"
											value="<%=board.getBOARD_SUBJECT()%>"></td>
									</tr>

									<tr>
										<td>
											<div>내 용</div>
										</td>
										<td><textarea name="BOARD_CONTENT" cols="67" rows="15"><%=board.getBOARD_CONTENT()%></textarea>
										</td>
									</tr>


									</tr>



									<tr class="center">
										<td colspan="2" class="h30 lime"><a
											href="javascript:modifyboard()" class="btn btn btn-warning">수정하기</a>&nbsp;&nbsp;
											<a href="javascript:history.go(-1)" class="btn btn-success">뒤로하기</a>&nbsp;&nbsp;
										</td>
									</tr>
									</div>
									</form>
								</table>
							</form>
						</div>
					</form>
				</div>
			</div>
			
		<jsp:include page="/inc/footer.jsp"></jsp:include>			
		</div>
	</div>

</body>
</html>