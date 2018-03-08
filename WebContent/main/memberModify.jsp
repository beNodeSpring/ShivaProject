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

<!-- 아래는 예시 내가 만든 구조니깐 참고하라는것 -->
<div class="main-body">	
	<div class="container">
		<div class="row">               
			<h2 class="sub_tit">회원정보 수정</h2>
			<div class="sub_page join_page modal-content">
				<form action="./memberUpdate.shiva" method="post" id="frmMemberModi" name="frmMemberModi">
					<div class="form-group">
					    <label for="id">ID</label>
					    <input type="text" class="form-control" maxlength="15" name="id" id="id" value="${sessionScope.id}" readonly>
					</div>
					<div class="form-group">
					    <label for="pass">변경할  비밀번호</label>
					    <input type="password" class="form-control" maxlength="15" name="passwd" id="passwd"  value="${memberVO.getPasswd()}">
					</div>
					<div class="form-group">
					    <label for="pass">비밀번호 확인</label>
					    <input type="password" class="form-control" maxlength="15" name="passwdChk" id="passwdChk">
						<p id="msgCheckPw" class="check_txt_check">비밀번호를 입력해주세요</p>
					</div>					
					
					<div class="form-group">
					    <label for="id">name</label>
					    <input type="text" class="form-control" maxlength="15" name="name" id="name" value="${memberVO.getName()}">
					</div>
					<div class="form-group">
					    <label for="id">gender</label>
					    <div>
                            <c:set var="userGender" value="${memberVO.getGender()}" />
                            <c:choose>
								<c:when test="${userGender == '남'}">
									남 <input type="radio" id="genM" name="gender" value="남" checked>
								        여 <input type="radio" id="genW" name="gender" value="여">
								</c:when>
								<c:when test="${userGender == '여'}">
									남 <input type="radio" id="genM" name="gender" value="남">
								        여 <input type="radio" id="genW" name="gender" value="여" checked>
								</c:when>								
                                <c:otherwise>
									남 <input type="radio" id="genM" name="gender" value="남">
								        여 <input type="radio" id="genW" name="gender" value="여">
                                </c:otherwise>
                            </c:choose>				    	
					    	
					    </div>
					</div>
					<div class="form-group">
					    <label for="id">mail</label>
					    <script>
					    var mailAdress = ${memberVO.getMail()};
					    
					    </script>
					    <ul class="li_mail cf">
					    	<li><input type="text" class="form-control" maxlength="30" name="mail" id="mail" value="${memberVO.getMail()}"></li>
					    	<li>@</li>
					    	<li><input type="text" class="form-control" maxlength="30" name="mailDomain" id="mailDomain" value="${memberVO.getMailDomain()}"></li>
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
					    <label for="id">phone</label>
					    <input type="text" class="form-control" maxlength="15" name="phone" id="phone" value="${memberVO.getPhone()}" placeholder="-없이 입력해주세요">
					</div>
					<div class="text-right">
						<input type=button id="btnJoin" class="btn btn-warning" value="회원정보수정">
						<a href="/ShivaProject/" class="btn btn-primary">메인페이지</a>
					</div>
				</form>
				
			</div>
		</div>
	</div>
</div>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>