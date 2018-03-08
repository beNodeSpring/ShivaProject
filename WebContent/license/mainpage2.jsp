<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shiva.license.dao.*"%>
<%@ page import="com.shiva.license.vo.*"%>
<%
  
	String id = (String) session.getAttribute("id");
	
	String board_type = request.getParameter("board_type");
	
	String action = request.getParameter("action");
	int listcount=0;
    int nowpage=0;
    int maxpage=0;
    int startpage=0;
    int endpage=0;
     List<LicenseBoardVO> boardList =null;
     
     
%>
<!DOCTYPE html>
<html>
<head>
<% if(session.getAttribute("id")==null)
  {
    
    %>
    <script>
    location.href="./ShivaProject";
    </script>
    <% 
    
  } %>

<title>자격증메인페이지</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="/ShivaProject/css/style.css" rel="stylesheet">
<link href="/ShivaProject/css/sub.css" rel="stylesheet">
<link href="/ShivaProject/css/fa-brands.min.css" rel="stylesheet">
<link href="/ShivaProject/css/fa-regular.min.css" rel="stylesheet">
<link href="/ShivaProject/css/fa-solid.min.css" rel="stylesheet">
<link href="/ShivaProject/css/fontawesome-all.min.css" rel="stylesheet">

<style>
* {
	margin: 0;
	padding: 0;
    color:white;
}

.mb30{margin-bottom:4px;}
.table{ margin-bottom:0;
}

li {
	list-style: none;
}


a {
	text-decoration: none;
    color:white;
}

.cf:after {
	display: block;
	content: '';
	clear: both;
}

.top_section {
	
}

.top_section>div {
	float: left
}

.top_section .lft_img {
	width: 17%;
    min-width: 220px;
    margin-left:10px;
    margin-top:10px;
    padding-left:65px;
    padding-top:20px;
	background-color: #01A101;
	height: 146px;
  
}



.top_section .rft_text {
    width: 81%;
    max-width: calc(100% - 240px);
    margin-top:10px;
    padding-left:20px;
    padding-top:20px;
  background-color: #01A101;
  height: 146px;
  
}
.top_section .rft_text li
{
  float:left;
  width:200px;
  
}

.con_section {
	
}

.con_section>div {
	float: left
}

.con_section .lft_sec {
	min-width: 240px;
	width: 24%;
}

.con_section .rgh_sec {
	max-width: calc(100% - 320px);
    
    min-height:660px;
	width: 76%;
    margin-left: 10px;
    margin-right: 10px;
    margin-top: 15px;
    
    background:orange;
}

.button {
    padding-top:10%;
	display: block;
	width: 220px;
	height: 120px;
	text-align: center;
	vertical-align: middle;
	color: white;
	font-weight: bold;
	margin-top: 15px;
    margin-bottom: 15px;
    margin-right: 5px;
    margin-left: 10px;
    line-height:2;
}

.boardlist {
	width: 90%;
	margin-left: 5%;
	margin-right: 5%;
	margin-top: 5%;
    
}

.mini {
	background: gray;
}

body {
	background-image: url(images/bg_main-body6.jpg)
}

.tr_th {
	background-color: #F28E02;
}

.reply_head{width: 15%}

.reply_middle{width: 65%}

