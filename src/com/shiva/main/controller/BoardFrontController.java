package com.shiva.main.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

import com.shiva.main.service.*;


// web.xml에 등록 안하고 @WebServlet으로도 RequestMapping 가능
@WebServlet("*.bbs")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 2L;
	String charset = null;                   // 인코딩 하려는 문자코드를 저장
	HashMap<String, BoardService> list = null; // 클라이언트 요청에 대하여 실제 실행할 컨트롤러가 누구인지에 대한 정보를 저장
	
	// 서블릿 초기화 기능을 담당
	@Override
	public void init(ServletConfig sc) throws ServletException {
		//charset = sc.getInitParameter("charset"); // web.xml의 <param-value>값 추출
		
		// 클라이언트 요청에 대하여 실제로 처리하는 서브 컨트롤러를 실행
		list = new HashMap<String, BoardService>();
		list.put("/boardList.bbs", new BoardListService());
		list.put("/boardWriteForm.bbs", new BoardWriteFormService());
		list.put("/boardWrite.bbs", new BoardWriteService());
		list.put("/boardRead.bbs", new BoardReadService());
		list.put("/boardUpdateForm.bbs", new BoardUpdateFormService());
		list.put("/boardUpdate.bbs", new BoardUpdateService());
		list.put("/boardDelete.bbs", new BoardDeleteService());
		list.put("/boardSearch.bbs", new BoardSearchService());
		list.put("/boardReplyForm.bbs", new BoardReplyFormService());
		list.put("/boardReply.bbs", new BoardReplyService());

	}
	
	// 클라이언트로 요청이 들어올 때마다 실행하는 로직
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");		
		
		String url=request.getRequestURI();                //  /ShivaProject/업무.shiva
		String contextPath = request.getContextPath();     //  /ShivaProject
		String path = url.substring(contextPath.length()); //  /업무.shiva
		
		BoardService boardService = list.get(path);         //  해당 서비스의 경로를 가져와 저장
		boardService.execute(request, response);            //  boardService를 구현하는 클래스의 execute() 실행

	}

}
