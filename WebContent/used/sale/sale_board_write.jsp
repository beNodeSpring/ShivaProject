<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	
	.used_board{min-height:100%; background : url(/ShivaProject/images/bg_main-body3.jpg) 50% 0 no-repeat;background-size: cover;}
	.textarea_content{width : 100% ;height:500px!important;text-align:left; resize : none;}
	.sale_board{background : #fff; padding:60px;}
	</style>
	<title>개발의 시발점</title>
	<script src="../../js/jquery-3.3.1.min.js"></script>
	</head>
	<body>
	
		<jsp:include page="/inc/header.jsp" flush="false" />
	
		<div class = "used_board">
			<div class = "container">
				<div class = "row">
					<div class = "sale_board modal-content">
						<div class="text-right">
							<a href="./ListSaleController.uo" class="btn btn-primary">  목록보기  </a>
						</div>
						<form action="addservice_sale.uo" method="post" enctype = "multipart/form-data" name = "boardform">
						<table class="table table-responsive mb50 table-bordered">
							<caption class="sr-only">게시판 제목</caption>
							<colgroup>
								<col width="30%">
								<col width="70%">
							</colgroup>
							<thead>
								<tr valign="middle" class="tr_th">
									<th colspan = "2">판매하기 게시판 작성</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div>제목</div>
									</td>
									<td class = "tit">
										<input type = "text" maxlength = "90" name = "SUBJECT_S" class = "form-control tit" >
									</td>
								</tr>
								<tr>
									<td>
										<div>내용</div>
									</td>
									<td class = "tit">
										<textarea name = "CONTENT_S" maxlength = "1330" class = "textarea_content form-control">
1. 판매 아이템 : 
 
2. 판매 가격 : 

3. 아이템 설명 : 

4. 거래 방법 : 
										</textarea>
									</td>
								</tr>
								<tr>
									<td>
										<div>사진첨부</div>
									</td>
									<td class = "tit">
										<input name = "FILE_S" maxlength = "18" type = "file" ></input>
									</td>
								</tr>
								
									
							</tbody>
						</table>
						<div class="text-right">
							<a href = "javascript:history.go(-1)"  class="btn btn btn-warning">뒤로</a>
							<input type = "submit" value = "등록" class="btn btn btn-info"/>
						</div>
						</form>
					</div>
				</div>
				
				<jsp:include page="/inc/footer.jsp" flush="false" />
				
			</div>
		</div>
	</body>
</html>