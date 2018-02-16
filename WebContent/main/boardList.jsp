<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<div class="container">
	<h3>공지사항</h3>
	
	<!-- 게시물 검색 -->
	<form class="form-inline" action="" method="get" >
		<div class="text-right mb30">
			<select name="searchOpt" id="">
				<option value="subject">제목</option>
				<option value="content">본문</option>
				<option value="both">제목+본문</option>
				<option value="name">작성자</option>
			</select>
			<input type="text" class="form-control" name="searchWord" id="">
			<input type="submit" class="btn btn-info" name="" id="" value="검색">
		</div>
	</form>
	<!--/ 게시물 검색 -->
	
	<!-- 게시판 내용 -->
	<table class="table table-responsive mb50" summary="게시판 설명">
		<caption class="sr-only">게시판 제목</caption>
		<colgroup>
			<col width="8%">
			<col width="50%">
			<col width="13%">
			<col width="13%">
			<col width="8%">
			<col width="8%">
		</colgroup>
		<thead>
			<tr class="tr_th">
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일</th>
				<th scope="col">조회수</th>
				<th scope="col">답글수</th>
			</tr>													
		</thead>
		<tbody>
			<c:forEach items="${boardList}" var="dto">
		        <tr>
			        <td>
						<c:forEach begin="1" end="${dto.lev}">			        
							%npsp;<i class="fa fa-long-arrow-right"></i>
						</c:forEach>
			        	<strong class="bd_num">${dto.num}</strong>
			        </td>
			        <td class="tit"><a href="./boardRead.bbs?num=${dto.num}">${dto.subject}</a></td>
			        <td>${dto.id}</td> 
			        <td>${dto.writeDate}</td>
			        <td>${dto.readCnt}</td>
			        <td>${dto.childCnt}</td>
		        </tr>
			</c:forEach>
		</tbody>
	</table>
	<!--/ 게시판 내용 -->
	
	<!-- 페이징 -->
	<div class="paging">
		<a href="./boardList.bbs">첫페이지로</a>
		<c:forEach var="i" begin="1" end="${pageCnt}">			        
			<a href="./ShivaProject/boardList.bbs?curPage=${i}">[${i}]</a>
		</c:forEach>			
	</div>
	<!--/ 페이징 -->
	
	<div class="text-right btn_wrap">
		<a href="./boardWriteForm.bbs" class="btn btn-primary">글작성</a>
<!-- 			<input type=submit value="버튼파랑" class="btn btn-primary">
			<input type=submit value="버튼초록" class="btn btn-success">
			<input type=submit value="버튼하늘" class="btn btn btn-info">
			<input type=submit value="버튼주황" class="btn btn btn-warning">
			<input type=submit value="버튼빨강" class="btn btn btn-danger"> -->
	</div>
</div>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>