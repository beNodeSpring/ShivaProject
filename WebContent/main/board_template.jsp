<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">  
<title>회원 생성</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.table-responsive *{text-align: center;}
.table-responsive .tit{text-align: left;}
.form-control{display: inline-block; max-width: calc(100% - 60px);}
.tr_th th{background: #337ab7; color: #fff; font-size: 14px;}
.mb30{margin-bottom: 30px;}
.mb40{margin-bottom: 40px;}
.mb50{margin-bottom: 50px;}
</style>
</head>
<body>
<%-- Notice
---- 테이블의 td의 칸 너비를 조절하고 싶을때 
---- colgroup 안에 col width값 변경(모두 합쳤을때 100%가 되게 할것)
 --%>
<div class="container">
	<h3>게시판 템플릿</h3>
	<form class="form-inline" action="" method="get" >
		<div class="text-right mb30">
			<input type="text" class="form-control" name="" id="">
			<input type="button" class="btn btn-info" name="" id="" value="검색">
		</div>
		<table class="table table-responsive mb50" summary="게시판 설명">
			<caption class="sr-only">게시판 제목</caption>
			<colgroup>
				<col width="10%">
				<col width="50%">
				<col width="10%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr class="tr_th">
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
			        <td><i class="fa fa-file"></i></td> 
			        <td>2018-02-06</td>
			        <td>109</td>
		        </tr>
		        <tr>
			        <td><strong class="bd_num">13</strong></td>
			        <td class="tit"><a href="#">시바 게시물 제목</a></td>
			        <td><i class="fa fa-file"></i></td> 
			        <td>2018-01-18</td>
			        <td>173</td>
		        </tr>
		        <tr>
			        <td><strong class="bd_num">12</strong></td>
			        <td class="tit"><a href="#">시바 게시물 제목</a></td>
			        <td><i class="fa fa-file"></i></td> 
			        <td>2018-01-18</td>
			        <td>173</td>
		        </tr>
		        <tr>
			        <td><strong class="bd_num">11</strong></td>
			        <td class="tit"><a href="#">시바 게시물 제목</a></td>
			        <td><i class="fa fa-file"></i></td> 
			        <td>2018-01-18</td>
			        <td>173</td>
		        </tr>
		        <tr>
			        <td><strong class="bd_num">10</strong></td>
			        <td class="tit"><a href="#">시바 게시물 제목</a></td>
			        <td><i class="fa fa-file"></i></td> 
			        <td>2018-01-18</td>
			        <td>173</td>
		        </tr>

			</tbody>
		</table>
		<div class="text-right">
			<input type=submit value="버튼파랑" class="btn btn-primary">
			<input type=submit value="버튼초록" class="btn btn-success">
			<input type=submit value="버튼하늘" class="btn btn btn-info">
			<input type=submit value="버튼주황" class="btn btn btn-warning">
			<input type=submit value="버튼빨강" class="btn btn btn-danger">
		</div>
	</form>
</div>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>