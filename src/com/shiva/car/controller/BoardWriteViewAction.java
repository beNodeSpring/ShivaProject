//글 등록에 대한 Action 클래스
package com.shiva.car.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shiva.car.dao.BoardDAO;
import com.shiva.car.vo.BoardVO;

public class BoardWriteViewAction implements Action {
	 public ActionForward execute(HttpServletRequest request,
			      HttpServletResponse response) throws Exception{
		 
		BoardDAO  boarddao   =new BoardDAO();
	   	BoardVO boarddata  =new BoardVO();
	   	ActionForward forward=new ActionForward();
	   	 		
   		//실제 저장 경로를 지정합니다.
   		ServletContext sc = request.getServletContext();
   		
   		
   		boolean result=false;
   		
   		try{   			
   			//MultipartRequest multi=null;   			
   				
   			//BoardBean 객체에 글 등록 폼에서 입력 받은 정보들을 저장합니다.
   			boarddata.setBOARD_NAME(
   					request.getParameter("BOARD_NAME"));   	
	   		boarddata.setBOARD_SUBJECT(
	   				request.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBOARD_CONTENT(
	   				request.getParameter("BOARD_CONTENT"));
	   		
	   		
	   		
	   		
	   		//글 등록 처리를 위해 DAO의 boardInsert()메서드를 호출합니다.
	   		//글 등록 폼에서 입력한 정보가 저장되어 있는 boarddata객체를 전달합니다.
	   		result=boarddao.boardInsert(boarddata);
	   		
	   		//글 등록에 실패할 경우 null을 반환합니다.
	   		if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		System.out.println("게시판 등록 완료");
	   		
	   		//글 등록이 완료되면 글 목록을 단순히 보여주기만 할 것이므로 
	   		//Redirect여부를 true로 설정합니다.
	   		forward.setRedirect(true);
	   		//이동할 경로를 지정합니다.
	   		forward.setPath("./BoardList.co");
	   		return forward;	   		
  		}catch(Exception ex){  			
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}