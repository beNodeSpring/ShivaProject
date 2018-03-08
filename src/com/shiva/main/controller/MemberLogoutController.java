package com.shiva.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutController implements Controller {
	
	// id로 로그인한 세션을 제거(로그아웃)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		response.setContentType("text/html; charset=UTF-8");		
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("id") != null) {
			session.invalidate();
			System.out.println("MemberLogoutController : 로그아웃 업무 완료");
			out.print("<script>");
			out.print("alert('로그아웃이 완료되었습니다');");
			out.print("location.href='/ShivaProject';");
			out.print("</script>");				
		} else {
			System.out.println("MemberLogoutController : 로그인 상태 아님");
		}
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

}
