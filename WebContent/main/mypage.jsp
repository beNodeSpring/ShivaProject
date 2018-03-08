<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="main-body">	
	<div class="container">
		<div class="row">               
			<h2 class="sub_tit">마이페이지</h2>
			<div class="sub_page join_page modal-content">
				<!-- action의 값은  main.js에서 분기처리 -->
				<form action="" method="post" id="frmMypage" >
					<div class="text-center">
						<img src="/ShivaProject/images/img_siba197.png" alt="사진" />
					</div>
					
					<ul>
						<li><strong>ID : <c:out value='${sessionScope.id}'/></strong></li>
						<li><strong></strong></li>
						<li><strong></strong></li>
					</ul>

					<div class="text-right">
						<a href="javascript:history.back()" class="btn btn-primary">메인페이지</a>
						<input type="button" id="btnMod" class="btn  btn-warning" value="회원정보수정" />
						<input type="button" id="btnDel" class="btn btn-danger" value="회원탈퇴" />
<!-- 						<a href="./memberModify.jsp" class="btn  btn-warning">회원정보수정</a>
						<a href="../memberDelete.shiva" class="btn btn-danger">회원탈퇴</a> -->
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>