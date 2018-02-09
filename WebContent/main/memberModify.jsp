<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개발의 시발점</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="/ShivaProject/css/style.css" rel="stylesheet">
<link href="/ShivaProject/css/sub.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">    
<!-- JavaScript -->
<script src="/ShivaProject/js/jquery-3.3.1.min.js"></script> 
<!--<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script> -->
<script src="/ShivaProject/js/bootstrap.min.js"></script>
<script src="/ShivaProject/js/main.js"></script>
</head>
<body>
${ error } <!-- request.getAttribute("error") -->

<!-- 아래는 예시 내가 만든 구조니깐 참고하라는것 -->
<div class="main-body">	
	<div class="container">
		<div class="row">               
			<h2 class="sub_tit">회원정보 수정</h2>
			<div class="sub_page join_page modal-content">
				<form action="../memberInsert.shiva" method="get" >
					<div class="form-group">
					    <label for="id">ID</label>
					    <input type="text" class="form-control" size="10" name="id" id="id" value="${sessionScope.id}" readonly>
					</div>
					<div class="form-group">
					    <label for="pass">비밀번호</label>
					    <input type="password" class="form-control" size="10" name="passwd" id="passwd">
					</div>
					<div class="form-group">
					    <label for="id">name</label>
					    <input type="text" class="form-control" size="10" name="name" id="name">
					</div>
					<div class="form-group">
					    <label for="id">gender</label>
					    <div>
							남 <input type="radio" class="" size="10" name="group" id="gM" checked>
						        여 <input type="radio" class="" size="10" name="group" id="gW">
					    </div>
					</div>
					<div class="form-group">
					    <label for="id">mail</label>
					    <input type="text" class="form-control" size="10" name="mail" id="mail">
					</div>
					<div class="form-group">
					    <label for="id">phone</label>
					    <input type="text" class="form-control" size="10" name="phone" id="phone">
					</div>
					<div class="text-right">
						<input type=submit value="수정" class="btn btn-primary">
						<a href="javascript:history.back()" class="btn btn-warning">취소</a>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>

</body>
</html>