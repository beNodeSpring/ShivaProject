<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<jsp:include page="/inc/header.jsp"></jsp:include>

<body>
	<div class="car_section">
		<div class="container">
			<div class="row">
				<div class="modal-content">


						<table class="table table-responsive mb50">
					<form class=board action="./CarBoardWrite.co" name="writeform">
							<tr class="tr_th">
								<th colspan=5></th>
							</tr>
							<tr>
								<td>작성자 : </td>
								<td colspan=4><input type="hidden" name="BOARD_NAME" value="tt"></td>
							</tr>
							<tr>
								<td>글제목 :</td>
								<td colspan=4><input type=text size=50 name="BOARD_SUBJECT"></td>
							</tr>

							<tr>
								<td>내용 :</td>
								<td colspan=4><textarea rows="10" cols="60" name="BOARD_CONTENT"></textarea></td>
							</tr>

						</table>
						<div class="text-right">
							<input type=button value="작성완료" class="btn btn-info"
                        onclick="javascript:writeform.submit()">
							
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

