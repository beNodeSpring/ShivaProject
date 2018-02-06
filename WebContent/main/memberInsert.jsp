<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 생성</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
</head>
<body>
${ error } <!-- request.getAttribute("error") -->

<div class="container">
	<form action="../memberInsert.shiva" method="get" >
		<div class="form-group">
		    <label for="id">ID</label>
		    <input type="text" class="form-control" size="10" name="id" id="id">
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
				남 <input type="radio" class="" size="10" name="group" id="gM">
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
		<input type=submit value="회원가입" class="btn btn-primary">	
	</form>
</div>
</body>
</html>