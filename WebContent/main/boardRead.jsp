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
				<h3 class="mb50">글 열람</h3>
				<div class="row">
			
					<table class="table table-responsive read_table">
						<caption class="sr-only">게시판 제목</caption>
						<colgroup>
							<col width="30%">
							<col width="15%">
							<col width="15%">
							<col width="15%">
							<col width="15%">
							<col width="10%">
						</colgroup>
						<tbody>
							<tr>
								<th>아이디</th>
								<td colspan="5" class="lft">${boardRead.id}</td>
							</tr>
							<tr>
								<th>제목</th>
								<td colspan="5" class="lft">${boardRead.subject}</td>
							</tr>
							<tr>
								<th>날짜</th>
								<td class="lft">${boardRead.writeDate}</td>
								<th>조회수</th>
								<td class="lft">${boardRead.readCnt}</td>
								<th>답글수</th>
								<td class="lft">${boardRead.childCnt}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="5" class="lft td_content">${boardRead.content}</td>
							</tr>
						</tbody>
					</table>
					<div class="text-right btn_wrap">
						<a href="./boardList.bbs" class="btn btn-primary">목록</a>		
						<c:set var="userId" value="${sessionScope.id}" />
						<c:if test="${userId == 'admin'}">
							<a href="./boardReplyForm.bbs?num=${boardRead.num}" class="btn btn-info">답글</a>
							<a href="./boardUpdateForm.bbs?num=${boardRead.num}" class="btn btn-warning">수정</a>
							<a href="./boardDelete.bbs?num=${boardRead.num}" class="btn btn-danger">삭제</a>
						</c:if>						
					</div>
			
				</div>
			</div>

		</div>
	</div>
</div>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>