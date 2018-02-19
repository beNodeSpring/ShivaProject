//글 삭제에 대한 Action 클래스
package com.shiva.car.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.car.dao.BoardDAO;


public class BoardDeleteAction implements Action {
	 public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) 
	 	throws Exception{
		 
		request.setCharacterEncoding("utf-8");
		
	   	boolean result=false;
	  
	   	int num=Integer.parseInt(request.getParameter("BOARD_NO"));
	   	
	   	BoardDAO boarddao=new BoardDAO();
	   	
	 	   	   
	   	
	   	//비밀번호 일치하는 경우 삭제 처리 합니다.
	   	result=boarddao.boardDelete(num);
	   	//삭제 처리 실패한 경우
	   	if(result==false){
	   		System.out.println("게시판 삭제 실패");
	   		return null;
	   	}
	   	//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
	   	System.out.println("게시판 삭제 성공");
	   	response.setContentType("text/html;charset=utf-8");
   		PrintWriter out=response.getWriter();
   		out.println("<script>");
   		out.println("alert('삭제 되었습니다.');");
   		out.println("location.href='./CarBoardList.co';");
   		out.println("</script>");
   		out.close();
   		return null;
	 }
}