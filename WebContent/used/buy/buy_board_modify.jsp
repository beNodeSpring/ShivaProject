<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.shiva.used.VO.*" %>
<%@ page import="com.shiva.used.dao.*" %>
<%@ page import="com.shiva.used.controller.*" %>
<%@ page import="com.shiva.used.service.*" %>
<%
    //저장되어 있던 글 내용 데이터를 화면에 보여주기 위해 boarddata 속성의 데이터를  가져옵니다.
	used_buyVO VO = (used_buyVO)request.getAttribute("VO");
%>
<!Doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width">  
	<title>회원 생성</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
	.table-responsive *{text-align: center;}
	.table-responsive .tit{text-align: left;}
	.form-control{display: inline-block; max-width: calc(100% - 60px);}
	.tr_th th{background: #337ab7; color: #fff; font-size: 14px;}
	.mb30{margin-bottom: 30px;}
	.mb40{margin-bottom: 40px;}
	.mb50{margin-bottom: 50px;}
	<%-- 김재겸/css구분 --%>
	html, body{height:100%;}
	
	.textarea_content{width : 100% ;height:500px!important;text-align:left; resize : none;}
	.used_board{min-height:100%; background : url(/ShivaProject/images/bg_main-body3.jpg) 50% 0 no-repeat;background-size: cover;}
	.buy_board{background : #fff; padding:60px;}
	</style>
	<title>개발의 시발점</title>
	<script src="../../js/jquery-3.3.1.min.js"></script>
	<script>
	function modifyboard(){
		modifyform.submit();
	}
	</script>
</head>

<body>

<jsp:include page="/inc/header.jsp" flush="false" />

<!-- 게시판 수정 -->
<div class = "used_board">
	<div class = "container">
		<div class = "row">
			<div class = "buy_board modal-content">
				<form action="ModifyBuyService.uo" method="post" name="modifyform">
				<input type="hidden" name="num" value=<%=VO.getNUM_B() %>>
				<table class="table table-responsive mb50 table-bordered">
					<caption class="sr-only">게시판 제목</caption>
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<thead>
						<tr valign="middle" class="tr_th">
							<th colspan="2">MVC 게시판 - Modify view</th>
						</tr>
					</thead>
					<tr>
						<td>
							<div>글쓴이</div>
						</td>
						<td class = "tit">
							<%=VO.getNAME_B()%>
							<input type = "hidden" name="NAME_B" value = "<%=VO.getNAME_B()%>">
						</td>
					</tr>
						<tr>
						
						<td>
							<div>제 목</div>
						</td>
						<td class = "tit">
							<input class = "form-control tit" name="SUBJECT_B" size="50" maxlength="100" 
								value="<%=VO.getSUBJECT_B()%>">
						</td>
					</tr>
					<tr>
						<td>
							<div>내 용</div>
						</td>
						<td class = "tit">
							<textarea name="CONTENT_B" class = "textarea_content form-control"><%=VO.getCONTENT_B() %></textarea>
						</td>
					</tr>
					<%if(!(VO.getFILE_B()==null)){ %>
					<tr>
						<td>
							<div>파일 첨부</div>
						</td>
						<td class = "tit">
							<%=VO.getFILE_B() %>
						</td>
					</tr>
					<%} %>
						
					
					
				</table>
					<div class="text-right">
							<a href="javascript:history.go(-1)" class="btn btn btn-warning">뒤로</a>
							<a href="javascript:modifyboard()" class="btn btn btn-info">수정</a>
					</div>
				</form>
			</div>
			
			<jsp:include page="/inc/footer.jsp" flush="false" />
			
		</div>
	</div>
<!-- 게시판 수정 -->
</div>
</body>
</html>
