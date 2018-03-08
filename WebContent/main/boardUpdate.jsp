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

			<div class="modal-content bd_padding"">
				<h3 class="mb50">글 수정</h3>
				<div class="row">
					<form method="post" action="boardUpdate.bbs">
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
									<td class="lft"><input type="text" class="form-control" name="subject" id="wrtSubject" value="${boardUpdateForm.subject}" maxlength="50" /></td>
								</tr>
								<tr>
									<td colspan="2" class="lft">
										<textarea name="ckeditorNoti" id="ckeditorNoti" rows="10" cols="80">
											${boardUpdateForm.content}
										</textarea>
										<script>
											CKEDITOR.replace('ckeditorNoti');
											var ckContent = CKEDITOR.instances.ckeditorNoti
											ckContent.getData();			
										</script>				
									</td>
								</tr>
							</tbody>
						</table>
						<div class="text-right btn_wrap">
							<input type="hidden" name="num" value="${boardUpdateForm.num}" />
							<a href="./boardList.bbs" class="btn btn-warning">목록</a>
							<input type="submit" id="btnWrtSubmit" class="btn btn-primary pull-right" value="수정" />
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
</div>
<script src="/ShivaProject/js/main.js"></script>
</body>
</html>