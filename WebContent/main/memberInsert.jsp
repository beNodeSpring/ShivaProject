<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 생성</title>
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
			<h2 class="sub_tit">회원가입</h2>
			<div class="sub_page join_page modal-content">
				<form action="./memberInsert.shiva" method="post" id="frmMemberJoin" name="frmMemberJoin">
					<div class="form-group">
						<label for="id">아이디</label>
						<input type="text" class="form-control" maxlength="10" name="id" id="id">
						<p id="msgCheckId" class="check_txt_check">아이디를 입력 해주세요</p>
<!-- 						<div class="input-group">
						    <input type="text" class="form-control" maxlength="15" name="id" id="id">
						    <div class="input-group-btn">
							    <input type="button" id="btnIdCheck" class="btn btn-danger" value="아이디 중복체크"/>
						    </div>
						</div> 
						<p id="msgCheckId" class="check_txt_check">아이디 중복체크를 해주세요</p>
						-->
					</div>
					<div class="form-group">
					    <label for="pass">비밀번호</label>
					    <input type="password" class="form-control" maxlength="10" name="passwd" id="passwd">
					</div>
					<div class="form-group">
					    <label for="pass">비밀번호 확인</label>
					    <input type="password" class="form-control" maxlength="10" name="passwdChk" id="passwdChk">
						<p id="msgCheckPw" class="check_txt_check">비밀번호를 입력해주세요</p>
					</div>
					<div class="form-group">
					    <label for="id">이름</label>
					    <input type="text" class="form-control" maxlength="10" name="name" id="name">
					</div>
					<div class="form-group">
					    <label for="id">성별</label>
					    <div>
							남 <input type="radio" id="genM" name="gender" value="남">
						        여 <input type="radio" id="genW" name="gender" value="여">
					    </div>
					</div>
					<div class="form-group">
					    <label for="id">이메일</label>
					    <ul class="li_mail cf">
					    	<li><input type="text" class="form-control" maxlength="10" name="mail" id="mail"></li>
					    	<li>@</li>
					    	<li><input type="text" class="form-control" maxlength="10" name="mailDomain" id="mailDomain"></li>
					    	<li>
							    <select name="selectMail" id="selectMail" class="select_mail">
							    	<option value="">선택</option>
							    	<option value="google.com">google.com</option>
							    	<option value="naver.com">naver.com</option>
							    	<option value="hanmail.net">hanmail.net</option>
							    	<option value="hotmail.com">hotmail.com</option>
							    </select>					    	
					    	</li>
					    </ul>   
					</div>
					<div class="form-group">
					    <label for="id">전화번호</label>
					    <input type="text" class="form-control" maxlength="11" name="phone" id="phone" placeholder="-없이 입력해주세요">
					</div>
					<div class="text-right">
						<button type=button id="btnJoin" class="btn btn-primary">회원가입</button>
						<a href="javascript:history.back()" class="btn btn-warning">메인화면</a>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>