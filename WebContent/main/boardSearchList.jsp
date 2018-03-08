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

<jsp:include page="/inc/header.jsp" flush="false" />

<div class = "notice_board">
	<div class = "container">
		<div class = "row">

			<div class="modal-content bd_padding">
				<h3 class="mb50">검색된 게시글 : ${resultLen}</h3>
				
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
						<c:set var="resultLen" value="${resultLen}" />
						<c:choose>
							<c:when test="${resultLen==0}">
								<td colspan="6" class="td_result_null">검색결과가 없습니다</td>
							</c:when>
							<c:otherwise>
							
								<c:forEach items="${boardList}" var="dto">
							        <tr>
								        <td>
								        	<strong class="bd_num">${dto.num}</strong>
								        </td>
								        <td class="tit">
								        	<c:forEach begin="1" end="${dto.lev}">			        
												&nbsp;<i class="fa fa-long-arrow-right"></i>
											</c:forEach>
								        	<a href="./boardRead.bbs?num=${dto.num}">${dto.subject}</a>
								        </td>
								        <td>${dto.id}</td> 
								        <td>${dto.writeDate}</td>
								        <td>${dto.readCnt}</td>
								        <td>${dto.childCnt}</td>
							        </tr>
								</c:forEach>				
							
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<!--/ 게시판 내용 -->
				
				<div class="text-right btn_wrap">
					<a href="./boardList.bbs" class="btn btn-primary">목록</a>
				</div>
			</div>

		</div>
	</div>
</div>

<script src="/ShivaProject/js/main.js"></script>
</body>
</html>