package com.shiva.main.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 2L;
	String charset = null;                   // 인코딩 하려는 문자코드를 저장
	HashMap<String, Controller> list = null; // 클라이언트 요청에 대하여 실제 실행할 컨트롤러가 누구인지에 대한 정보를 저장

	// 서블릿 초기화 기능을 담당
	@Override
	public void init(ServletConfig sc) throws ServletException {
		charset = sc.getInitParameter("charset"); // web.xml의 <param-value>값 추출
		
		// 클라이언트 요청에 대하여 실제로 처리하는 서브 컨트롤러를 실행
		list = new HashMap<String, Controller>();
		list.put("/boardList.shiva", new BoardListController());

	}
	
	// 클라이언트로 요청이 들어올 때마다 실행하는 로직
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(charset);
		response.setContentType("text/html; charset="+charset);		
		
		String url=request.getRequestURI();                //  /ShivaProject/업무.shiva
		String contextPath = request.getContextPath();     //  /ShivaProject
		String path = url.substring(contextPath.length()); //  /업무.shiva
		Controller subController = list.get(path);         //  해당 업무의 컨트롤러의 주소값을 가져와 저장
		subController.execute(request, response);          //  controll 인터페이스때문에 execute() 실행
		System.out.println("현재 실행된 컨트롤러명"+list.get(path));
	}

}