.modal-content
{
background: -webkit-linear-gradient(left, #014051 0%,#014051 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to right, #014051 0%,#014051 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.textinput{background-color:#64AA9C}
.btnc1 {
	background-color: orange
}
.btnc2 {
  background: -webkit-linear-gradient(left, #0099ab 0%,#259daa 100%); /* Chrome10-25,Safari5.1-6 */
  background: linear-gradient(to right, #0099ab 0%,#259daa 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.btnc3 {
  background: -webkit-linear-gradient(left, #9d00a5 0%,#9d2fa3 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to right, #9d00a5 0%,#9d2fa3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.btnc4 {
  background: -webkit-linear-gradient(left, #bd1c47 0%,#bc365a 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to right, #bd1c47 0%,#bc365a 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.btnc5 {
  background: -webkit-linear-gradient(left, #603cba 0%,#694bb7 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to right, #603cba 0%,#694bb7 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.writebtn{
margin-bottom:5px;
}

.fsize1{
font-size:30pt;
}
.fsize20{
font-size:20pt;
}
.admin_button{
	width :20%;
	height:10%;
	background:lightgray;
	margin-top: 15px;
    margin-bottom: 15px;
    margin-right: 5px;
    margin-left: 10px;
}

</style>
<script>
	
</script>
</head>


<!-- 게시판이 열릴때 그공간의 색깔과 th색을 바꿔줍니다. -->
<%if(board_type.equals("notice") ){%>
<style>
.con_section .rgh_sec {
background:orange;
}
.tr_th {
background:#F28E02;
}
.btn-color
{
  background:#F28E02;
}
.textinput{
background:#FFBE4F;
}
</style>
<%} else if(board_type.equals("free") ){%>
<style>
.con_section .rgh_sec {
background: -webkit-linear-gradient(left, #0099ab 0%,#259daa 100%); /* Chrome10-25,Safari5.1-6 */
  background: linear-gradient(to right, #0099ab 0%,#259daa 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.tr_th {
background:#008C72;
}
.btn-color
{
  background:#008C72;
}
.textinput{
background:#4EA1AA;
}
</style>
<%} else if(board_type.equals("complete") ){%>
<style>
.con_section .rgh_sec {
  background: -webkit-linear-gradient(left, #9d00a5 0%,#9d2fa3 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to right, #9d00a5 0%,#9d2fa3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.tr_th {
background:#7300A0;
}
.btn-color
{
  background:#7300A0;
}
.textinput{
background:#9D39A3;
}
</style>
<%} else if(board_type.equals("qna") ){%>
<style>
.con_section .rgh_sec {
  background: -webkit-linear-gradient(left, #bd1c47 0%,#bc365a 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to right, #bd1c47 0%,#bc365a 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
}
.tr_th {
background:#AD1A46;
}
.btn-color
{
  background:#AD1A46;
}
.textinput{
background:#BC536F;
}

</style>
<%} %>

<body>
 <jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
  <div class="license_section">
    <div class="container">
      <div class="row">
        <div class="modal-content">
          <div class="top_section cf">
            <div class="lft_img"><img src="images/dogfoot.png" style="width:100px; height: 100px;"> </div>
            <div class="rft_text"> <font class="fsize20">의 IT자격증 입니다.</font> <br>
            <ul><li>● 정보처리기사  </li><li> ● 정보처리기능사</li><li> ● 정보처리산업기사 </li><br> 
            <li>● SQLP(SQL전문가)</li><li> ● SQLD(SQL개발자)</li><li> ● 컴퓨터활용1급</li><br>
            <li>● 정보보안기사</li><li> ● 네트워크관리사</li><li> ● Linux마스터</li>
            </ul></div>
            <%if(id.equals("admin")){ %>
            <div class="admin_button"><a href="./AdminPage.lo"><i class="fsize1 fas fa-bullhorn"></i><font class="fsize20">관리자모드</font></a></div>              
            <%} %>
        </div>
          <div class="con_section cf">
            <div class="lft_sec">
              <!--  여기서부터 게시판공간 -->
              <div class="text-center"> 
              <ul>
                <li class="button btnc1"><a href="./List.lo?board_type=notice" ><i class="fsize1 fas fa-bullhorn"></i><br><font>자격증일정</font></a></li>
                <li class="button btnc2"><a href="./List.lo?board_type=free" ><i class="fsize1 far fa-comment-alt"></i><br><font>자유게시판</font></a></li>
                <li class="button btnc3"><a href="./List.lo?board_type=complete" ><i class="fsize1 far fa-address-card"></i><br><font>자격증 합격후기</font></a></li>
                <li class="button btnc4"><a href="./List.lo?board_type=qna" ><i class="fsize1 fas fa-meh"></i><br><font>질문게시판</font></a></li>
                <li class="button btnc5"><a href="./Calendar.lo" ><i class="fsize1 far fa-calendar-alt"></i><br><font>일정캘린더</font></a></li>
               
                
                
              </ul>
            </div>
          </div>
            <div class="rgh_sec">
              <div class="boardlist">
                <%if(board_type.equals("notice")){ %>
                <h3>자격증일정 게시판</h3>
                <%} else if(board_type.equals("free")){%>
                <h3>자유 게시판</h3>
               	<%} else if(board_type.equals("complete")){%>
                <h3>합격후기 게시판</h3>
                <%} else if(board_type.equals("qna")){%>
                <h3>질문답변 게시판</h3>
                <%} %>
                
                <%if(action.equals("list")||action.equals("search")){ // 액션이 검색과 리스트출력일때마다 데이터를 호출
                            listcount=((Integer)request.getAttribute("listcount")).intValue();
                             nowpage=((Integer)request.getAttribute("page")).intValue();
                             maxpage=((Integer)request.getAttribute("maxpage")).intValue();
                             startpage=((Integer)request.getAttribute("startpage")).intValue();
                             endpage=((Integer)request.getAttribute("endpage")).intValue();
                             boardList = (ArrayList<LicenseBoardVO>)request.getAttribute("boardlist");
                            
                             %>
                
                
                  <div class="text-right mb30">
                  <form  name ="searchform"  class="form-inline" action="./Search.lo">
                  <select id=search name="option" class="form-control">
                      <option value="subject" selected>제목</option>
                      <option value="name">작성자</option>
                  </select> <input type="text" class="form-control" name="searchname" id=""> <input type="button" class="btn btn-color"  value="검색" onclick="javascript:searchform.submit()"> 
                  <input type=hidden name="board_type" value=<%=board_type%> >
                  </form></div> <!--  윗줄 끝 -->
                  <table class="table table-responsive mb50" summary="게시판 설명">
                    <caption class="sr-only">게시판 제목</caption>
                    <colgroup>
                      <col width="10%">
                      <col width="40%">
                      <col width="20%">
                      <col width="20%">
                      <col width="10%">
                  </colgroup>
                    <thead>
                      <tr class="tr_th">
                        <th scope="col">번호</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성자</th>
                        <th scope="col">작성일</th>
                        <th scope="col">조회수</th>
                    </tr>
                  </thead>
                    
                    <tbody>
                    <%if(listcount>0){%>
                    <!-- 게시글이 있는경우 게시물출력-->
                    <%		LicenseDAO boarddao = new LicenseDAO(); //리플숫자를 출력하기 위해 db연결
                    		int replynum=0;
                    		String replyboardname="";
                           for(int i=0;i<boardList.size();i++){
                        	   LicenseBoardVO bl=(LicenseBoardVO)boardList.get(i);
                        	   // 리플 카운트 받아오기 위한 보드네임 지정
                        	   replynum=boardList.get(i).getBOARD_NUM();
                 				if(board_type.equals("notice")) {
                 				replyboardname="IT_NOTICE_REPLY";
								}
                 				else if(board_type.equals("free")) {
                     				replyboardname="IT_COMUNITY_REPLY";
    								}
                 				else if(board_type.equals("complete")) {
                     				replyboardname="IT_COMPLETE_REPLY";
    								}
                 				else if(board_type.equals("qna")) {
                     				replyboardname="IT_QNA_REPLY";
    								}
			
                        	   replynum=boarddao.getReplyCount(replynum,replyboardname );
                     %>
                      <tr>
                        <td><strong class="bd_noti"><%=bl.getBOARD_NUM()%></strong></td>
                        <td class="tit"><a href="./View.lo?num=<%=bl.getBOARD_NUM()%>&board_type=<%=board_type%>"> 
                        <%=bl.getBOARD_SUBJECT()%><!-- 댓글 숫자출력 -->
                        <%if(replynum>0){ %>(<%=replynum %>)<% }%></a></td>
                        <td><%=bl.getBOARD_NAME()%></td>
                        <td><%=bl.getBOARD_DATE() %></td>
                        <td><%=bl.getBOARD_COUNT() %></td> 
                    </tr>
                    
                      
                      <% } //for end%>
                      
                      <tr class="text-center">
                    <td colspan =5> <!-- 여기서부터 페이지이동처리 -->
                         <%if(nowpage<=1){ %>
                          [이전]&nbsp;
                          <%}else { if(action.equals("list")){%>
                          <a href="./List.lo?page=<%=nowpage-1 %>&board_type=<%=board_type%>">
                          [이전]
                          </a>&nbsp;
                          <%} else if(action.equals("search")){ %> 
                           <a href="./Search.lo?page=<%=nowpage-1 %>&option=<%=request.getParameter("option")%>&searchname=<%=request.getParameter("searchname")%>&board_type=<%=board_type%>">
                          [이전]
                          </a>&nbsp;
                            
                        <%   }  } %>
                          <%for(int a=startpage;a<=endpage;a++) {
                          if(a==nowpage){ %>
                            [<%=a %>]
                            <%}else{  if(action.equals("list")){%>
                            <a href="./List.lo?page=<%=a%>&board_type=<%=board_type%>">[<%=a %>]</a>
                            <%} else if(action.equals("search")){ %>
                             <a href="./Search.lo?page=<%=a%>&option=<%=request.getParameter("option")%>&searchname=<%=request.getParameter("searchname")%>&board_type=<%=board_type%>">[<%=a %>]</a>
                              
                           <% } }%>
                          <%} %>
                          
                          <% if(nowpage>=maxpage){%>
                          [다음]
                          <%}else { if(action.equals("list")){%>
                          <a href="./List.lo?page=<%=nowpage+1%>&board_type=<%=board_type%>">[다음]</a>
                          <%} else if(action.equals("search")) { %>
                           <a href="./Search.lo?page=<%=nowpage+1%>&option=<%=request.getParameter("option")%>&searchname=<%=request.getParameter("searchname")%>&board_type=<%=board_type%>">[다음]</a>
              
                            <% }}%>
                          </td>
                          
                        </tr>
                      <% }//if end 
                          else{  %>
                      <!-- 게시글이 없는경우  -->
                        <tr >
                        <td colspan=5 style="text-align:center"> 등록된 글이 없습니다. </td>
                        </tr>
                      <%} //else end%>
                      
                      <tr></tr>
                  </tbody>
                </table>
                <!-- 글쓰기 버튼! -->
                  <%if(!board_type.equals("notice")){ %>
                  <div class="text-right writebtn"><input type=button value="글쓰기" class="btn btn-color" onclick="location.href='./WriteView.lo?board_type=<%=board_type%>'"></div>
                  <%} %>
                  <%if(board_type.equals("notice")&&id.equals("admin")){ %>
                  <div class="text-right writebtn"><input type=button value="글쓰기" class="btn btn-color" onclick="location.href='./WriteView.lo?board_type=<%=board_type%>'"></div>
                  <%} %>
               <!-- 게시물리스트 보여주기 끝 -->
               
               <!-- 게시물 보기 -->
             <%  } else if(action.equals("view")){  LicenseBoardVO board = (LicenseBoardVO)request.getAttribute("boarddata"); //액션 처리 view 게시물보기 %>
                      <form class=board>
                        <table class="table table-responsive mb50" border=1>
                        <tr class="tr_th text-center">
                        <th colspan=5>게시판 읽기</th>
                        </tr>
                        <tr>
                        <td>작성자 :</td><td colspan=4><%=board.getBOARD_NAME() %></td>
                        </tr>
                        <tr>
                        <td>글제목 :</td><td colspan=4><%=board.getBOARD_SUBJECT() %></td>
                        </tr>
                      
                        <tr>
                        <td>내용 :</td><td colspan=4><textarea readonly rows=12 cols=90 class="textinput"><%=board.getBOARD_CONTENT() %></textarea></td>
                        </tr>
                        
                        
                        
                        
                        </form>
                        <!-- 리플 보여주는공간 -->
                        
                        <form name="reply" action="./ReplyAdd.lo">
                        
                        
                        <% List<LicenseReplyVO> replyList = (ArrayList<LicenseReplyVO>)request.getAttribute("replylist");%>
                        <!-- 리플 출력! -->
                        <%if(replyList.size()==0){%>
                        <tr><td colspan=5>현재 리플이 없습니다.</td></tr>
                        <%}else{ for(int i=0;i<replyList.size();i++){%>
                        <tr><td class=reply_head><%=replyList.get(i).getREPLY_NAME() %></td><td colspan=3 class=reply_middle><%=replyList.get(i).getREPLY_CONTENT()%></td>
                        <%if(id.equals(replyList.get(i).getREPLY_NAME())||id.equals("admin")){ %>
                        <td><input type="button" value="삭제" class="btn btn-color" onclick="location.href='./ReplyDelete.lo?board_type=<%=board_type%>&REPLY_NUM=<%=replyList.get(i).getREPLY_NUM()%>&REPLY_LEVEL=<%=replyList.get(i).getREPLY_LEVEL()%>'"></td>
                        <%} else{%>
                        <td>&nbsp;</td>
                        <%} %>
                        
                        </tr>
                        <%}//for end 
                          
                        }//else end %>
                        <!-- 리플 작성공간 -->
                        
                        <tr><td class=reply_head><%=id %></td><td colspan=3 class=reply_middle><textarea name="REPLY_CONTENT" cols=80 rows=2 class="textinput"></textarea></td>
                        <td><%if(!board_type.equals("notice")){ %><input class="btn btn-color" type="button" value="작성" onclick="javascript:reply.submit()"><%}%> </td></tr>                        
                                          
                        </table>
                        <!-- 리플 데이터 넣을곳 -->
                        <input name="REPLY_NUM" type="hidden" value=<%=board.getBOARD_NUM()%>> 
                        <input name="REPLY_NAME" type=hidden value=<%=id%>> 
                        <input name="board_type" type=hidden value=<%=board_type%>>
                        <!-- 버튼 공간 -->
                        <div class="text-right">
                        <%if(board.getBOARD_NAME().equals(id)||id.equals("admin")){%>
                        <input type=button value="수정" class="btn btn-color" onclick="location.href='./ModifyView.lo?num=<%=board.getBOARD_NUM() %>&board_type=<%=board_type%>'">
                        <input type=button value="삭제" class="btn btn-color" onclick="location.href='./Delete.lo?num=<%=board.getBOARD_NUM() %>&board_type=<%=board_type%>'">
                        <%} %>
                        <input type=button value="목록" class="btn btn-color" onclick="location.href='./List.lo?board_type=<%=board_type%>'">
                        </div>
                        </form>
                      <%} else if(action.equals("modify")) { LicenseBoardVO board = (LicenseBoardVO)request.getAttribute("boarddata"); //수정하긔!%>
                      <form name=modifyboard action="./Modify.lo">
                        <table class="table table-responsive mb50" border=1>
                        <tr class="tr_th text-center">
                        <th colspan=5>게시판 수정</th>
                        </tr>
                        <tr>
                        <td>작성자 :</td><td colspan=4><%=board.getBOARD_NAME() %></td>
                        
                        </tr>
                        <tr>
                        <td>글제목 :</td><td colspan=4><input name=BOARD_SUBJECT type="text" class="textinput" size=60 value='<%=board.getBOARD_SUBJECT() %>'></td>
                        </tr>
                      
                        <tr>
                        <td>내용 :</td><td colspan=4><textarea name=BOARD_CONTENT rows=12 cols=90 class="textinput"><%=board.getBOARD_CONTENT() %></textarea></td>
                        </tr>
                        </table>
                        <!-- name지정 안된객체들을 저장... -->
                        <input type=hidden name="BOARD_NAME" value=<%=board.getBOARD_NAME()%> >
                        <input type=hidden name="BOARD_NUM" value=<%=board.getBOARD_NUM()%> >
                        <input type=hidden name="board_type" value=<%=board_type%> >
                        <div class="text-right">
                        <input type=button value="수정완료" class="btn btn-success" onclick="javascript:modifyboard.submit()">
                        <input type=button value="목록" class="btn btn-info" onclick="location.href='./List.lo?board_type=<%=board_type%>'">
                        </div>
                        <br>
                        </form>
                      <%} else if(action.equals("write")) {  //글작성 쓰긔!%>
                        <form name=writeboard action="./Write.lo">
                      <table class="table table-responsive mb50" border=1>
                      <tr class="tr_th">
                      <th colspan=5>게시판 작성</th>
                      </tr>
                      <tr>
                      <td>작성자 :</td><td colspan=4><%=id %></td>
                      </tr>
                      <tr>
                      <td>글제목 :</td><td colspan=4><input name="BOARD_SUBJECT" type=text size=50 class="textinput"></td>
                      </tr>
                    
                      <tr>
                      <td>내용 :</td><td colspan=4><textarea name="BOARD_CONTENT" rows="12" cols="90" class="textinput"></textarea></td>
                      </tr>
                      
                      </table>
                      <!-- name지정 안된객체들을 저장... -->
                      <input type=hidden name="BOARD_NAME" value=<%=id%> >
                        <input type=hidden name="board_type" value=<%=board_type%> >
                      <div class="text-right">
                      <input type=button value="작성완료" class="btn btn-color" onclick="javascript:writeboard.submit()">
                      </div>
                      <br>
                      </form>
                    	  
                     <% } %>
            </div>
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="/inc/footer.jsp" flush="false"></jsp:include>
  </div>
</div>
  
</body>

</html>