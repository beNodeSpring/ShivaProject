<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/car.js"></script>
<link href="/ShivaProject/css/style.css" rel="stylesheet">
<link href="/ShivaProject/css/sub.css" rel="stylesheet">
<script>
	function move(){
		location.href="/ShivaProject";
	}
</script>
<style>
body {
	background-image: url("images/bg_main-body5.jpg");
}
</style>
</head>
<body>
<jsp:include page="/inc/header.jsp" flush="false"/>
<h3>클릭</h3>

<input type="button" onclick="javascript:move()" value="시바렌트카"><br>
	
<jsp:include page="/inc/footer.jsp" flush="false"/>
</body>
</html>