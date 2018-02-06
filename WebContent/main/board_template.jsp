<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 생성</title>
<link href="../css/bootstrap.min.css" rel="stylesheet" >
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/icomoon.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
<p><%= request.getRealPath("/") %></p>
	<h3>게시판 템플릿</h3>
	<i class="fa fa-camera-retro"></i>
	<form action="" method="get" >
		<input type="text" class="form-control" name="" id="">
		<input type="button" class="btn btn-info" name="" id="" value="검색">
		<table class="table" summary="게시판 설명">
			<caption>게시판 제목</caption>
			<colgroup>
				<col width="60px">
				<col width="">
				<col width="80px">
				<col width="120px">
				<col width="60px">
			</colgroup>
			<thead>
				<tr class="info">
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">첨부</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>													
			</thead>
			<tbody>
		        <tr>
			        <td><strong class="bd_noti">공 지</strong></td>
			        <td class="tit"><a href="#">시바 공지사항 </a></td>
			        <td></td> 
			        <td>2018-02-06</td>
			        <td>109</td>
		        </tr>
		        <tr>
			        <td><strong class="bd_num">11</strong></td>
			        <td class="tit"><a href="#">시바 게시물 제목</a></td>
			        <td><i class="fas fa-save"></i></td> 
			        <td>2018-01-18</td>
			        <td>173</td>
		        </tr>
			</tbody>
		</table>

		<input type=submit value="회원가입" class="btn btn-primary">	
	</form>
</div>
</body>
</html>