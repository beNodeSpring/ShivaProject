<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<div class="main-body">	
	<div class="container">
		<div class="row">               
			<h2 class="sub_tit">회원가입완료</h2>
			<div class="sub_page join_page modal-content">
				<h3>회원가입이 완료되었습니다</h3>
				<div class="join_bg">
					<div class="btn_wrap">
						<a href="#" data-target="#layerpop" data-toggle="modal" class="btn btn-primary">Login</a>
						<a href="/ShivaProject" class="btn btn-info">Home</a>
					</div>
				</div>
			</div>
			
			<!-- 로그인 모달창 -->
			<div class="modal fade" id="layerpop" >
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<!-- 닫기(x) 버튼 -->
							<button type="button" class="close" data-dismiss="modal">×</button>
							<!-- header title -->
							<h4 class="modal-title">로그인</h4>
						</div>
						
						<!-- 로그인 폼 -->
						<form action="./memberLogin.shiva" method="post" >
							<div class="modal-body">
								<!-- 로그인 폼 -->
									<div class="form-group">
									    <label for="id">ID</label>
									    <input type="text" class="form-control" size="10" name="id" id="id">
									</div>
									<div class="form-group">
									    <label for="pass">비밀번호</label>
									    <input type="password" class="form-control" size="10" name="passwd" id="passwd">
									</div>
							</div>
			
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">로그인</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!--// 로그인 모달창 -->	
		</div>
	</div>
</div>
</body>
</html>
