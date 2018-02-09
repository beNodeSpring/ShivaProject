package com.shiva.main.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String charset = null;                   // 인코딩 하려는 문자코드를 저장
	HashMap<String, Controller> list = null; // 클라이언트 요청에 대하여 실제 실행할 컨트롤러가 누구인지에 대한 정보를 저장

	// 서블릿 초기화 기능을 담당
	@Override
	public void init(ServletConfig sc) throws ServletException {
		charset = sc.getInitParameter("charset"); // web.xml의 <param-value>값 추출
		
		// 클라이언트 요청에 대하여 실제로 처리하는 서브 컨트롤러를 실행
		list = new HashMap<String, Controller>();
		list.put("/memberInsert.shiva", new MemberInsertController());
		list.put("/memberSearch.shiva", new MemberSearchController());
		list.put("/memberUpdate.shiva", new MemberUpdateController());
		list.put("/memberDelete.shiva", new MemberDeleteController());
		list.put("/memberLogin.shiva", new MemberLoginController());
		list.put("/memberLogout.shiva", new MemberLogoutController());
	}
	
	// 클라이언트로 요청이 들어올 때마다 실행하는 로직
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("프론트컨트롤러 service");
		request.setCharacterEncoding(charset);             //  post로 전달된 쿼리 문자열을 한글 인코딩 처리
		String url=request.getRequestURI();                //  /ShivaProject/업무.shiva
		String contextPath = request.getContextPath();     //  /ShivaProject
		String path = url.substring(contextPath.length()); //  /업무.shiva
		//System.out.println(path);
		Controller subController = list.get(path);         //  해당 업무의 컨트롤러의 주소값을 가져와 저장
		subController.execute(request, response);          //  controll 인터페이스때문에 execute() 실행
		//System.out.println("service 완료");//★ 완성후 지우기
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
