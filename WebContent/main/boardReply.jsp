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
<script src="//cdn.ckeditor.com/4.8.0/standard/ckeditor.js"></script>
</head>
<body>

<jsp:include page="/inc/header.jsp" flush="false" />

<div class = "notice_board">
	<div class = "container">
		<div class = "row">

			<div class="modal-content bd_padding">
				<h3 class="mb50">답글 작성</h3>
				<div class="row">
					<form method="post" action="boardReply.bbs">
						<input type="hidden" name="num" value="${boardReplyForm.num}" />
						<input type="hidden" name="ref" value="${boardReplyForm.ref}" />
						<input type="hidden" name="step" value="${boardReplyForm.step}" />
						<input type="hidden" name="lev" value="${boardReplyForm.lev}" />
						<table class="table table-responsive write_table">
							<caption class="sr-only">게시판 제목</caption>
							<colgroup>
								<col width="30%">
								<col width="70%">
							</colgroup>
							<tbody>
								<tr>
									<th>작성자</th>
									<td class="lft"><input type="text" class="form-control" name="id" maxlength="20" value="admin" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>제목</th>
									<td class="lft"><input type="text" class="form-control" id="" name="subject" maxlength="50" placeholder="RE:" /></td>
								</tr>
								<tr>
									<td colspan="2" class="lft">
										<textarea name="ckeditorReply" id="ckeditorReply" rows="10" cols="80">
							</textarea>
										<script>
											CKEDITOR.replace('ckeditorReply');
											var data = CKEDITOR.instances.ckeditorNoti.getData();
										</script>				
									</td>
								</tr>
							</tbody>
						</table>
						<div class="text-right btn_wrap">
							<a href="./boardList.bbs" class="btn btn-warning">목록</a>
							<input type="submit" id="btnWrtSubmit" class="btn btn-primary pull-right" value="글쓰기" />
						</div>
					</form>
				</div>	
			
			<%--
				<div class="row">
					<form method="post" action="boardWrite.bbs">
						<table class="table table-responsive write_table">
							<caption class="sr-only">게시판 제목</caption>
							<colgroup>
								<col width="30%">
								<col width="70%">
							</colgroup>
							<tbody>
								<tr>
									<th>작성자</th>
									<td class="lft"><input type="text" class="form-control" name="id" maxlength="20" value="admin" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>제목</th>
									<td class="lft"><input type="text" class="form-control" id="wrtSubject" name="subject" maxlength="50" /></td>
								</tr>
								<tr>
									<th>내용</th>
									<td class="lft"><textarea class="form-control write_con" id="wrtCcontent" name="content" maxlength="3900"></textarea></td>
								</tr>
							</tbody>
						</table>
						<div class="text-right btn_wrap">
							<a href="./boardList.bbs" class="btn btn-warning">목록</a>
							<input type="submit" id="btnWrtSubmit" class="btn btn-primary pull-right" value="글쓰기" />
						</div>
					</form>
				</div>
				
			 --%>
			</div>

		</div>
	</div>
</div>


<script>
// 다 완성되면 main.js로 옮기기
var writeValidator = {
	excute : function() {
		$('#btnWrtSubmit').on('click', function() {
			if($('#wrtSubject').val().length<3) {
				alert('게시물 제목을 3자 이상 입력해주세요')
				return false;
			} else if($('#wrtCcontent').val().length<10) {
				alert('게시물 내용을 10자 이상 입력해주세요')
				return false;	
			}
		});
	}
}
writeValidator.excute();
</script>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>