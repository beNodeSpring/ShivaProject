package com.shiva.main.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainFrontController")
public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String charset = null;                   // 인코딩 하려는 문자코드를 저장
	HashMap<String, Controller> list = null; // 클라이언트 요청에 대하여 실제 실행할 컨트롤러가 누구인지에 대한 정보를 저장

	// 서블릿 초기화 기능을 담당
	@Override
	public void init(ServletConfig sc) throws ServletException {
		charset = sc.getInitParameter("charset"); // web.xml의 <param-value>값 추출
		list =new HashMap<String, Controller>();
		list.put("/memberInsert.shiva", new MemberInsertController());
		list.put("/memberSearch.shiva", new MemberSearchController());
		list.put("/memberUpdate.shiva", new MemberUpdateController());
		list.put("/memberDelete.shiva", new MemberDeleteController());
		list.put("/memberList.shiva", new MemberListController());
	}
	
	// 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(charset);
		String url=request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		Controller subController = list.get(path);
		subController.execute(request, response);
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
